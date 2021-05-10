package com.ajaguilar.Taller.Modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;

import Utiles.Conexion;

public class ReparacionDAO extends Reparacion {
	private static final String GETBYID="SELECT id,precio,matricula,descripcion,dni_client as client FROM reparacion WHERE id=";
	private static final String GETBYMATRI="SELECT id,precio,matricula,descripcion,dni_client as client FROM reparacion WHERE matricula=";
	private static final String DELETE="DELETE FROM reparacion WHERE id=?";
	private static final String SELECTALL="SELECT `id`, `precio`, `matricula`, `descripcion`, `fecha`, `dni_client` FROM `reparacion`";
	private static final String SELECTID="SELECT  `id`, `precio`, `matricula`, `descripcion`, `fecha`, `dni_client` FROM `reparacion` WHERE id=?";

	private final static String INSERTUPDATE="INSERT INTO reparacion(precio, matricula, descripcion, fecha, dni_client) "
			+ "VALUES (?,?,?,?,?) "
			+ "ON DUPLICATE KEY UPDATE precio=?,matricula=?,descripcion=?,fecha=?,dni_client=?";
	
	
	private final static String GETBYCLIENT="SELECT reparacion.id,reparacion.precio,reparacion.matricula,reparacion.descripcion,reparacion.fecha,reparacion.dni_client, "
			+ "client.nombre,client.direccion "
			+ "FROM reparacion,client WHERE reparacion.dni_client=client.dni AND reparacion.dni_client=?";
	
	public ReparacionDAO(double precio,String matricula,String descripcion,String fecha, Client cliente) {
		super(precio,matricula,descripcion, fecha,cliente);
	}
	public ReparacionDAO() {
		super();
	}
	public ReparacionDAO(Reparacion r) {
		this.id=r.getId();
		this.precio=r.getPrecio();
		this.matricula=r.getMatricula();
		this.descripcion=r.getDescripcion();
		this.fecha=r.getFecha();
		this.miclient=r.getMiclient();
	}
	
	public ReparacionDAO(int id) {
		super();
		Connection con = Conexion.getConexion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q=GETBYID+id;
				ResultSet rs =st.executeQuery(q);
				while(rs.next()) {
					this.id=rs.getInt("id");
					this.precio=rs.getDouble("precio");
					this.matricula=rs.getString("matricula");
					this.descripcion=rs.getString("descripcion");
					this.fecha=rs.getString("fecha");
					
					this.miclient=new ClientDAO(rs.getString("client"));
				}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ReparacionDAO(String matricula) {
		super();
		Connection con = Conexion.getConexion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q=GETBYMATRI+"'"+matricula+"'";
				ResultSet rs =st.executeQuery(q);
				while(rs.next()) {
					this.id=rs.getInt("id");
					this.precio=rs.getDouble("precio");
					this.matricula=rs.getString("matricula");
					this.descripcion=rs.getString("descripcion");
					this.fecha=rs.getString("fecha");

					
					this.miclient=new ClientDAO(rs.getString("client"));
				}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static List<Reparacion> getTodasRepas() {
		List<Reparacion> result=new ArrayList<Reparacion>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTALL);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Reparacion a=new Reparacion();
					a.setId(rs.getInt("id"));
					a.setPrecio(rs.getDouble("precio"));
					a.setMatricula(rs.getString("matricula"));
					a.setDescripcion(rs.getString("descripcion"));
					a.setFecha(rs.getString("fecha"));
					
					ClientDAO cl=new ClientDAO(rs.getString("dni_client"));
					a.setMiclient(cl);

					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar todos reparaciones");
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static List<Reparacion> getReparacionById(int id) {
		List<Reparacion> result=new ArrayList<Reparacion>();
		Connection con = Conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTID);
				q.setInt(1,id);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Reparacion a=new Reparacion();
					a.setId(rs.getInt("id"));
					a.setPrecio(rs.getDouble("precio"));
					a.setMatricula(rs.getString("matricula"));
					a.setDescripcion(rs.getString("descripcion"));
					a.setFecha(rs.getString("fecha"));

					ClientDAO cl=new ClientDAO(rs.getString("dni_client"));
					a.setMiclient(cl);
					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en reparacionDAO al buscar todos reparaciones");
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	public int guardar() {

		int rs=0;
		Connection con = Conexion.getConexion();
		if(this.miclient==null) {
			this.miclient=new Client();
		}
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(INSERTUPDATE);
				q.setDouble(1, this.precio);
				q.setString(2, this.matricula);
				q.setString(3, this.descripcion);
				q.setString(4, this.fecha);
				q.setString(5, this.miclient!=null?this.miclient.getDni():"0");
				q.setDouble(6, this.precio);
				q.setString(7, this.matricula);
				q.setString(8, this.descripcion);
				q.setString(9, this.fecha);
				q.setString(10, this.miclient.getDni());
				
				rs =q.executeUpdate();		
			} catch (SQLException e) {
				System.out.println("Error al guardar reparacionDAO");
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int eliminar() {
		int rs=0;
		Connection con = Conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs =q.executeUpdate();
				this.id=-1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	@Override
	public Client getMiclient() {
		if(this.miclient==null || this.miclient.getDni().equals("-1")) {
			this.miclient=ClientDAO.getClientByReparacion(this.id);
		}
		//this.miclient.setMiReparaciones(null);;

		return super.getMiclient();
	}
	
	public static List<Reparacion> getReparacionByClient(String dni) {
		List<Reparacion> result=new ArrayList<Reparacion>();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(GETBYCLIENT);
				q.setString(1, dni);
				ResultSet rs =q.executeQuery();
				while(rs.next()) {
					Reparacion l=new Reparacion();
					l.setId(rs.getInt("id"));
					l.setPrecio(rs.getDouble("precio"));
					l.setMatricula(rs.getString("matricula"));
					l.setDescripcion(rs.getString("descripcion"));
					l.setFecha(rs.getString("fecha"));
					
					
					Client a=new Client();
					a.setDni(rs.getString("dni_client"));
					a.setNombre(rs.getString("nombre"));
					a.setDireccion(rs.getString("direccion"));
					
					l.setMiclient(a);
					result.add(l);
				}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparacion other = (Reparacion) obj;
		if (id != other.getId())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Reparacion [id=" + id + ", precio=" + precio + ", matricula=" + matricula + ", descripcion=" + descripcion + ", Fecha=" + fecha +/*", dni_client="+ miclient.getDni()+*//* ", miclient=" + getMiclient() + */"]";
	}
	
	
	

}

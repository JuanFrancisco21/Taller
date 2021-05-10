package com.ajaguilar.Taller.Modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;

import Utiles.Conexion;



public class ClientDAO extends Client {

	private final static String GETBYDNI = "SELECT dni,nombre,direccion FROM client WHERE dni=";
	private final static String INSERTUPDATE="INSERT INTO client (dni, nombre, direccion) "
			+ "VALUES (?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?,direccion=?";
	private final static String DELETE ="DELETE FROM client WHERE dni=?";
	private final static String SELECTBYNAME ="SELECT dni,nombre,direccion FROM client WHERE nombre LIKE ?";
	private final static String SELECTALL ="SELECT dni,nombre,direccion FROM client";
	private final static String SELECTBYDNI ="SELECT dni,nombre,direccion FROM client WHERE dni LIKE ?";

	
	
	public ClientDAO(String dni, String nombre, String direccion) {
		super(dni, nombre, direccion);
	}

	public ClientDAO(String nombre,String direccion) {
		super(nombre, direccion);
	}

	public ClientDAO() {
		super();
	}

	/// DAO
	public ClientDAO(Client a) {
		this.dni = a.getDni();
		this.nombre = a.getNombre();
		this.direccion = a.getDireccion();
		this.Reparaciones=a.getMiReparaciones();
	}

	public ClientDAO(String dni) {

		super();
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q=GETBYDNI+"'"+dni+"'";
				ResultSet rs =st.executeQuery(q);
				while(rs.next()) {
					this.dni=rs.getString("dni");
					this.nombre=rs.getString("nombre");
					this.direccion=rs.getString("direccion");
				}			
				this.Reparaciones=ReparacionDAO.getReparacionByClient(this.dni);
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al conectar");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Buscar todos los clientes de la base de datos
	 * @return devuelve una lista de clientes
	 */
	public static List<Client> TodosClient() {
		List<Client> result=new ArrayList<Client>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTALL);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					Client a=new Client();
					a.setDni(rs.getString("dni"));
					a.setNombre(rs.getString("nombre"));
					a.setDireccion(rs.getString("direccion"));
					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar todos Clientes");
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	public int guardar() {
		int rs=0;
		Connection con = Conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.dni);
				q.setString(2, this.nombre);
				q.setString(3, this.direccion);
				q.setString(4, this.nombre);
				q.setString(5, this.direccion);
				rs =q.executeUpdate();		
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al guardar");
				e.printStackTrace();
			}
		}
		return rs;
	}
    @Override
    public List<Reparacion> getMiReparaciones(){
		if(Reparaciones==null) {
			Reparaciones=ClientDAO.buscaReparacionDni(this.dni);
		}
		return Reparaciones;
    }
	public int eliminar() {
		int rs=0;
		Connection con = Conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setString(1, this.dni);
				rs =q.executeUpdate();
				this.dni="-1";
				this.nombre="";
				this.direccion="";
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al eliminar");
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Client> buscaPorNombre(String nombre) {
		List<Client> result=new ArrayList<Client>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%"+nombre+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Client a=new Client();
					a.setDni(rs.getString("dni"));
					a.setNombre(rs.getString("nombre"));
					a.setDireccion(rs.getString("direccion"));
					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar nombre");
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static Client buscaPorDni(String dni) {
		List<Client> result=new ArrayList<Client>();
		Client a=new Client();

		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYDNI);
				q.setString(1, "%"+dni+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					a.setDni(rs.getString("dni"));
					a.setNombre(rs.getString("nombre"));
					a.setDireccion(rs.getString("direccion"));
					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar nombre");
				e.printStackTrace();
			}
		}
		
		return a;
	}

	public static List<Client> buscaPorDireccion(int edadmenor, int edadmayor) {
		return null;
	}
	
	public static List<Reparacion> buscaReparacionDni(String dni){
		return null;
	}

	public static Client getClientByReparacion(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
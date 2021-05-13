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



public class ClientDAO extends Client implements DAO{

	private final static String GETBYDNI = "SELECT dni,nombre,direccion FROM client WHERE dni=";
	private final static String INSERTUPDATE="INSERT INTO client (dni, nombre, direccion) "
			+ "VALUES (?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?,direccion=?";
	private final static String DELETE ="DELETE FROM client WHERE dni=?";
	private final static String SELECTBYNAME ="SELECT dni,nombre,direccion FROM client WHERE nombre LIKE ?";
	private final static String SELECTALL ="SELECT dni,nombre,direccion FROM client";
	private final static String SELECTBYDNI ="SELECT dni,nombre,direccion FROM client WHERE dni LIKE ?";
	private final static String SELECTREPBYDNI ="SELECT  `id`, `precio`, `matricula`, `descripcion`, `fecha`, `dni_client` FROM `reparacion` WHERE dni_client=";

	
	/**
	 * Contructor Full de cliente
	 * @param dni de un cliente
	 * @param nombre de un cliente
	 * @param direccion de un cliente
	 */
	public ClientDAO(String dni, String nombre, String direccion) {
		super(dni, nombre, direccion);
	}

	/**
	 * Constructor de cliente por defecto.
	 */
	public ClientDAO() {
		super();
	}

	/**
	 * Metodo para convertir cliente en clienteDAO.
	 * @param a cliente a covertir
	 */
	public ClientDAO(Client a) {
		this.dni = a.getDni();
		this.nombre = a.getNombre();
		this.direccion = a.getDireccion();
		this.Reparaciones=a.getMiReparaciones();
	}
	
	/**
	 * Metodo de busqueda de cliente por dni.
	 * @param dni por el cual se busca al cliente.
	 */
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
	
	/**
	 * Metodo de guardado de un cliente.
	 * @return devuelve un entero
	 */
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
	
	/**
	 * Metodo de busqueda de reparaciones mediante
	 */
    @Override
    public List<Reparacion> getMiReparaciones(){
		if(Reparaciones==null) {
			Reparaciones=ClientDAO.buscaReparacionDni(this.dni);
		}
		return Reparaciones;
    }
    
    /**
     * Metodo por el cual se borran clientes.
     * @return devuelve un entero
     */
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
				System.out.println("Error en clienteDAO al eliminar cliente");
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Metodo para buscar personas por su nombre.
	 * @param nombre por el cual se busca al cliente.
	 * @return devuelve una lista de personas.
	 */
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
	
	/**
	 * Metodo para buscar persona por su dni.
	 * @param dni por el cual se busca al cliente.
	 * @return devuelve una lista de personas.
	 */
	public static Client buscaPorDni(String dni) {
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
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar nombre");
				e.printStackTrace();
			}
		}
		
		return a;
	}

	
	public static List<Reparacion> buscaReparacionDni(String dni){
		List<Reparacion> result=new ArrayList<Reparacion>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTREPBYDNI);
				q.setString(1, "%"+dni+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Reparacion a=new Reparacion();
					a.setId(rs.getInt("id"));
					a.setPrecio(rs.getDouble("precio"));
					a.setMatricula(rs.getString("matricula"));
					a.setDescripcion(rs.getString("descripcion"));
					a.setFecha(rs.getString("fecha"));
					result.add(a);
				}
			} catch (SQLException e) {
				System.out.println("Error en clienteDAO al buscar nombre");
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public static Client getClientByReparacion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
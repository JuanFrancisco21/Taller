package com.ajaguilar.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	protected String dni;
	protected String nombre;
	protected String direccion;
	protected List<Reparacion> Reparaciones;
	
	
	public Client() {
		this("-1", "", "",new ArrayList<Reparacion>());
	}

	

	public Client(String dni, String nombre, String direccion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public Client(String nombre, String direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public Client(String dni, String nombre, String direccion, List<Reparacion> reparaciones) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		Reparaciones = reparaciones;
	}


	public Client(String dni, String nombre, String direccion, Reparacion id) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		Reparaciones.add(id);
	}

	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Reparacion> getMiReparaciones() {
		return Reparaciones;
	}

	public void setMiReparaciones(List<Reparacion> reparaciones) {
		Reparaciones = reparaciones;
	}
	public String getMiReparacionesSize() {
		String result="";
		result.valueOf(this.Reparaciones.size());
		System.out.println(result);
		return result;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Client other = (Client) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Client [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", Reparaciones="
				+ Reparaciones + "]";
	}

	
}
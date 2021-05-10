package com.ajaguilar.modelo;

public class Reparacion {
	protected    int id;
	protected double precio;
	protected String matricula;
	protected String descripcion;
	protected String fecha;
	protected Client miclient;
	
	public Reparacion(int id, double precio, String matricula, String descripcion,String fecha, Client miclient) {
		super();
		this.id = id;
		this.precio = precio;
		this.matricula = matricula;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.miclient = miclient;
	}
	

	public Reparacion(double precio, String matricula, String descripcion, String fecha, Client miclient) {
		super();
		this.precio = precio;
		this.matricula = matricula;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}


	public Reparacion() {
		this(-1,-1,"","","1-1-2000", new Client());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String date) {		
		this.fecha = date;
	}

	public Client getMiclient() {
		return miclient;
	}

	public void setMiclient(Client miclient) {
		this.miclient = miclient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reparacion [id=" + id + ", precio=" + precio + ", matricula=" + matricula + ", descripcion="
				+ descripcion + ", Fecha=" + fecha+"]";
	}
	
	
	
}

package com.ajaguilar.Taller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ajaguilar.modelo.Client;
import com.ajaguilar.modelo.Reparacion;
import com.ajaguilar.modelo.DAO.ClientDAO;
import com.ajaguilar.modelo.DAO.ReparacionDAO;

import javafx.util.converter.LocalDateStringConverter;

public class Ejecutable {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Reparacion b =new Reparacion(20,"1111 abc","la maletero","2021-05-03",ClientDAO.buscaPorDni("31319229p"));
		ReparacionDAO c=new ReparacionDAO(b);
		System.out.println(c.getMiclient().getDni());
		//c.guardar();
		System.out.println(c);
	}

}

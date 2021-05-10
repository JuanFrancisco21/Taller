package com.ajaguilar.Taller.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;
import com.ajaguilar.Taller.Modelo.DAO.ClientDAO;
import com.ajaguilar.Taller.Modelo.DAO.ReparacionDAO;

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

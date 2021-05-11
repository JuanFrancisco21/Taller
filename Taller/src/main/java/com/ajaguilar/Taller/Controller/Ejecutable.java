package com.ajaguilar.Taller.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Connection;
import com.ajaguilar.Taller.Modelo.Reparacion;
import com.ajaguilar.Taller.Modelo.DAO.ClientDAO;
import com.ajaguilar.Taller.Modelo.DAO.ReparacionDAO;

import javafx.util.converter.LocalDateStringConverter;

public class Ejecutable {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Connection a=new Connection();
		System.out.println(a);
	}

}

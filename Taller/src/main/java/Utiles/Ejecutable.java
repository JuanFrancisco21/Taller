package Utiles;

import com.ajaguilar.Taller.Modelo.Connection;
import com.ajaguilar.Taller.Modelo.DAO.ClientDAO;
import com.ajaguilar.Taller.Modelo.DAO.ReparacionDAO;

public class Ejecutable {

	public static void main(String[] args) {
		ReparacionDAO.getTodasRepas().forEach(item->System.out.println(item.getPrecio()+" "+item.getFecha()));
		
	}

}

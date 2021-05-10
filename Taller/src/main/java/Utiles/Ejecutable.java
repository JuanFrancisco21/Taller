package Utiles;

import com.ajaguilar.modelo.DAO.ClientDAO;

public class Ejecutable {

	public static void main(String[] args) {
		ClientDAO a=new ClientDAO("31009229p");
		System.out.println(a);
	}

}

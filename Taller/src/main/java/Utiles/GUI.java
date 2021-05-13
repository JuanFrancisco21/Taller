package Utiles;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI {
	
	public static Boolean getDate(String fecha) {
		 boolean result = false;
	        Pattern patron = Pattern.compile("[0-9]{4}[-][0-1][0-9][-][0-3][0-9]");
	        Matcher mat = patron.matcher(fecha);
	       
				if(!mat.matches()){
			           mat = patron.matcher(fecha);
			           result=true;
			     }
			
	        
	        return result;
	}
	/**
	 * Validacion de direccion
	 * @param address direccion a validar
	 * @return true si es corecta
	 */
	public static boolean validaAddress(String address) {
		boolean result=false;
		if(address!=null) {
			char l1='C';
			char l2='/';
			char letra=address.charAt(0);
			char letra2=address.charAt(1);
			if(letra==l1||letra2==l2) {
				result=true;
			}
		}
		return result;
	}
	/**
	 * Valida si dni es real
	 * @param dni string a validar
	 * @return true si es valido
	 */
	public static boolean validaDNI(String dni){
		boolean result=false;
		if(dni.length()==9) {
			char letra=dni.charAt(8);
			String n=dni.substring(0, 8);
			Integer num=Integer.parseInt(n);
			if(calcularLetraArray(num)==letra) {
				result=true;
			}
		}
		return result;
	}
	/**
	 * metodo para validar la letra de un dni
	 * @param dni string a validar
	 * @return la letra de supuesto dni
	 */
	private static char calcularLetraArray(int dni){
        char caracteres[] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        int resto = dni%23;
        return caracteres[resto];
   }
	/**
	 * Método para validar una matricula real.
	 * @param matricula a validar.
	 * @return devuelve un booleano.
	 */
	public static boolean validaMatricula(String matricula) {
		boolean result=false;
		if(matricula!=null|| matricula!=" ") {
			Pattern patron = Pattern.compile("[0-9]{4}\s[A-Z]{3}");
			Pattern patron2= Pattern.compile("[0-9]{4}-[A-Z]{3}");
			Pattern patron3= Pattern.compile("[0-9]{4}\s[A-Z]{2}");

	        Matcher mat = patron.matcher(matricula);
	        Matcher mat2 = patron2.matcher(matricula);
	        Matcher mat3 = patron3.matcher(matricula);


	       
				if(mat.matches()){
			           mat = patron.matcher(matricula);
			           result=true;
			     }else if (mat2.matches()) {
			    	 	mat = patron.matcher(matricula);
			    	 	result=true;
				 }else if (mat3.matches()) {
					 	mat = patron.matcher(matricula);
					 	result=true;
				}
		}
		return result;
	}
	/**
	 * Método paara validar si una cadena es decimal.
	 * @param cad a evaluar.
	 * @return devuelve un booleano.
	 */
	public static boolean isDecimal(String cad){
		 try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	 }
	/**
	 * Método para validar si una cadena es numérica.
	 * @param cadena a validar.
	 * @return devuelve un booleano.
	 */
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}


}

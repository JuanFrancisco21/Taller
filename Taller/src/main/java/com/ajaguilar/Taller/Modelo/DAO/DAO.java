package com.ajaguilar.Taller.Modelo.DAO;

public interface DAO {
	int eliminar();
	   /**
	    *Elimina aquellas filas que coincidan con el id.
	    */
	 int guardar();
	   
	   /**
	    * En caso de que id=-1 realiza un INSERT
	    * En caso de que id>0 realiza un UPDATE
	    * Estableciendo en la tabla los valores corrrespondientes a esta instancia
	    */

}

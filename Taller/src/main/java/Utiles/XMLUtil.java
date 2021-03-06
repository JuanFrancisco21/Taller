package Utiles;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ajaguilar.Taller.Modelo.Connection;

import javafx.scene.control.Dialog;

public class XMLUtil {
    public static String file="conn.xml";
    /**
     * Metodo para cargar datos de la conexion del programa con la base de datos de un archivo .xml
     * @return devuelve un objeto Connection
     */
    public static Connection loadDataXML() {
        Connection result=new Connection();       
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(Connection.class);
                Unmarshaller um = context.createUnmarshaller();
                Connection conec = (Connection) um.unmarshal(f);
                result=conec;
            }catch(JAXBException ex){
                ex.printStackTrace();
                result=new Connection();
            }
        }
        return result;
    }
    /**
     * Metodo para guardar datos de la conexion del programa con la base de datos en un archivo .xml
     * @param data Objeto con los datos de la conexion a gurdar
     */
    public static void writeDataXML(Connection data){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Connection.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Connection conec = new Connection();
            m.marshal(data, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            //Dialog.showError("ERROR", "Error reading "+file, ex.toString());
        }
               
    }
}

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
    
    public static Connection loadDataXML() {
        Connection result=new Connection();       
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(Connection.class);
                Unmarshaller um = context.createUnmarshaller();
                Connection conec = (Connection) um.unmarshal(f);
                //result.addAll(conec.getConns());
            }catch(JAXBException ex){
                ex.printStackTrace();
                //Dialog.showError("ERROR", "Error writing "+file, ex.toString());
                result=new Connection();
            }
        }
        return result;
    }
    
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

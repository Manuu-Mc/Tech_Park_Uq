package co.edu.uniquindio.poo.tech_park_uq.controller.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaUtil {

    private PersistenciaUtil() {
        // Clase utilitaria: no instanciable
    }

    public static void guardarArchivo(String ruta, Object objeto){

        try(ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(objeto);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object cargarArchivo(String ruta){

        try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(ruta))) {

            return ois.readObject();

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

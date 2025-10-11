package fantasy_lol.main;

import fantasy_lol.conexion.MySQLConnection;
import fantasy_lol.interfaz.VentanaInicio;

import java.sql.Connection;

/**
 * Clase principal que inicia la aplicación Fantasy LOL.
 * 
 * Se encarga de establecer la conexión con la base de datos
 * y lanzar la ventana de inicio si la conexión es exitosa.
 * 
 * @author Vicente y Gonzalo
 */
public class Main {

    /**
     * Método principal que ejecuta la aplicación.
     * 
     */
    public static void main(String[] args) {
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection conn = mySQLConnection.mySQLConnect();
        
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
            new VentanaInicio();
        } else {
            System.out.println("❌ No se pudo establecer conexión.");
        }
    }
}

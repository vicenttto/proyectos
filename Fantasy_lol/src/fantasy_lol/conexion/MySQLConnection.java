package fantasy_lol.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MySQL.
 * 
 * Permite establecer una conexión con una base de datos utilizando JDBC.
 * 
 * @author Vicente y Gonzalo
 */
public class MySQLConnection {
    
    /**
     * Establece y devuelve una conexión a la base de datos MySQL.
     * 
     * La configuración incluye la URL, el usuario y la contraseña de la base de datos.
     * 
     * @return objeto de conexión si es exitosa, null si falla.
     */
    public Connection mySQLConnect() {
        
        // Datos de conexión
        String url = "jdbc:mysql://localhost:3306/fantasylol"; // URL de la base de datos
        String usuario = "root"; // Usuario de MySQL
        String password = ""; // Contraseña de MySQL

        Connection conexion = null;
        
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
    
        return conexion;
    }
}

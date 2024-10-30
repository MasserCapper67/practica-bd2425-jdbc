package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {

    // @TODO Sistituye xxx por los parámetros de tu conexión
    private final static String DB_SERVER = xxx;
    private final static int DB_PORT = xxx;
    private final static String DB_NAME = xxx;
    private final static String DB_USER = xxx;
    private final static String DB_PASS = xxx;
    private static Connection conn;

    public static void main (String [] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);
    
        // @TODO Prueba sus funciones
        // 1. Añade los planetas a la base de datos
        // 2. Muestra por pantalla la lista de pasajeros de la tierra en criosueño de la cabina 60
        // 3. Muestra por pantalla una lista de sistemas, planetas y los pasajeros con origen en ellos
        
        conn.close();

    }

    private static void nuevoPlaneta (String nombre, double masa, int radio, String sistema) throws SQLException {
        // @TODO Añade planetas a la base de datos

        
    }

    private static void listaPasajerosCabina (int cabina, boolean criosueno, String planeta) throws SQLException {
        // @TODO Muestra por pantalla una lista de pasajeros provinientes del planeta Tierra en criosueño en la cápsula 60
        
    }

    private static void listaOrigenes() throws SQLException {
        // @TODO Muestra por pantalla una lista de planetas, sistemas y pasajeros provinientes de ellos

    }

}
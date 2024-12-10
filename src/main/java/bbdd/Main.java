package bbdd;

import java.sql.*;

public class Main {

    private final static String DB_SERVER = "localhost";
    private final static int DB_PORT = 3306;
    private final static String DB_NAME = "titanic_spaceship";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";
    private static Connection conn;

    public static void main (String [] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

        // 1. Añade los planetas a la base de datos
        nuevoPlaneta("Kepler-186f", 3.3e24, 8800, "Copernico");
        nuevoPlaneta("HD 209458 b (Osiris)", 1.4e27, 100000, "Beta Pictoris");
        nuevoPlaneta("LHS 1140 b", 8.3e24, 8800, "Copernico");
        // 2. Muestra por pantalla la lista de pasajeros de la cabina A-60-S
        listaPasajerosCabina("A", 60, "S");
        // 3. Muestra por pantalla una lista de sistemas, planetas y número de pasajeros con origen en ellos
        listaOrigenes();
        conn.close();
    }

    private static void nuevoPlaneta (String nombre, double masa, int radio, String sistema) throws SQLException {
        String sql = "INSERT INTO planetas (nombre, masa, radio, sistema) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nombre);
        preparedStatement.setDouble(2, masa);
        preparedStatement.setInt(3, radio);
        preparedStatement.setString(4, sistema);
        preparedStatement.executeUpdate();
    }

    private static void listaPasajerosCabina (String cubierta, int cabina, String lado) throws SQLException {
        String sql = "SELECT * FROM pasajeros WHERE pasajeros.cubierta = ? AND pasajeros.numero_cabina = ?" +
                " AND pasajeros.lado_cabina = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, cubierta);
        preparedStatement.setInt(2, cabina);
        preparedStatement.setString(3, lado);

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.printf("%s: %s ", columnName, value);
            }
            System.out.println();
        }
    }

    private static void listaOrigenes() throws SQLException {
        // @TODO Muestra por pantalla una lista de planetas, sistemas y número de pasajeros provinientes de ellos
        String sql = "SELECT sistemas.nombre AS sistema, planetas.nombre AS planeta, count(pasajeros.id) AS habitantes_nativos " +
                "FROM sistemas " +
                "JOIN planetas ON planetas.sistema = sistemas.nombre " +
                "LEFT JOIN pasajeros ON pasajeros.planeta_natal = planetas.nombre AND pasajeros.sistema_natal = sistemas.nombre " +
                "GROUP BY sistemas.nombre, planetas.nombre " +
                "ORDER BY habitantes_nativos DESC";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.printf("%s: %s ", columnName, value);
            }
            System.out.println();
        }
    }
}
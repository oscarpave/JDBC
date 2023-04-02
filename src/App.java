import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB_NAME = "test2";

    public static void main(String[] args) throws Exception {
        // conexion
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "");
            System.out.println("Connection succeed");
        }catch(
        SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Verificación si la base de datos existe
        boolean dbExists = false;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");
        while (rs.next()) {
            String dbName = rs.getString(1);
            if (dbName.equals(DB_NAME)) {
                dbExists = true;
                break;
            }
        }

        // try
        // {
        //     stmt = con.createStatement();
        //     stmt.executeUpdate("drop database if exists test2");
        //     stmt.executeUpdate(sqldb);
        // }catch(
        // SQLException e)
        // {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }finally
        // {
        //     stmt.close();
        // }

        // Creación de la base de datos si no existe
        // creacion de tabla
        if (!dbExists) {
            // String sqldb = "create database test2";
            String createString = "create table crud.users " +
            "(dni varchar(9) NOT NULL," +
            "nombre varchar(20) NOT NULL," +
            "apellido varchar(20) NOT NULL," +
            "segApellido varchar(20) NOT NULL," +
            "empresa varchar(20) NOT NULL," +
            "telefono integer(11) NOT NULL," +
            "email varchar(20) NOT NULL," +
            "PRIMARY KEY (dni))";

            stmt = con.createStatement();
            stmt.executeUpdate(createString);
            System.out.println("Base de datos creada con éxito");
        } else {
            System.out.println("La base de datos ya existe");
        }

        // Cierre de la conexión y los recursos
        rs.close();
        stmt.close();
        con.close();

        CrearCliente.mensajePrincipal();
    }
}

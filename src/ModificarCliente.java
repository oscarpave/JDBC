import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModificarCliente {

    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void modificarCliente(String nif, String nombre, String apellido1, String apellido2, String empresa, String telefono, String correoElectronico) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE clientes SET nombre=?, apellido1=?, apellido2=?, empresa=?, telefono=?, correo_electronico=? WHERE nif=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido1);
            statement.setString(3, apellido2);
            statement.setString(4, empresa);
            statement.setString(5, telefono);
            statement.setString(6, correoElectronico);
            statement.setString(7, nif);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente modificado correctamente.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar cliente: " + ex.getMessage());
        }
    }

}

// ModificarCliente modificador = new ModificarCliente();
// modificador.modificarCliente("12345678A", "Juan", "García", "Pérez", "ACME Inc.", "555-123456", "juan.garcia@acme.com");

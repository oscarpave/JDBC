import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrarCliente {

    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void borrarCliente(String nif) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM clientes WHERE nif=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nif);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente borrado correctamente.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al borrar cliente: " + ex.getMessage());
        }
    }

}

// BorrarCliente borrador = new BorrarCliente();
// borrador.borrarCliente("12345678A");

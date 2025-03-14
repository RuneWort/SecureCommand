import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public boolean login(String username, String password) {
        // Connessione al database
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Query SQL costruita in modo sicuro
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Login avvenuto con successo
                return true;
            } else {
                // Credenziali errate
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Chiusura risorse
            closeResources(conn, pst, rs);
        }
    }

    private void closeResources(Connection conn, PreparedStatement pst, ResultSet rs) {
        // Chiusura di connessioni e statement
    }
}
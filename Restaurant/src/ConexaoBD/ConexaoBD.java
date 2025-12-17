package ConexaoBD;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConexaoBD {
    private static final String url = "jdbc:mysql://localhost:3306/BD_Restaurante";
    private static final String user = "root";
    private static final String password = "Linuxthur2025#";
    private static Connection conn;

    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                // Descomente a linha abaixo se der erro de driver:
                // Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn;
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage());
        }
        return null;
    }
}
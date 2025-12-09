package ConexaoBD;
import java.sql.*;

public class ConexaoBD {
    private static final String url = "jdbc:msql://localhost:3306/BD_Restaurante"; // coloquem o banco de dados de vocês
    private static final String user = "root";
    private static final String password = "Linuxthur2005#";

    public static Connection conn;

    public static Connection getConexao() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao entrar no Banco de dados",e);
        }

    }
}
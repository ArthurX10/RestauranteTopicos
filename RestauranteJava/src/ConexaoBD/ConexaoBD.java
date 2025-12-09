package ConexaoBD;
import java.sql.*;

public class ConexaoBD {
    private static final String url = "jdbc:msql:/seu_local_host//seu_banco_de_dados"; // coloquem o banco de dados de vocês
    private static final String user = "seu_usuario";
    private static final String password = "sua_senha";

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

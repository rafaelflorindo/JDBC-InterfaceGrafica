import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/controleestoque";
            String usuario = "root";
            String senha = "";

            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
}

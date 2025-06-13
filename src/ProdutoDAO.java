import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade, descricao, dataCadastro, dataValidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getDescricao());
            stmt.setDate(5, produto.getDataCadastro());

            if (produto.getDataValidade() != null) {
                stmt.setDate(6, produto.getDataValidade());
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            //stmt.setDate(6, produto.getDataValidade());
            stmt.executeUpdate();

            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setDescricao(rs.getString("descricao"));
                p.setDataCadastro(rs.getDate("dataCadastro"));
                p.setDataValidade(rs.getDate("dataValidade"));
                produtos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ?, descricao = ?, datavalidade = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getDescricao());
            stmt.setDate(5, produto.getDataValidade());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Produto excluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

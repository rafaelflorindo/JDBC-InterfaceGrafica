import javax.xml.crypto.Data;
import java.sql.Date;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private Date dataCadastro;
    private Date dataValidade;

    public Produto() {}

    public Produto(String nome, double preco, int quantidade, String descricao, Date dataCadastro) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() {return quantidade;   }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade;  }

    public String getDescricao() { return descricao;   }
    public void setDescricao(String descricao) { this.descricao = descricao;    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
}

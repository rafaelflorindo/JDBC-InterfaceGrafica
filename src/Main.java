import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        FormCadastroProduto frame = new FormCadastroProduto();
        frame.setVisible(true);
    }
}

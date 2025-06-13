import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //ProdutoDAO dao = new ProdutoDAO();

        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);

        /*TelaMenu telaMenu = new TelaMenu();
        telaMenu.setVisible(true);*/

        /*FormMenuTeste frame1 = new FormMenuTeste();
        frame1.setVisible(true);*/

        /*FormCadastroProduto frame = new FormCadastroProduto();
        frame.setVisible(true);*/

       // Date dataCadastro = Date.valueOf(LocalDate.of(2025, 6, 7));

       /* Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRODUTOS =====");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Data de validade YYYY-MM-DD: ");
                    String dataValidadeString = scanner.nextLine();
                    Date dataValidade = null;

                    if (!dataValidadeString.isBlank()) {
                        try {
                            // Converte string para LocalDate usando o formato dd/MM/yyyy
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data = LocalDate.parse(dataValidadeString, formatter);

                            // Converte LocalDate para java.sql.Date
                            dataValidade = Date.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido! Use dd/MM/yyyy");
                        }
                    }
                    Produto produto = new Produto(nome, preco,quantidade, descricao, dataCadastro);
                    produto.setDataValidade(dataValidade);
                    dao.inserir(produto);
                    break;

                case 2:
                    List<Produto> lista = dao.listarTodos();
                    System.out.println("\n--- Lista de Produtos ---");
                    for (Produto p : lista) {
                        System.out.println(p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco());
                    }
                    break;

                case 3:
                    System.out.print("ID do produto a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer

                    System.out.print("Nome do produto: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String novoDescricao = scanner.nextLine();

                    System.out.print("Quantidade: ");
                    int novoQuantidade = scanner.nextInt();

                    System.out.print("Preço: ");
                    double novoPreco = scanner.nextDouble();

                    Produto atualizado = new Produto(novoNome, novoPreco, novoQuantidade, novoDescricao, dataCadastro);
                    atualizado.setId(idAtualizar);
                    dao.atualizar(atualizado);
                    break;

                case 4:
                    System.out.print("ID do produto a excluir: ");
                    int idExcluir = scanner.nextInt();
                    dao.excluir(idExcluir);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
*/
    }
}

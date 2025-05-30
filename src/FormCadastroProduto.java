import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormCadastroProduto extends JFrame {
    private JButton incluirButton;
    private JButton listarButton;
    private JButton salvarButton;
    private JTextField TF_nome;
    private JTextField TF_preco;
    private JTextField TF_quantidade;
    private JTextArea TA_descricao;
    private JPanel Principal;
    private JPanel JP_Menu;
    private JPanel JP_Formulario;
    private JTable table1;
    private JScrollPane JP_Tabela;
    private JButton excluirButton;
    private JButton editarButton;

    ProdutoDAO dao = new ProdutoDAO();

    DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[]{"ID","Nome", "Preço", "Quantidade", "Descrição"},
            0
    );

    public FormCadastroProduto(){
        setContentPane(Principal);
        table1.setModel(modeloTabela);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("Gerenciador de Produdos");

        habilitarCampos(false);
        LimparCampos();

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagens/add.png"));
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        incluirButton.setIcon(resizedIcon);
        incluirButton.setText("");

        incluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarCampos(true);
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nome = TF_nome.getText().trim();
                    double preco = Double.parseDouble(TF_preco.getText().trim());
                    int quantidade = Integer.parseInt(TF_quantidade.getText().trim());
                    String descricao = TA_descricao.getText();

                    dao.inserir(new Produto(nome, preco, quantidade, descricao));
                    JOptionPane.showMessageDialog(null, "Produto Inserido com Sucesso");

                    if (salvarButton.getText().equals("Atualizar")) {
                        int linhaSelecionada = table1.getSelectedRow();
                        int id = (int) table1.getValueAt(linhaSelecionada, 0);
                        Produto produto = new Produto(nome, preco, quantidade, descricao);
                        produto.setId(id);
                        dao.atualizar(produto);
                        salvarButton.setText("Salvar");
                        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                    } else {
                        dao.inserir(new Produto(nome, preco, quantidade, descricao));
                        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
                    }

                    LimparCampos();
                    atualizarTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para preço e quantidade.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabela.setRowCount(0);
                // Busca os produtos e insere na tabela
                for (Produto p : dao.listarTodos()) {
                    modeloTabela.addRow(new Object[]{
                            p.getId(),
                            p.getNome(),
                            p.getPreco(),
                            p.getQuantidade(),
                            p.getDescricao()
                    });
                }
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table1.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int id = (int) table1.getValueAt(linhaSelecionada, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.excluir(id);
                        atualizarTabela();
                        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table1.getSelectedRow();
                if (linhaSelecionada != -1) {
                    // Preenche os campos com os dados da linha selecionada
                    TF_nome.setText(table1.getValueAt(linhaSelecionada, 1).toString());
                    TF_preco.setText(table1.getValueAt(linhaSelecionada, 2).toString());
                    TF_quantidade.setText(table1.getValueAt(linhaSelecionada, 3).toString());
                    TA_descricao.setText(table1.getValueAt(linhaSelecionada, 4).toString());

                    habilitarCampos(true);
                    salvarButton.setText("Atualizar");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.");
                }
            }
        });
    }
    private void atualizarTabela() {
        List<Produto> lista = dao.listarTodos();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Preço");
        model.addColumn("Quantidade");
        model.addColumn("Descrição");

        for (Produto p : lista) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getDescricao()});
        }

        table1.setModel(model);
    }
    private void LimparCampos() {
        TF_nome.setText("");
        TF_preco.setText("");
        TF_quantidade.setText("");
        TA_descricao.setText("");
    }
    private void habilitarCampos(boolean status) {
        TF_nome.setEnabled(status);
        TF_preco.setEnabled(status);
        TF_quantidade.setEnabled(status);
        TA_descricao.setEnabled(status);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
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
    private JTextField TF_Total;

    ProdutoDAO dao = new ProdutoDAO();

    DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[]{"ID","Nome", "Preço R$", "Quantidade", "Descrição", "Total R$"},
            0
    );

    public FormCadastroProduto(){
        setContentPane(Principal);
        table1.setModel(modeloTabela);

        // Alterar altura
        JTableHeader header = table1.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40)); // Altura de 40 pixels
        header.setFont(new Font("Arial", Font.BOLD, 14));// Alterar fonte
        // (Opcional) Centralizar o texto do cabeçalho
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


        DecimalFormat formato = new DecimalFormat("#,##0.00");


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("Gerenciador de Produdos");

        habilitarCampos(false);
        LimparCampos();

        setIconForButton(incluirButton, "/imagens/new-product.png", 50, 50);
        setIconForButton(salvarButton, "/imagens/diskette.png", 50, 50);
        setIconForButton(editarButton, "/imagens/edit.png", 50, 50);
        setIconForButton(excluirButton, "/imagens/delete.png", 50, 50);
        setIconForButton(listarButton, "/imagens/menu.png", 50, 50);

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

                double total = 0.0;

                DefaultTableCellRenderer alinhamentoDireita = new DefaultTableCellRenderer();
                DefaultTableCellRenderer alinhamentoCentral = new DefaultTableCellRenderer();
                alinhamentoDireita.setHorizontalAlignment(SwingConstants.RIGHT);
                alinhamentoCentral.setHorizontalAlignment(SwingConstants.CENTER);

                table1.getColumnModel().getColumn(0).setCellRenderer(alinhamentoCentral);
                table1.getColumnModel().getColumn(2).setCellRenderer(alinhamentoDireita);
                table1.getColumnModel().getColumn(3).setCellRenderer(alinhamentoCentral);
                table1.getColumnModel().getColumn(5).setCellRenderer(alinhamentoDireita);

                // Busca os produtos e insere na tabela
                for (Produto p : dao.listarTodos()) {
                    modeloTabela.addRow(new Object[]{
                            p.getId(),
                            p.getNome(),
                            String.valueOf(p.getPreco()),
                            p.getQuantidade(),
                            p.getDescricao(),
                            String.valueOf(formato.format(p.getPreco() * p.getQuantidade()))
                    });
                    total += p.getPreco() * p.getQuantidade();
                }

                TF_Total.setText(String.valueOf(formato.format(total)));
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
    private void setIconForButton(JButton button, String imagePath, int width, int height) {
        URL iconURL = getClass().getResource(imagePath);
        if (iconURL != null) {
            ImageIcon originalIcon = new ImageIcon(iconURL);
            Image image = originalIcon.getImage();
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            button.setIcon(resizedIcon);
            button.setText("");
        } else {
            System.err.println("Imagem não encontrada: " + imagePath);
        }
    }
    private void atualizarTabela() {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        List<Produto> lista = dao.listarTodos();
        DefaultTableModel model = new DefaultTableModel();



        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Preço R$");
        model.addColumn("Quantidade");
        model.addColumn("Descrição");
        model.addColumn("Total R$");

        for (Produto p : lista) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getDescricao(), String.valueOf(formato.format(p.getPreco() * p.getQuantidade()))});
        }

        table1.setModel(model);

        DefaultTableCellRenderer alinhamentoDireita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer alinhamentoCentral = new DefaultTableCellRenderer();
        alinhamentoDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        alinhamentoCentral.setHorizontalAlignment(SwingConstants.CENTER);

        table1.getColumnModel().getColumn(0).setCellRenderer(alinhamentoCentral);
        table1.getColumnModel().getColumn(2).setCellRenderer(alinhamentoDireita);
        table1.getColumnModel().getColumn(3).setCellRenderer(alinhamentoCentral);
        table1.getColumnModel().getColumn(5).setCellRenderer(alinhamentoDireita);
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

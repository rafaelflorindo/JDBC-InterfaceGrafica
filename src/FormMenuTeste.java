import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMenuTeste extends JFrame {

    public FormMenuTeste() {
        // Criando a barra de menu
        JMenuBar menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);setBounds(0, 0, 800,600);

        // Criando o menu
        JMenu menuProduto = new JMenu("Produto");
        menuPrincipal.add(menuProduto);

        JMenuItem cadastroProduto = new JMenuItem("Cadastro");
        menuProduto.add(cadastroProduto);

        JMenu menuUsuario = new JMenu("Usuário");
        menuPrincipal.add(menuUsuario);

        JMenuItem cadastroUsuario = new JMenuItem("Cadastro");
        menuUsuario.add(cadastroUsuario);

        cadastroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroProduto frame = new FormCadastroProduto();
                frame.setVisible(true);
            }
        });

        // Configurações básicas da janela
        setTitle("Exemplo de Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
    }

   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormMenuTeste().setVisible(true);
        });
    }*/
}

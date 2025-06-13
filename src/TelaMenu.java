import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame {

    private JPanel Principal;

    public TelaMenu(){
        setContentPane(Principal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setLocationRelativeTo(null);
        setSize(500, 300);

        JMenuBar menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);

        /* MENU USUARIO */
        JMenu menuProduto = new JMenu("Produto");
        menuPrincipal.add(menuProduto);

        JMenuItem cadastroProduto = new JMenuItem("Cadastro");
        menuProduto.add(cadastroProduto);

        /* MENU CADASTRO */
        JMenu menuUsuario = new JMenu("Usuario");
        menuPrincipal.add(menuUsuario);

        JMenuItem cadastroUsuario = new JMenuItem("Cadastro");
        menuUsuario.add(cadastroUsuario);

        /* MENU AJUDA */
        JMenu menuAjuda = new JMenu("Ajuda");
        menuPrincipal.add(menuAjuda);

        /* MENU SAIR */
        JMenu menuSair = new JMenu("Sair");
        menuPrincipal.add(menuSair);

        JMenuItem sair = new JMenuItem("Sair");
        menuSair.add(sair);

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroProduto formProduto = new FormCadastroProduto();
                formProduto.setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TelaPrincipal extends JFrame {
    private JButton produtoButton;
    private JButton sairButton;
    private JButton usuarioButton;
    private JButton ajudaButton;
    private JPanel Principal;

    public TelaPrincipal() {
        setTitle("Exemplo de Menu com Ícones");
        setContentPane(Principal);
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setIconForButton(produtoButton, "/imagens/adicionar-produto.png", 80, 80);
        setIconForButton(usuarioButton, "/imagens/adicionar-usuario.png", 80, 80);
        setIconForButton(ajudaButton, "/imagens/ajuda.png", 80, 80);
        setIconForButton(sairButton, "/imagens/sair.png", 80, 80);

        produtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroProduto formProduto = new FormCadastroProduto();
                formProduto.setVisible(true);
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
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
}

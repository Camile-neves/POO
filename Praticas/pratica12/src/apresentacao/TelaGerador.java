package apresentacao;

import javax.swing.*;
import java.awt.*;
import dados.*;

public class TelaGerador extends JFrame {

    private JTextField campoQtd;
    private JTextArea areaValores;

    public TelaGerador() {
        super("Calculadora Estatística");

        setLayout(new BorderLayout());

        // Painel principal
        JPanel painelPrincipal = new JPanel(new GridLayout(1, 3, 10, 10));

        // Painel central
        JPanel painelCentro = new JPanel(new BorderLayout());
        areaValores = new JTextArea();
        areaValores.setEditable(false);
        painelCentro.setBorder(BorderFactory.createTitledBorder("Valores"));
        painelCentro.add(new JScrollPane(areaValores), BorderLayout.CENTER);

        // Painel da direita
        JPanel painelDireita = new JPanel(new GridLayout(4, 1, 5, 5));
        campoQtd = new JTextField();
        JButton botaoNaturais = new JButton("Naturais");
        JButton botaoPrimos = new JButton("Primos");
        JButton botaoQuadrados = new JButton("Quadrados");

        painelDireita.setBorder(BorderFactory.createTitledBorder("Gerar Valores"));
        painelDireita.add(campoQtd);
        painelDireita.add(botaoNaturais);
        painelDireita.add(botaoPrimos);
        painelDireita.add(botaoQuadrados);
        painelPrincipal.add(painelCentro);
        painelPrincipal.add(painelDireita);

        add(painelPrincipal, BorderLayout.CENTER);

        botaoNaturais.addActionListener(e -> gerarValores(new Naturais()));
        botaoPrimos.addActionListener(e -> gerarValores(new NumerosPrimos()));
        botaoQuadrados.addActionListener(e -> gerarValores(new Quadrados()));

        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void gerarValores(Gerador gerador) {
        try {
            int qtd = Integer.parseInt(campoQtd.getText());
            gerador.gerar(qtd);
            areaValores.setText("");
            for (int val : gerador.getSequencia()) {
                areaValores.append(val + "\n");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaGerador());
    }
}

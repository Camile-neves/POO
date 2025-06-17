package apresentacao;

import dados.Usuario;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private Sistema sistema;

    public TelaLogin(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Sistema de Streaming - Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(8, 15, 10, 2));

        JLabel lblNome = new JLabel("Nome de usuário:");
        JTextField txtNome = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLogin = new JButton("Login");
        JButton btnSair = new JButton("Sair");

        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblSenha);
        painel.add(txtSenha);
        painel.add(new JLabel());  // espaço vazio
        painel.add(btnCadastrar);
        painel.add(btnLogin);
        painel.add(btnSair);

        add(painel);

        // Cadastrar usuário
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword());

            if (nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e senha não podem ser vazios.");
                return;
            }

            int anoAtual = java.time.Year.now().getValue();
            int nascimento = -1;

            while (true) {
                String anoStr = JOptionPane.showInputDialog(this,
                        "Ano de nascimento (entre 1905 e " + anoAtual + "):");
                if (anoStr == null) {
                    return; // cancelou
                }
                try {
                    nascimento = Integer.parseInt(anoStr);
                    if (nascimento >= 1905 && nascimento <= anoAtual) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Ano inválido! Tente novamente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Digite um número válido.");
                }
            }

            Usuario novoUsuario = new Usuario();
            novoUsuario.setId(System.currentTimeMillis()); // ID simples
            novoUsuario.setNome(nome);
            novoUsuario.setSenha(senha);
            novoUsuario.setDataNasc(nascimento);

            sistema.cadastrarUsuario(novoUsuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        });

        // Login
        btnLogin.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword());

            if (nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e senha não podem ser vazios.");
                return;
            }

            boolean sucesso = sistema.autenticarUsuario(nome, senha);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                dispose();  // fecha a tela de login
                TelaPrincipal telaPrincipal = new TelaPrincipal(sistema);
                telaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
            }
        });

        // Sair
        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente sair?", "Confirmar saída",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
    
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin(sistema);
            telaLogin.setVisible(true);
        });
    }
    
}

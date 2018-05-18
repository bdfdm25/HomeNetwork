package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton botaoLogin, cadastroUsuarios;
    JLabel titulo, tituloUsuario, tituloSenha;
    JPanel painelGeral;
    public JTextField nomeUsuario;
    JPasswordField senhaUsuario;
    String usuario, senha;
    FileInputStream arquivoTexto;
    int caractere = 0, i = 0;
    String buffer = "";
    String string = "";

    public Login() {
        //Propriedades da Janela Principal.
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("HomeNetwork - Login de Usuario");
        setBounds(125, 100, 450, 200);
        setResizable(false);

        //Propriedades do Painel.
        painelGeral = new JPanel();
        painelGeral.setLayout(null);
        painelGeral.setBackground(Color.white);
        painelGeral.setBounds(0, 0, 500, 300);

        titulo = new JLabel("HomeNetwork - Painel de Acesso");
        titulo.setBounds(130, 10, 200, 20);

        tituloUsuario = new JLabel("Usuario:");
        tituloUsuario.setBounds(30, 45, 50, 20);
        tituloSenha = new JLabel("Senha:");
        tituloSenha.setBounds(30, 75, 40, 20);

        nomeUsuario = new JTextField("");
        nomeUsuario.setBounds(85, 45, 300, 25);
        senhaUsuario = new JPasswordField("");
        senhaUsuario.setBounds(85, 75, 300, 25);

        botaoLogin = new JButton();
        botaoLogin.setBounds(145, 115, 70, 25);
        botaoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/okbutton.png")));
        botaoLogin.setToolTipText("Confirmar Login e Senha!");
        botaoLogin.setContentAreaFilled(false);
        botaoLogin.setBorder(null);
        botaoLogin.addActionListener(this);
        
        cadastroUsuarios = new JButton();
        cadastroUsuarios.setBounds(235, 115, 70, 25);
        cadastroUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/usersadd.png")));
        cadastroUsuarios.setToolTipText("Cadastrar Novo Usuario");
        cadastroUsuarios.setContentAreaFilled(false);
        cadastroUsuarios.setBorder(null);
        cadastroUsuarios.addActionListener(this);
        
        //adiciona objetos ao Painel;
        painelGeral.add(titulo);
        painelGeral.add(tituloUsuario);
        painelGeral.add(tituloSenha);
        painelGeral.add(nomeUsuario);
        painelGeral.add(senhaUsuario);
        painelGeral.add(botaoLogin);
        painelGeral.add(cadastroUsuarios);

        //adiciona o Painel a Janela Principal;
        getContentPane().add(painelGeral);
    }

    //@SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent evento) {
        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evento.getSource();        
        
        if (objetoRecebeuEvento == cadastroUsuarios) {
            
            Cadastro novoCadastro = new Cadastro("");
            novoCadastro.setVisible(true);
            this.dispose();
        }

        try {


            if (objetoRecebeuEvento == botaoLogin) {
                usuario = nomeUsuario.getText();
                senha = new String(senhaUsuario.getPassword());
                RuntimeExecJava runtime = new RuntimeExecJava(usuario, senha);
                this.dispose();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

                arquivoTexto = new FileInputStream("Acesso.txt");

                buffer = "";

                while (caractere != -1) {
                    caractere = arquivoTexto.read();
                    if (caractere != -1) {
                        buffer += (char) caractere;
                    }

                }

                while (buffer.charAt(i) != ';') {
                    string += buffer.charAt(i);
                    i++;
                }

                if (string.compareTo("Liberado") == 0) {
                    JOptionPane.showMessageDialog(null,"Acesso Liberado!\nUsuario: Admin");
                    Menu carregaMenu = new Menu("1");
                    carregaMenu.setVisible(true);

                }
                if (string.compareTo("Liberado2") == 0) {
                    JOptionPane.showMessageDialog(null,"Acesso Liberado!\nUsuario: Tipo 2");
                    Menu carregaMenu = new Menu("2");
                    carregaMenu.setVisible(true);

                }
                if (string.compareTo("Liberado3") == 0) {
                    JOptionPane.showMessageDialog(null,"Acesso Liberado!\nUsuario: Tipo 3");
                    Menu carregaMenu = new Menu("3");
                    carregaMenu.setVisible(true);

                }
                if (string.compareTo("Liberado4") == 0) {
                    JOptionPane.showMessageDialog(null,"Acesso Liberado!\nUsuario: Tipo 4");
                    Menu carregaMenu = new Menu("4");
                    carregaMenu.setVisible(true);

                }
                if (string.compareTo("NLiberado") == 0) {
                    JOptionPane.showMessageDialog(null,"Acesso Não Liberado!\nUsuario ou Senha Inválidos!");
                    Login login = new Login();
                    login.setVisible(true);
                }

            }
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
        }
    }

    public static void main(String argumentos[]) {
        JFrame novoLogin = new Login();
        novoLogin.setVisible(true);
    }

    public class RuntimeExecJava {

        public RuntimeExecJava(String usuario, String senha) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start liberarAcesso.exe " + usuario + " " + senha;

            try {
                Runtime.getRuntime().exec(linhaComando);
            } catch (IOException erro) {
            }
        }
    }
}

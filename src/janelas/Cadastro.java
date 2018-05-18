package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Cadastro extends JFrame implements ActionListener{

    JPanel painelCadastro;
    JLabel usuario, senha, tipo, titulo;
    JTextField nomeUsuario, tipoUsuario;
    JPasswordField senhaUsuario;
    JButton cadastra, limpa, voltar;
    String user, pwd, type, id;
    File usuariosCadastrados ;
    long codigo;
    public String tipoUser;
    
    public Cadastro(String tipoU){
        
        tipoUser = tipoU;
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Cadastro");
        setBounds(100, 100, 340, 360);
        setResizable(false);
        //Criaçao do Painel que recebera os campos de cadastro;
        painelCadastro = new JPanel();
        painelCadastro.setLayout(null);
        painelCadastro.setBackground(Color.white);
        painelCadastro.setBounds(0, 0, 400, 400);
        
        //titulo do painel;
        titulo = new JLabel("==NOVO USUÁRIO==");
        titulo.setBounds(100, 20, 150, 25);
        
        //campos de cadastro;
        usuario = new JLabel("Usuário:");
        usuario.setBounds(20,80,50,20);
        senha = new JLabel("Senha:");
        senha.setBounds(20,110,50,20);
        
        nomeUsuario = new JTextField();
        nomeUsuario.setBounds(75,80,200,20);
        senhaUsuario = new JPasswordField();
        senhaUsuario.setBounds(75,110,200,20);
        
        cadastra = new JButton("");
        cadastra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png")));
        cadastra.setBounds(75,140,50,20);
        cadastra.addActionListener(this);
        
        voltar = new JButton("");
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        voltar.setBounds(150,140,50,20);
        voltar.addActionListener(this);
        
        limpa = new JButton("");
        limpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eraser.png")));
        limpa.setBounds(225,140,50,20);
        limpa.addActionListener(this);
        
        //adiciona objetos ao painel;
        painelCadastro.add(titulo);
        painelCadastro.add(usuario);
        painelCadastro.add(senha);
        painelCadastro.add(nomeUsuario);
        painelCadastro.add(senhaUsuario);
        painelCadastro.add(cadastra);
        painelCadastro.add(limpa);
        painelCadastro.add(voltar);
        
        //adiciona o painel a janela principal;
        getContentPane().add(painelCadastro);
        
    }
        
    public void actionPerformed(ActionEvent evt) {
       
       usuariosCadastrados = new File("usuarios.dad");
        
        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evt.getSource();

        if (objetoRecebeuEvento == limpa){ 
            nomeUsuario.setText(null);
            senhaUsuario.setText(null);
        }
        
        if (objetoRecebeuEvento == cadastra){
            
            codigo = usuariosCadastrados.length()/44;
            id = String.valueOf(codigo);
            user = nomeUsuario.getText();
            System.out.println(""+user);
            pwd = new String(senhaUsuario.getPassword());
            System.out.println(""+pwd);
            
            RuntimeExecJava runtime = new RuntimeExecJava(id, user, pwd);
            JOptionPane.showMessageDialog(null,"Cadastro Realizado com Sucesso!");
        }
        
        if(objetoRecebeuEvento == voltar){
            if(tipoUser.equals("")){
                Login voltarLogin = new Login();
                voltarLogin.setVisible(true);
                this.dispose();
            }else{
                GerenciaUsuarios voltarMenu = new GerenciaUsuarios(tipoUser);
                voltarMenu.setVisible(true);
                this.dispose();
            }
        }
    }
    
    public class RuntimeExecJava {

        public RuntimeExecJava(String id, String user, String pwd) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start Cadastro.exe " +id+ " " + user + " " + pwd;

            try {
                Runtime.getRuntime().exec(linhaComando);
            } catch (IOException erro) {
            }
        }
    }
    
     public static void main(String argumentos[]){
        Cadastro janelaCad = new Cadastro(argumentos[0]);
        janelaCad.setVisible(true);
        
    }
}

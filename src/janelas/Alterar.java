package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


    
    

public class Alterar extends JFrame implements ActionListener {

    JPanel painelAlterar;
    JLabel usuario, tipo, codigo, titulo, senha;
    JTextField nomeUsuario, codUsuario, tipoUsuario, senhaUsuario;
    JButton salvar, limpa, voltar, ok;
    public String cod, user, type, pwd,tipoUser, buffer = "";
    public int x = 0, caractere = 0;
    FileInputStream arquivoTexto;
    String[] dados;

    public Alterar(String tipoU) {

        tipoUser = tipoU;
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Cadastro");
        setBounds(100, 100, 340, 360);
        setResizable(false);
        //Criaçao do Painel que recebera os campos de cadastro;
        painelAlterar = new JPanel();
        painelAlterar.setLayout(null);
        painelAlterar.setBackground(Color.white);
        painelAlterar.setBounds(0, 0, 400, 400);

        //titulo do painel;
        titulo = new JLabel("==ALTERAR DADOS==");
        titulo.setBounds(75, 20, 150, 25);

        //campos de cadastro;
        codigo = new JLabel("Código:");
        codigo.setBounds(20, 80, 50, 20);
        usuario = new JLabel("Usuario:");
        usuario.setBounds(20, 110, 50, 20);
        usuario.setEnabled(false);
        senha = new JLabel("Senha:");
        senha.setBounds(20, 140, 50, 20);
        senha.setEnabled(false);
        tipo = new JLabel("Tipo:");
        tipo.setBounds(20, 170, 50, 20);
        tipo.setEnabled(false);

        codUsuario = new JTextField();
        codUsuario.setBounds(75, 80, 80, 20);
        nomeUsuario = new JTextField();
        nomeUsuario.setBounds(75, 110, 200, 20);
        nomeUsuario.setEnabled(false);
        senhaUsuario = new JTextField();
        senhaUsuario.setBounds(75, 140, 200, 20);
        senhaUsuario.setEnabled(false);
        tipoUsuario = new JTextField();
        tipoUsuario.setBounds(75, 170, 200, 20);
        tipoUsuario.setEnabled(false);

        ok = new JButton("");
        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/okbutton.png")));
        ok.setBounds(160, 80, 50, 20);
        ok.addActionListener(this);

        salvar = new JButton("");
        salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png")));
        salvar.setBounds(75, 210, 50, 20);
        //salvar.setContentAreaFilled(false);
        //salvar.setBorder(null);
        salvar.addActionListener(this);

        voltar = new JButton("");
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        voltar.setBounds(150, 210, 50, 20);
        voltar.addActionListener(this);

        limpa = new JButton("");
        limpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eraser.png")));
        limpa.setBounds(225, 210, 50, 20);
        //limpa.setContentAreaFilled(false);
        //limpa.setBorder(null);
        limpa.addActionListener(this);

        //adiciona objetos ao painel;
        painelAlterar.add(titulo);
        painelAlterar.add(codigo);
        painelAlterar.add(codUsuario);
        painelAlterar.add(usuario);
        painelAlterar.add(nomeUsuario);
        painelAlterar.add(senha);
        painelAlterar.add(senhaUsuario);
        painelAlterar.add(tipo);
        painelAlterar.add(tipoUsuario);
        painelAlterar.add(salvar);
        painelAlterar.add(limpa);
        painelAlterar.add(voltar);
        painelAlterar.add(ok);

        //adiciona o painel a janela principal;
        getContentPane().add(painelAlterar);

    }

    public void actionPerformed(ActionEvent evt) {
        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evt.getSource();

        if (objetoRecebeuEvento == limpa) {
            codUsuario.setText(null);
            nomeUsuario.setText(null);
            tipoUsuario.setText(null);
            senhaUsuario.setText(null);
        }
        if (objetoRecebeuEvento == voltar) {
            GerenciaUsuarios voltarMenu = new GerenciaUsuarios(tipoUser);
            voltarMenu.setVisible(true);
            this.dispose();
        }

        if (objetoRecebeuEvento == ok) {
            x++;
            cod = codUsuario.getText();
            RuntimeExecJava runtime = new RuntimeExecJava(cod);
            
            try {
                Thread.sleep(5000);
               
                    arquivoTexto = new FileInputStream("Consulta.txt");
                    dados = new String[4];

                    while (caractere != -1) //o -1 indica o fim de arquivo
                    {
                        caractere = arquivoTexto.read(); //read() é igual o fgetc em C
                        
                        if (caractere != -1) {
                            buffer += (char) caractere; //concatenando e convertendo os caracteres, que sao lidos em codigo ASCII, do arquivo texto.
                            
                        }
                    }
                    
                    dados = buffer.split(";");
                    usuario.setEnabled(true);
                    senha.setEnabled(true);
                    tipo.setEnabled(true);
                    nomeUsuario.setEnabled(true);
                    senhaUsuario.setEnabled(true);
                    tipoUsuario.setEnabled(true);
                    nomeUsuario.setText(dados[0]);
                    senhaUsuario.setText(dados[1]);
                    tipoUsuario.setText(dados[2]);            
                    
                } catch (FileNotFoundException erro) {
                    System.out.println("Arquivo não encontrado");
                } catch (IOException erro) {
                    System.out.println("Erro ao abrir o arquivo");                
                } catch (InterruptedException ex) {
                Logger.getLogger(Exclui.class.getName()).log(Level.SEVERE, null, ex);
                }


            JOptionPane.showMessageDialog(null,"Código Encontrado!");
        }

        if (objetoRecebeuEvento == salvar) {
            x--;
            cod = codUsuario.getText();
            user = nomeUsuario.getText();
            pwd = senhaUsuario.getText();
            type = tipoUsuario.getText();
            RuntimeExecJava runtime = new RuntimeExecJava(cod,user,pwd,type);
            JOptionPane.showMessageDialog(null,"Dados Alterados com Sucesso!");
        }

    }

    public class RuntimeExecJava {

        public RuntimeExecJava(String cod) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start consulta.exe " + cod;

            try {               
                
                Runtime.getRuntime().exec(linhaComando);                

            } catch (IOException erro) {
            }
        }
        
         public RuntimeExecJava(String cod, String user, String pwd, String type) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start alterarRegistros.exe " +cod+ " " + user + " " + pwd + " " + type;

            try {               
                
                Runtime.getRuntime().exec(linhaComando);                

            } catch (IOException erro) {
            }
        }
    }

    public static void main(String argumentos[]) {
        Alterar janelaAltera = new Alterar(argumentos[0]);
        janelaAltera.setVisible(true);

    }
}
    


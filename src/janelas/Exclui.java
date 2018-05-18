package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Exclui extends JFrame implements ActionListener {

    JPanel painelExclui;
    JLabel usuario, tipo, codigo, titulo;
    JTextField nomeUsuario, codUsuario, tipoUsuario;
    JButton exclui, limpa, voltar, ok;
    public String cod, user, type, tipoUser, buffer = "";
    public int x = 0, caractere = 0;
    FileInputStream arquivoTexto;
    String[] dados;

    public Exclui(String tipoU) {

        tipoUser = tipoU;
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Cadastro");
        setBounds(100, 100, 340, 360);
        setResizable(false);
        //Criaçao do Painel que recebera os campos de cadastro;
        painelExclui = new JPanel();
        painelExclui.setLayout(null);
        painelExclui.setBackground(Color.white);
        painelExclui.setBounds(0, 0, 400, 400);

        //titulo do painel;
        titulo = new JLabel("==EXCLUINDO USUARIO==");
        titulo.setBounds(75, 20, 150, 25);

        //campos de cadastro;
        codigo = new JLabel("Código:");
        codigo.setBounds(20, 80, 50, 20);
        usuario = new JLabel("Usuario:");
        usuario.setBounds(20, 110, 50, 20);
        usuario.setEnabled(false);
        tipo = new JLabel("Tipo:");
        tipo.setBounds(20, 140, 50, 20);
        tipo.setEnabled(false);

        codUsuario = new JTextField();
        codUsuario.setBounds(75, 80, 80, 20);
        nomeUsuario = new JTextField();
        nomeUsuario.setBounds(75, 110, 200, 20);
        nomeUsuario.setEnabled(false);
        tipoUsuario = new JTextField();
        tipoUsuario.setBounds(75, 140, 200, 20);
        tipoUsuario.setEnabled(false);

        ok = new JButton("");
        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/okbutton.png")));
        ok.setBounds(160, 80, 50, 20);
        ok.addActionListener(this);

        exclui = new JButton("");
        exclui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/exclui.png")));
        exclui.setBounds(75, 180, 50, 20);
        //exclui.setContentAreaFilled(false);
        //exclui.setBorder(null);
        exclui.addActionListener(this);

        voltar = new JButton("");
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        voltar.setBounds(150, 180, 50, 20);
        voltar.addActionListener(this);

        limpa = new JButton("");
        limpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eraser.png")));
        limpa.setBounds(225, 180, 50, 20);
        //limpa.setContentAreaFilled(false);
        //limpa.setBorder(null);
        limpa.addActionListener(this);

        //adiciona objetos ao painel;
        painelExclui.add(titulo);
        painelExclui.add(codigo);
        painelExclui.add(codUsuario);
        painelExclui.add(usuario);
        painelExclui.add(nomeUsuario);
        painelExclui.add(tipo);
        painelExclui.add(tipoUsuario);
        painelExclui.add(exclui);
        painelExclui.add(limpa);
        painelExclui.add(voltar);
        painelExclui.add(ok);

        //adiciona o painel a janela principal;
        getContentPane().add(painelExclui);

    }

    public void actionPerformed(ActionEvent evt) {
        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evt.getSource();

        if (objetoRecebeuEvento == limpa) {
            codUsuario.setText(null);
            nomeUsuario.setText(null);
            tipoUsuario.setText(null);
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
                    dados = new String[3];

                    while (caractere != -1) //o -1 indica o fim de arquivo
                    {
                        caractere = arquivoTexto.read(); //read() é igual o fgetc em C
                        
                        if (caractere != -1) {
                            buffer += (char) caractere; //concatenando e convertendo os caracteres, que sao lidos em codigo ASCII, do arquivo texto.
                            
                        }
                    }
                    
                    dados = buffer.split(";");
                    usuario.setEnabled(true);
                    tipo.setEnabled(true);
                    nomeUsuario.setEnabled(true);
                    tipoUsuario.setEnabled(true);
                    nomeUsuario.setText(dados[0]);
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

        if (objetoRecebeuEvento == exclui) {
            x--;
            cod = codUsuario.getText();
            RuntimeExecJava runtime = new RuntimeExecJava(cod);

        }

    }

    public class RuntimeExecJava {

        public RuntimeExecJava(String cod) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start exclui.exe " + cod;
            String linhaComando2 = "cmd.exe /C start consulta.exe " + cod;

            try {

                if (x > 0) {
                    Runtime.getRuntime().exec(linhaComando2);
                } else {
                    Runtime.getRuntime().exec(linhaComando);
                }

            } catch (IOException erro) {
            }


        }
    }

    public static void main(String argumentos[]) {
        Exclui janelaExclui = new Exclui(argumentos[0]);
        janelaExclui.setVisible(true);

    }
}
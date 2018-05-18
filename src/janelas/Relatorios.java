package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;

public class Relatorios extends JFrame implements ActionListener {

    JPanel painelGeral;
    JLabel titulo;
    JButton maiorConsumo, botaoUsuarios, botaoVoltar, logOff;
    public String tipoUser, random;
    int cont = 0;
    int[] lista = new int[20];
    //String[] random = new String[20];

    public Relatorios(String tipo) {

        tipoUser = tipo;
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Relatórios");
        setBounds(100, 100, 290, 275);
        setResizable(false);

        //Criação do Painel;
        painelGeral = new JPanel();
        painelGeral.setLayout(null);
        painelGeral.setBackground(Color.white);
        painelGeral.setBounds(0, 0, 400, 300);

        //titulo da janela;
        titulo = new JLabel("===RELATÓRIOS===");
        titulo.setBounds(90, 20, 150, 25);

        //Criação dos botoes;
        botaoUsuarios = new JButton("Usuarios Cadastrados");
        botaoUsuarios.setBounds(65, 50, 165, 25);
        botaoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/users.png")));
        botaoUsuarios.addActionListener(this);
        botaoUsuarios.setContentAreaFilled(false);
        botaoUsuarios.setBorder(null);

        maiorConsumo = new JButton("Consumo Medio");
        maiorConsumo.setBounds(65, 90, 165, 25);
        maiorConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/relatorioConsumo.png")));
        maiorConsumo.setContentAreaFilled(false);
        maiorConsumo.setBorder(null);
        maiorConsumo.addActionListener(this);

        logOff = new JButton("LogOff");
        logOff.setBounds(150, 190, 110, 25);
        logOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logOff.png")));
        logOff.setContentAreaFilled(false);
        logOff.setBorder(null);
        logOff.addActionListener(this);

        botaoVoltar = new JButton("");
        botaoVoltar.setBounds(20, 190, 70, 25);
        botaoVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setBorder(null);
        botaoVoltar.addActionListener(this);

        //Adiciona objetos ao Painel;
        painelGeral.add(titulo);
        painelGeral.add(maiorConsumo);
        painelGeral.add(botaoUsuarios);
        painelGeral.add(botaoVoltar);
        painelGeral.add(logOff);


        //Adiciona Painel a Janela Principal;
        getContentPane().add(painelGeral);
    }

    public void botaoUsuariosActionPerformed(ActionEvent evt) {
    }

    public void consumoMedioActionPerformed(ActionEvent evt) {
        File file = new File("consumoMedio.txt");
        //String conteudo;

        try {
            FileWriter f = new FileWriter(file, false);

            for (cont = 0; cont < 20; cont++) {
                lista[cont] = (int) (1 + (Math.random() * 20));
                System.out.println(lista[cont]);
                random = String.valueOf(lista[cont]);
                //random += System.getProperty("line.separator");
                f.write(cont +": "+ random + "Kw;" + System.getProperty("line.separator"));
                
                
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void botaoVoltarActionPerformed(ActionEvent evt) {
        Menu voltarMenu = new Menu(tipoUser);
        voltarMenu.setVisible(true);
        this.dispose();
    }

    public void logOffActionPerformed(ActionEvent evt) {
        Login novoLogin = new Login();
        novoLogin.setVisible(true);
        this.dispose();
    }

    public void actionPerformed(ActionEvent evt) {
        Object evento;
        evento = evt.getSource();

        if (evento == botaoVoltar) {
            botaoVoltarActionPerformed(evt);
        }
        if (evento == botaoUsuarios) {
            botaoUsuariosActionPerformed(evt);
        }
        if (evento == maiorConsumo) {
            consumoMedioActionPerformed(evt);
            JOptionPane.showMessageDialog(null,"Relatório Gerado com Sucesso!");

        }

        if (evento == logOff) {
            logOffActionPerformed(evt);
        }

    }

    public class RuntimeExecJava {

        public RuntimeExecJava(String random) {
            Runtime processoExecutor = Runtime.getRuntime();
            String linhaComando = "cmd.exe /C start maiorConsumo.exe " + random;

            try {
                Runtime.getRuntime().exec(linhaComando);
            } catch (IOException erro) {
            }
        }
    }

    public static void main(String argumentos[]) {
        Relatorios relatorio = new Relatorios(argumentos[0]);
        relatorio.setVisible(true);


    }
}

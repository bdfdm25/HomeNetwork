package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    JPanel painelGeral;
    JLabel titulo;
    JButton botaoPlanta, botaoRelatorios, botaoUsuarios, botaoInfo, botaoSair, logOff;
    public String tipoUser;
    
    public Menu(String tipo) {
        
        tipoUser = tipo;
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Menu Principal");
        setBounds(100, 100, 340, 275);
        setResizable(false);

        //Criação do Painel;
        painelGeral = new JPanel();
        painelGeral.setLayout(null);
        painelGeral.setBackground(Color.white);
        painelGeral.setBounds(0, 0, 400, 300);

        //titulo da janela;
        titulo = new JLabel("MENU PRINCIPAL");
        titulo.setBounds(115, 20, 150, 25);

        //Criação dos botoes;
        botaoPlanta = new JButton("Carregar Planta de Equipamentos");
        botaoPlanta.setBounds(45, 50, 245, 25);
        botaoPlanta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/home.png")));
        botaoPlanta.setContentAreaFilled(false);
        botaoPlanta.setBorder(null);
        botaoPlanta.addActionListener(this);

        botaoUsuarios = new JButton("Gerenciar Usuarios");
        botaoUsuarios.setBounds(80, 90, 165, 25);
        botaoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/users.png")));
        botaoUsuarios.addActionListener(this);
        botaoUsuarios.setContentAreaFilled(false);
        botaoUsuarios.setBorder(null);
        
        botaoRelatorios = new JButton("Emitir Relatórios");
        botaoRelatorios.setBounds(83, 130, 150, 25);
        botaoRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/relatorio.png")));
        botaoRelatorios.setContentAreaFilled(false);
        botaoRelatorios.setBorder(null);
        botaoRelatorios.addActionListener(this);
        
        logOff = new JButton("LogOff");
        logOff.setBounds(170, 190, 150, 25);
        logOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logOff.png")));
        logOff.setContentAreaFilled(false);
        logOff.setBorder(null);
        logOff.addActionListener(this);
        
        botaoInfo = new JButton("Informações");
        botaoInfo.setBounds(15, 190, 150, 25);
        botaoInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/info.png")));
        botaoInfo.setContentAreaFilled(false);
        botaoInfo.setBorder(null);
        botaoInfo.addActionListener(this);       

        //Adiciona objetos ao Painel;
        painelGeral.add(titulo);
        painelGeral.add(botaoPlanta);
        painelGeral.add(botaoRelatorios);
        painelGeral.add(botaoUsuarios);
        painelGeral.add(botaoInfo);
        painelGeral.add(logOff);
       
        

        //Adiciona Painel a Janela Principal;
        getContentPane().add(painelGeral);
        
        if(!tipoUser.equals("1")){
            botaoUsuarios.setEnabled(false);
            botaoRelatorios.setEnabled(false);
            
        }

    }

    
    public void actionPerformed(ActionEvent evento) {

        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evento.getSource();
        
        if (objetoRecebeuEvento == logOff) {
                Login novoLogin = new Login();
                novoLogin.setVisible(true);
                this.dispose();               
                
        }

        if (objetoRecebeuEvento == botaoPlanta){ 
            
            if(tipoUser.equals("1")){
                Tela planta = new Tela();
                planta.setVisible(true);
                this.dispose();
            }
            if(tipoUser.equals("2")){
                Tela2 planta = new Tela2();
                planta.setVisible(true);
                this.dispose();
            }
            if(tipoUser.equals("3")){
                Tela3 planta = new Tela3();
                planta.setVisible(true);
                this.dispose();
            }
            if(tipoUser.equals("4")){
                Tela4 planta = new Tela4();
                planta.setVisible(true);
                this.dispose();
            }
        }
        
        if (objetoRecebeuEvento == botaoUsuarios){ 
            GerenciaUsuarios usuario = new GerenciaUsuarios(tipoUser);
            usuario.setVisible(true);
            this.dispose();
        }

        if (objetoRecebeuEvento == botaoRelatorios){
            Relatorios novoRelatorio = new Relatorios(tipoUser);
            novoRelatorio.setVisible(true);
            this.dispose();
        }
        
        if (objetoRecebeuEvento == botaoInfo) {
            JFrame novo = new JFrame("Info:");
            novo.setBounds(100, 100, 200, 200);
            novo.setVisible(true);
            JLabel info = new JLabel("Texto Texto2");
            info.setBounds(70, 70, 70, 70);
            info.setVisible(true);
            novo.add(info);
        }
       
    }
    
     public static void main(String argumentos[]) {

        Menu menuPrincipal = new Menu(argumentos[0]);
        menuPrincipal.setVisible(true);
        //novaJanela.EXIT_ON_CLOSE;

    }
}
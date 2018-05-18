package janelas;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class GerenciaUsuarios extends JFrame implements ActionListener {
        
    JPanel painelGeral;
    JLabel titulo;
    JButton botaoInclui, botaoAlterar, botaoExclui, voltar;
    public String tipoUser;

    public GerenciaUsuarios(String tipo){

        tipoUser = tipo;        
        //Janela Principal;
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Gerenciamento de Usuarios");
        setBounds(100, 100, 390, 300);
        setResizable(false);

        //Criação do Painel;
        painelGeral = new JPanel();
        painelGeral.setLayout(null);
        painelGeral.setBackground(Color.white);
        painelGeral.setBounds(0, 0, 400, 300);

        //titulo do painel;
        titulo = new JLabel("==USUÁRIOS==");
        titulo.setBounds(155, 20, 150, 25);

        //Criação dos botoes;
        botaoInclui = new JButton("Incluir Usuarios");
        botaoInclui.setBounds(90, 50, 200, 25);
        botaoInclui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/usersadd.png")));
        botaoInclui.addActionListener(this);

        botaoAlterar = new JButton("Alterar Registros");
        botaoAlterar.setBounds(90, 90, 200, 25);
        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/atualiza.png")));
        botaoAlterar.addActionListener(this);
        
        botaoExclui = new JButton("Excluir Registro");
        botaoExclui.setBounds(90, 130, 200, 25);
        botaoExclui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/usersoff.png")));
        botaoExclui.addActionListener(this);
        
        voltar = new JButton("");
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        voltar.setBounds(170,210,50,20);
        voltar.addActionListener(this);

        //Adiciona objetos ao Painel;
        painelGeral.add(titulo);
        painelGeral.add(botaoInclui);
        painelGeral.add(botaoAlterar);
        painelGeral.add(botaoExclui);
        painelGeral.add(voltar);
        
        //Adiciona Painel a Janela Principal;
        getContentPane().add(painelGeral);

    }
    
    public void botaoAlterarActionPerformed(ActionEvent evt){
            Alterar novoAlterar = new Alterar(tipoUser);
            novoAlterar.setVisible(true);
            this.dispose();
    }
    
    public void botaoExcluiActionPerformed(ActionEvent evt){
       
        Exclui novaExclusao = new Exclui(tipoUser);
        novaExclusao.setVisible(true);
        this.dispose();
        
    }
    
    public void botaoIncluiActionPerformed(ActionEvent evt){
   
        Cadastro novoCadastro = new Cadastro(tipoUser);
        novoCadastro.setVisible(true);
        this.dispose();
    }
    
    
    public void actionPerformed(ActionEvent evt){
        Object evento;
        evento = evt.getSource();
        
        if(evento == voltar){
            Menu voltarMenu = new Menu(tipoUser);
            voltarMenu.setVisible(true);
            this.dispose();
        }
        if(evento == botaoInclui){
            botaoIncluiActionPerformed(evt);
        }
        if(evento == botaoExclui){
            botaoExcluiActionPerformed(evt);
        }
        
        if(evento == botaoAlterar){
            botaoAlterarActionPerformed(evt);
        }
        
    }
    
    public static void main(String argumentos[]){
        GerenciaUsuarios menu = new GerenciaUsuarios(argumentos[0]);
        menu.setVisible(true);
    }

}

package janelas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//PenDrive
public class Tela extends JFrame implements ActionListener {

    //objetos
    JPanel novoPainel;
    JLabel plantaCasa;
    JButton salvar, onOff, logOff, voltar;
    FileInputStream arquivoTexto;
    File arquivo;
    public int caractere = 0, i = 3, j = 0, k = 0, x = 0, y = 0, d = 0, contVetor = 0, contEquip = 0;
    String buffer = "";
    String coordenada[], coodX[], coodY[], dados[];
    JRadioButton equip[] = new JRadioButton[20];
    JRadioButton testeButton;
    boolean verificaEquip, testaVerificacao;
    

    public Tela() {
        
        
        //Janela Principal
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.darkGray);
        setTitle("Home NetWork");
        setBounds(100, 100, 700, 450);
        setResizable(false);

        //Criação do Painel
        novoPainel = new JPanel();
        novoPainel.setLayout(null);
        novoPainel.setBackground(Color.white);
        novoPainel.setBounds(0, 0, 800, 600);

        //Tratamento do Painel
        plantaCasa = new JLabel();
        plantaCasa.setBounds(10, 10, 516, 405);
        plantaCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/plantacasa.jpg")));
        plantaCasa.setToolTipText("Planta da Residência");
        
        onOff = new JButton("ON/OFF");
        onOff.setBounds(530, 90, 170, 25);
        onOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/onOff.png")));
        onOff.setContentAreaFilled(false);
        onOff.setBorder(null);
        onOff.addActionListener(this);        
        
        salvar = new JButton("Save");
        salvar.setBounds(530, 120, 170, 25);
        salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png")));
        salvar.setContentAreaFilled(false);
        salvar.setBorder(null);
        salvar.addActionListener(this);
        
        logOff = new JButton("Logoff");
        logOff.setBounds(590, 380, 110, 25);
        logOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logOff.png")));
        logOff.setContentAreaFilled(false);
        logOff.setBorder(null);
        logOff.addActionListener(this);
        
        voltar = new JButton("");
        voltar.setBounds(530, 383, 50, 20);
        voltar.addActionListener(this);
        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/voltar.png")));
        voltar.setContentAreaFilled(false);
        voltar.setBorder(null);
        
        //adiciona objetos ao painel
        novoPainel.add(salvar);
        novoPainel.add(onOff);
        novoPainel.add(logOff);
        novoPainel.add(plantaCasa);
        novoPainel.add(voltar);

        //adiciona painel a janela principal
        getContentPane().add(novoPainel);

        coodX = new String[20];
        coodY = new String[20];
        coordenada = new String[40];
        dados = new String[100];

        try {
            arquivoTexto = new FileInputStream("dadosEquip.txt");
            buffer = "";

            while (caractere != -1) //o -1 indica o fim de arquivo
            {
                caractere = arquivoTexto.read(); //read() é igual o fgetc em C
                if (caractere != -1) {
                    buffer += (char) caractere; //concatenando e convertendo os caracteres, que sao lidos em codigo ASCII, do arquivo texto.
                }
            }


            arquivoTexto.close();

            coordenada = buffer.split(";");
            dados = buffer.split(";");
            while (i < 100) {

                coodX[j] = coordenada[i];

                i++;

                coodX[j] = coodX[j].trim();

                x = Integer.parseInt(coodX[j]);
                //System.out.println("Cood X" + x);
                j++;
                coodY[k] = coordenada[i];


                i = i + 4;

                coodY[k] = coodY[k].trim();

                y = Integer.parseInt(coodY[k]);
                //System.out.println("Cood Y" + y);
                k++;


                equip[contEquip] = new JRadioButton(dados[d]);
                equip[contEquip].setBounds(x, y, 60, 15);
                equip[contEquip].setContentAreaFilled(false);
                equip[contEquip].setToolTipText("Id: " + dados[d] + " Ligado: " + dados[d + 1] + " Nome: " + dados[d + 2]);

                if (dados[d + 1].equals("0")) {
                    equip[contEquip].setForeground(Color.red);
                }
                if (dados[d + 1].equals("1")) {
                    equip[contEquip].setForeground(Color.green);
                }


                novoPainel.add(equip[contEquip]);
                contEquip++;
                d = d + 5;
            }

            novoPainel.add(plantaCasa);
            repaint();
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
        }
    }

    public void actionPerformed(ActionEvent evento) {
        Object objetoRecebeuEvento;
        objetoRecebeuEvento = evento.getSource();

        if(objetoRecebeuEvento == voltar){
            Menu voltarMenu = new Menu("1");
            voltarMenu.setVisible(true);
            this.dispose();
        }
        
        if (objetoRecebeuEvento == salvar) {
            File file = new File("dadosequip.txt");
            String conteudo;
             
            try{  
                FileWriter f = new FileWriter (file, false);  

                for(contVetor = 0; contVetor < dados.length; contVetor++){
                    
                    conteudo = dados[contVetor];
                    f.write(conteudo+";");
                    
                }  

                f.close();  
  
            }catch (IOException e)  {  
                e.printStackTrace();  
            }  
            JOptionPane.showMessageDialog(null,"Dados salvos com Sucesso!");
        }

        
        if(objetoRecebeuEvento == onOff){ 
            //FileWriter arquivo;
            //dados = buffer.split(";");
            d=1;
            for(contEquip = 0;contEquip<20; contEquip++){ 
                 if(equip[contEquip].isSelected() && equip[contEquip].getForeground() == Color.red){
                    System.out.println("Desligado, equipamento selecionado");
                    equip[contEquip].setForeground(Color.green);
                    
                    dados[d] = "1";                    
                   
                    
                 } else if(equip[contEquip].isSelected() && equip[contEquip].getForeground() == Color.green){ 
                     System.out.println("Ligado, equipamento selecionado");
                     equip[contEquip].setForeground(Color.red); 
                     
                     dados[d] = "0";                     
                    
                 }
             d=d+5;    
             }
        }  
            
        if (objetoRecebeuEvento == logOff) {
                Login novoLogin = new Login();
                novoLogin.setVisible(true);
                this.dispose();               
                
        }
    }

    public static void main(String argumentos[]) {

        JFrame novaJanela = new Tela();
        novaJanela.setVisible(true);
        //System.exit(1);

    }
}

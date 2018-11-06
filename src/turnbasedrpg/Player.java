/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import turnbasedrpg.moves.Pokemon;

/**
 *
 * @author matheus.oliveira
 */


public class Player extends JFrame {
    private int width;
    private int height;
    private Container contentPane;
    private JTextArea message;
    private JLabel label;
    private Pokemon pokemon;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    
    private int playerID;
    private int otherPlayerID;
    private int turnsMade;
    private int maxTurns;
    private int myPoints;
    private int enemyPoints;
    
    private boolean buttonsEnabled;
    
    private ClientSideConnection clientSideConnection;

    public Player (int width, int height) {
        // Configura as variavéis
        Pokemon pokemonGetter = new Pokemon();
        this.width = width;
        this.height = height;
        contentPane = this.getContentPane();
        message = new JTextArea();
        label = new JLabel();
        
        Pokemon Squirtle = pokemonGetter.getSquirtle();
        this.pokemon = Squirtle;
        
        // Configura os botões
        b1 = new JButton (this.pokemon.getPokemonMove(0).getName());
        b2 = new JButton (this.pokemon.getPokemonMove(1).getName());
        b3 = new JButton (this.pokemon.getPokemonMove(2).getName());
        b4 = new JButton (this.pokemon.getPokemonMove(3).getName());
        
        b1.setName("1");
        b2.setName("2");
        b3.setName("3");
        b4.setName("4");
        
        turnsMade = 0;
        myPoints = 0;
        enemyPoints = 0;
    }
    
    public void setUpGUI() {
        this.setSize (this.width, this.height);
        this.setTitle ("Player #"+playerID);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new GridLayout(1, 5));
        contentPane.add(message);
        contentPane.add(label);
        
        // Adiciona mensagens na caixa de mensagens
        message.setText("Creating game..");
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(true);
        
        // Adiciona os botões na tela
        contentPane.add(b1);
        contentPane.add(b2);
        contentPane.add(b3);
        contentPane.add(b4);
        
        message.setText("You are player #"+playerID);
        if (playerID == 1) {
            buttonsEnabled = true;
            otherPlayerID = 2;
        } else {
            buttonsEnabled = false;
            otherPlayerID = 1;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    updateTurn();
                }
            });
            t.start();
        }
        
        toggleButtons();
        
        this.setVisible(true);
    }

    public void setUpButtons() {
        ActionListener actionListener;
        
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JButton button = (JButton) event.getSource();
                String moveName = button.getText();
                
                message.setText("Used "+button.getText()+"!");
                System.out.println("Used "+button.getText()+"!");
                turnsMade++;
                buttonsEnabled = false;
                
                toggleButtons();
                
//                myPoints += values[bNum -1];;  // Reduz os pontos, no meu será diferente
                System.out.println("My health: "+myPoints);
                clientSideConnection.sendButtonNum(Integer.parseInt(button.getName())); // Ajustar para o ataque/pokém
                
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        updateTurn();
                    }
                });
                t.start();
            }
        };
        
        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
    }
    
    public void toggleButtons() {
        b1.setEnabled(buttonsEnabled);
        b2.setEnabled(buttonsEnabled);
        b3.setEnabled(buttonsEnabled);
        b4.setEnabled(buttonsEnabled);
    }
    
    public void updateTurn() {
        int n = clientSideConnection.receiveButtonNum();
        message.setText("Your enemy used "+n+". Your turn!");
        enemyPoints += n;
        buttonsEnabled = true;
        toggleButtons();
    }
    
    public void connectToServer() {
        clientSideConnection = new ClientSideConnection();
    }
   
    // Client Connection Inner Class
    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        
        public ClientSideConnection() {
            System.out.println("Cliente conectando");
            try {
                socket = new Socket("localhost", 51734); // Inicia o pedido de conexão ao servidor
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                playerID = dataIn.readInt();
                System.out.println("Connected as #"+ playerID);
                maxTurns = dataIn.readInt() / 2;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
        public void sendButtonNum(int n) {
            try {
                dataOut.writeInt(n);
                dataOut.flush();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public int receiveButtonNum() {
            int num = 0;
            try {
                num = dataIn.readInt();
                System.out.println("Player #"+otherPlayerID+" used" + num);
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            return num;
        }
    }
    
    
    public static void main(String[] args) {
        Player p = new Player (500, 100);
        p.connectToServer();
        p.setUpGUI();
        p.setUpButtons();
    }
}

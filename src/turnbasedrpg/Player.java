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
        
        if (playerID == 1) {
            message.setText("You have a "+this.pokemon.getName());
            buttonsEnabled = true;
        } else {
            buttonsEnabled = false;
        }
        toggleButtons();
        
        this.setVisible(true);
        setUpButtons();
    }

    public void setUpButtons() {
        ActionListener actionListener;
        
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JButton button = (JButton) event.getSource();
                String moveName = button.getText();
                
                message.setText("Used "+button.getText()+"!");
                turnsMade++;
                buttonsEnabled = false;
                
                toggleButtons();
                
//                myPoints += values[bNum -1];;  // Reduz os pontos, no meu será diferente
                System.out.println("My health: 0");
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
    public void connectToServer() {
        clientSideConnection = new ClientSideConnection();
    }
    // Client Connection Inner Class
    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        
        public ClientSideConnection() {
            System.out.println("Cliente conectando");
            try {
                socket = new Socket("localhost", 51734); // Inicia o pedido de conexão ao servidor
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                playerID = dataInputStream.readInt();
                System.out.println("Connected as #"+ playerID);
                maxTurns = dataInputStream.readInt() / 2;
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    public static void main(String[] args) {
        Player p = new Player (500, 100);
        p.connectToServer();
        p.setUpGUI();
        p.setUpButtons();
    }
}

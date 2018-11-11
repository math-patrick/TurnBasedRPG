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
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private Pokemon enemyPokemon;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JLabel image;
    private JLabel image2;
    
    private int playerID;
    private int otherPlayerID;
    private int turnsMade;
    private int maxTurns;
    private int myPoints;
    private int enemyPoints;
    
    private boolean buttonsEnabled;
    
    private ClientSideConnection clientSideConnection;

    public Player (int width, int height) throws IOException, URISyntaxException {
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
        
        URI img = getClass().getResource("/turnbasedrpg/pokemon/back/"+pokemon.getNumber()+".png").toURI();
        BufferedImage myPicture = ImageIO.read(new File((img)));

        image = new JLabel(new ImageIcon(myPicture));
        image.setMinimumSize(new Dimension(80, 80));
        
        URI img2 = getClass().getResource("/turnbasedrpg/pokemon/egg.png").toURI();
        BufferedImage myPicture2 = ImageIO.read(new File((img2)));

        image2 = new JLabel(new ImageIcon(myPicture2));
        image2.setMinimumSize(new Dimension(80, 80));
        
        b1.setName("1");
        b2.setName("2");
        b3.setName("3");
        b4.setName("4");
        
        turnsMade = 0;
        myPoints = 0;
        enemyPoints = 0;
    }
    
    public void setUpGUI() {
        this.setSize (200, 300);
        this.setTitle ("Player #"+playerID);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(image);
        contentPane.add(image2);
        
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
        
        contentPane.add(message);
        
        message.setText("You are player #"+playerID);
        if (playerID == 1) {
            buttonsEnabled = true;
            otherPlayerID = 2;
        } else {
            buttonsEnabled = false;
            otherPlayerID = 1;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        updateTurn();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                        try {
                            updateTurn();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (URISyntaxException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
    
    public void updateTurn() throws ClassNotFoundException, IOException, URISyntaxException {
        Pokemon n = clientSideConnection.receivePokemon();
        message.setText("Your enemy is a "+n.getName()+". Your turn!");
        
        // Atualiza a imagem do oponente
        URI img = getClass().getResource("/turnbasedrpg/pokemon/"+n.getNumber()+".png").toURI();
        BufferedImage enemy = ImageIO.read(new File((img)));
        image2.setIcon(new ImageIcon(enemy));
        
        // Habilita botões
        buttonsEnabled = true;
        toggleButtons();
    }
    
    public void connectToServer() {
        clientSideConnection = new ClientSideConnection(this.pokemon);
    }
   
    // Client Connection Inner Class
    private class ClientSideConnection {
        private Socket socket;
        private ObjectInputStream dataIn;
        private ObjectOutputStream dataOut;
        
        public ClientSideConnection(Pokemon pokemon) {
            System.out.println("Cliente conectando");
            try {
                socket = new Socket("localhost", 51734); // Inicia o pedido de conexão ao servidor
                dataOut = new ObjectOutputStream(socket.getOutputStream());
                dataOut.flush();
                dataIn = new ObjectInputStream(socket.getInputStream());
                playerID = dataIn.readInt();
                System.out.println("Connected as #"+ playerID);
                maxTurns = dataIn.readInt() / 2;
                sendPokemon(pokemon);
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
        
        public void sendPokemon(Pokemon n) {
            try {
                dataOut.writeObject(n);
                dataOut.flush();
            } catch (IOException ex) {
                System.out.println("Erro ao enviar o "+n.getName());
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public Pokemon receivePokemon() throws ClassNotFoundException {
            try {
                enemyPokemon = (Pokemon) dataIn.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            return enemyPokemon;
        }
    }
    
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        Player p = new Player (500, 100);
        p.connectToServer();
        p.setUpGUI();
        p.setUpButtons();
    }
}

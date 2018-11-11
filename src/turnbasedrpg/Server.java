/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import turnbasedrpg.moves.Combat;
import turnbasedrpg.moves.Pokemon;

/**
 *
 * @author matheus.oliveira
 */
public class Server {

    private ServerSocket serverSocket;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;
    private int turnsMade;
    private int maxTurns;
    private int[] values;

    private int p1ButtonNum;
    private Pokemon p1Pokemon;
    private int p2ButtonNum;
    private Pokemon p2Pokemon;

    public Server() {
        System.out.println("Servidor online!");
        numPlayers = 0;
        turnsMade = 0;
        maxTurns = 4;
        values = new int[4];

        for (int i = 0; i < values.length; i++) {
            values[i] = (int) Math.ceil(Math.random() * 100) + 1;
        }

        try {
            serverSocket = new ServerSocket(51734);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Esperando por conexoes.. ");
            while (numPlayers < 2) {
                Socket socket = serverSocket.accept();
                numPlayers++;
                System.out.println("Jogador número " + numPlayers + " se conectou!");
                ServerSideConnection serverSideConnection = new ServerSideConnection(socket, numPlayers);
                if (numPlayers == 1) {
                    player1 = serverSideConnection;
                } else {
                    player2 = serverSideConnection;
                }
                Thread thread = new Thread(serverSideConnection);
                thread.start();
            }
            System.out.println("Limite de jogadores alcançado");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private class ServerSideConnection implements Runnable {
        private Socket socket;
        private ObjectInputStream dataIn;
        private ObjectOutputStream dataOut;
        private int playerID;

        public ServerSideConnection(Socket socketParameter, int id) {
            this.socket = socketParameter;
            this.playerID = id;
            try {
                dataOut = new ObjectOutputStream(socket.getOutputStream());
                dataOut.flush();
                dataIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void run() {
            try {
                dataOut.writeInt(playerID);
                dataOut.writeInt(maxTurns);
                dataOut.flush();
                
                if (playerID == 1) {
                    p1Pokemon = (Pokemon) dataIn.readObject();
                } else {
                    p2Pokemon = (Pokemon) dataIn.readObject();
                }
                Combat combat = new Combat(p1Pokemon, p2Pokemon);
                p1ButtonNum = 0;
                p2ButtonNum = 0;
                while (true) {
                    if (p1Pokemon!=null && p2Pokemon!=null) {
                        if (playerID == 1) {
                            p1ButtonNum = dataIn.readInt();
    //                        int damage = combat.calculateDamage(p1ButtonNum, playerID);
                            System.out.println("Player 1 dealt " + 1);
                            player2.sendPokemon(p1Pokemon);
                        } else {
                            p2ButtonNum = dataIn.readInt();
    //                        int damage = combat.calculateDamage(p1ButtonNum, playerID);
                            System.out.println("Player 2 dealt " + 2);
                            player1.sendPokemon(p2Pokemon);
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("10");
                System.out.println(ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        public void sendPokemon(Pokemon n) {
            try {
                System.out.println("Sent: " + n);
                dataOut.writeObject(n);
                dataOut.flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Server gs = new Server();
        gs.acceptConnections();
    }
}

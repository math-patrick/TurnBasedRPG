/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int p2ButtonNum;    
    
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
                System.out.println("Jogador número "+numPlayers+" se conectou!");
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
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;
        
        public ServerSideConnection(Socket socketParameter, int id) {
            this.socket = socketParameter;
            this.playerID = id;
            try {
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex){
                System.out.println(ex);
            }
        }
        
        @Override
        public void run() {
            try {
                dataOut.writeInt(playerID);
                dataOut.writeInt(maxTurns);
                dataOut.flush();
                
                while (true) {
                    if (playerID == 1) {
                        p1ButtonNum = dataIn.readInt();
                        System.out.println("Player 1 used " + p1ButtonNum);
                        player2.sendButtonNum(p1ButtonNum);
                    } else  {
                        p2ButtonNum = dataIn.readInt();
                        System.out.println("Player 2 used " + p1ButtonNum);
                        player1.sendButtonNum(p2ButtonNum);
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
        public void sendButtonNum(int n) {
            try {
                System.out.println("Sent: "+n);
                dataOut.writeInt(n);
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

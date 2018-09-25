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

/**
 *
 * @author matheus.oliveira
 */
public class Server {
    private ServerSocket serverSocket;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;
    
    public Server() {
        System.out.println("Servidor online!");
        numPlayers =0;
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
            }
            System.out.println("Limite de jogadores alcançado");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private class ServerSideConnection implements Runnable {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private int playerID;
        
        public ServerSideConnection(Socket socketParameter, int id) {
            this.socket = socketParameter;
            this.playerID = id;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex){
                System.out.println(ex);
            }
        }
        
        @Override
        public void run() {
            dataOutputStream.writeInt(playerID);
        }
        
    }
    
    public static void main(String[] args) {
        Server gs = new Server();
        gs.acceptConnections();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

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

    private int p1ButtonNum;
    private int p2ButtonNum;
    private Pokemon p1Pokemon;
    private Pokemon p2Pokemon;

    private Combat combat;

    public Server() {
        System.out.println("Servidor online!");
        numPlayers = 0;

        combat = new Combat();

        try {
            serverSocket = new ServerSocket(51734);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Esperando por conexoes.. ");
            while (numPlayers <= 2) {
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
                dataOut.flush();

                if (playerID == 1) {
                    setP1Pokemon((Pokemon) dataIn.readObject());
                    combat.setPlayer1(getP1Pokemon());
                } else {
                    setP2Pokemon((Pokemon) dataIn.readObject());
                    combat.setPlayer2(getP2Pokemon());
                }

                p1ButtonNum = p2ButtonNum = -1;
                boolean cont = true;
                while (cont) {
                    if (getP1Pokemon() != null && getP2Pokemon() != null && combat.isReady()) {
                        if (playerID == 1) {
                            p1ButtonNum = dataIn.readInt();
                        } else {
                            p2ButtonNum = dataIn.readInt();
                        }
                        
                        if (p1ButtonNum!=-1 && p2ButtonNum!=-1) {
                            combat.calculateDamage(p1ButtonNum, p2ButtonNum);
                            p1ButtonNum = p2ButtonNum = -1;

                            setP1Pokemon(combat.getPlayer1());
                            setP2Pokemon(combat.getPlayer2());

                            player1.sendCombat(combat);
                            player2.sendCombat(combat);
                            
                            if (combat.getWinnerID()!=0) {
                                cont = false;
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void sendPokemon(Pokemon n) {
            try {
                dataOut.writeObject(n);
                dataOut.reset();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void sendCombat(Combat n) {
            try {
                dataOut.writeObject(n);
                dataOut.reset();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Server gs = new Server();
        gs.acceptConnections();
    }

    public Pokemon getP1Pokemon() {
        return p1Pokemon;
    }

    public void setP1Pokemon(Pokemon p1Pokemon) {
        this.p1Pokemon = p1Pokemon;
    }

    public Pokemon getP2Pokemon() {
        return p2Pokemon;
    }

    public void setP2Pokemon(Pokemon p2Pokemon) {
        this.p2Pokemon = p2Pokemon;
    }

}

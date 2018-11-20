/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import turnbasedrpg.moves.Combat;
import turnbasedrpg.moves.Pokemon;

/**
 *
 * @author Zhaetar
 */
class ClientSideConnection {

    private boolean isAlive = false;
    private Socket socket;
    private ObjectInputStream dataIn;
    private ObjectOutputStream dataOut;
    private int playerID;

    public ClientSideConnection(Pokemon pokemon) {
        System.out.println("Cliente conectando..");
        try {
            socket = new Socket("localhost", 51734); // Inicia o pedido de conexão ao servidor
            dataOut = new ObjectOutputStream(socket.getOutputStream());
            dataOut.flush();
            dataIn = new ObjectInputStream(socket.getInputStream());
            playerID = dataIn.readInt();
            System.out.println("Conectado como jogador #" + playerID);
            pokemon.setOT(playerID);
            sendPokemon(pokemon);
            isAlive = true;
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
            System.out.println("Erro ao enviar o " + n.getName());
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pokemon receivePokemon() throws ClassNotFoundException {
        Pokemon received = null;
        try {
            received = (Pokemon) dataIn.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return received;
    }

    public Combat receiveCombat() throws ClassNotFoundException {
        Combat received = null;
        try {
            received = (Combat) dataIn.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return received;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getDataIn() {
        return dataIn;
    }

    public void setDataIn(ObjectInputStream dataIn) {
        this.dataIn = dataIn;
    }

    public ObjectOutputStream getDataOut() {
        return dataOut;
    }

    public void setDataOut(ObjectOutputStream dataOut) {
        this.dataOut = dataOut;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    
    
}

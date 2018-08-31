/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author matheus.oliveira
 */
public class Player extends JFrame {
    private int width;
    private int height;
    private Container contentPane;
    private JTextArea message;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    
    public Player (int width, int height) {
        this.width = width;
        this.height = height;
        contentPane = this.getContentPane();
        message = new JTextArea();
        b1 = new JButton ("Water gun");
        b2 = new JButton ("Growl");
        b3 = new JButton ("Tackle");
        b4 = new JButton ("Bubbles");
    }
    
    public void setUpGUI() {
        this.setSize (this.width, this.height);
        this.setTitle ("Turn-Based RPG");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new GridLayout(1, 5));
        contentPane.add(message);
        message.setText("Creating game..");
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(true);
        contentPane.add(b1);
        contentPane.add(b2);
        contentPane.add(b3);
        contentPane.add(b4);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Player p = new Player (500, 100);
        p.setUpGUI();
    }
}

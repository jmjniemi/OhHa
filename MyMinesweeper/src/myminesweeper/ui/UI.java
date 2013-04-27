/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import myminesweeper.functionality.Field;

/**
 *Käyttöliittymä. Luo alueen, jolle ruudut piirretään, ja asettaa sen alle
 *tekstikentän, jossa näkyy jäljelläolevien miinojen määrä. Päälle ajanlaskun.
 */
public class UI implements Runnable {
    /**
     * Viite Field-olioon
     */
    private Field game;
    /**
     * Näyttää jäljellläolevien miinojen määrän
     */
    private JLabel statusbar;
    /**
     * Ajanlasku näkyy timebarissa
     */
    private JLabel timebar;
    /**
     * Timer-olio, jota timeConter-olio käyttää ajan laskemiseen.
     */
    private Timer timer;
    /**
     * Huolehtii ajanlaskusta
     */
    private TimeCounter timeCounter;
    /**
     * Kehys.
     */
    private JFrame frame;
    /**
     * Miinakentän piirtämisestä huolehtiva olio.
     */
    private Paintboard paintboard;
    /**
     * Hiiriadapteri.
     */
    private MineAdapter mouse;
    
    /**
     * Kostruktori saa parametrinä Field-olion, jonka se lähettää eteenpäin
     * piirtoalustalle ja hiiriadapterille
     * 
     * @param game Field-olio
     */
    public UI(Field game) {
        
        this.game = game;
        game.createField();
        
    }

    
    @Override
    public void run() {  
        
        frame = new JFrame("My Minesweeper");
        int boardWidth = game.getWidth() * 20 + 5;
        int boardHeight = game.getHeight() * 20 + 60;
        
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        frame.setResizable(false);
    }
    /**
     * Käyttöliittymään kuuluu alue, jolle piirtoalusta piirtää graafisen
     * esityksen miinakentästä, tilarivi, jossa on tieto jäljelläolevista
     * miinoista, timebar, jossa näkyy aika, sekä hiiriadapteri, jolla vuorovaikutus kentän kanssa
     * tapahtuu.
     * 
     * @param container 
     */
    public void createComponents(Container container) {
        statusbar = new JLabel("Start Minesweeping!");
        container.add(statusbar, BorderLayout.SOUTH);
        
        timebar = new JLabel("0");
        container.add(timebar, BorderLayout.NORTH);
        
        timeCounter = new TimeCounter(timebar);
        timer = new Timer(1000, timeCounter);
        timer.start();
        
        paintboard = new Paintboard(game, statusbar, frame, timer, timeCounter);
        container.add(paintboard);
        
        mouse = new MineAdapter(game, statusbar, paintboard, timer, timeCounter);
        frame.addMouseListener(mouse);
    }
    
    public JFrame getFrame() {
        return frame;
    }
}

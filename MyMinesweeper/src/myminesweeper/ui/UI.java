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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import myminesweeper.functionality.Field;

/**
 *Käyttöliittymä. Luo alueen, jolle ruudut piirretään, ja asettaa sen alle
 *tekstikentän, jossa näkyy jäljelläolevien miinojen määrä
 * 
 * 
 */
public class UI implements Runnable {
    
    private Field game;
    private JLabel statusbar;
    private JLabel timebar;
    private Timer timer;
    private TimeCounter timeCounter;
    private JFrame frame;
    private Paintboard paintboard;
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
        
//        JMenuBar menuBar = new JMenuBar();
//        frame.setJMenuBar(menuBar);
//        
//        JMenu file = new JMenu("File");
//        JMenuItem options = new JMenuItem("Options");
//        
//        file.add(options);
//        
//        menuBar.add(file);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        frame.setResizable(false);
    }
    /**
     * Käyttöliittymään kuuluu alue, jolle piirtoalusta piirtää graafisen
     * esityksen miinakentästä, tilarivi, jossa on tieto jäljelläolevista
     * miinoista, sekä hiiriadapteri, jolla vuorovaikutus kentän kanssa
     * tapahtuu.
     * 
     * @param container 
     */
    public void luoKomponentit(Container container) {
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

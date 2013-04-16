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
import javax.swing.WindowConstants;
import myminesweeper.Field;

/**
 *Käyttöliittymä. Luo alueen, jolle ruudut piirretään, ja asettaa sen alle
 *tekstikentän, jossa näkyy jäljelläolevien miinojen määrä
 * 
 * 
 */
public class UI implements Runnable {
    
    private Field game;
    private JLabel statusbar;
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
        int boardWidth = game.getWidth() * 20 + 15;
        int boardHeight = game.getHeight() * 20 + 60;
        
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
         
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
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
        
        paintboard = new Paintboard(game, statusbar, frame);
        container.add(paintboard);
        
        mouse = new MineAdapter(game, statusbar, paintboard);
        frame.addMouseListener(mouse);
    }
    
    public JFrame getFrame() {
        return frame;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.Image;
import javax.swing.JLabel;
import myminesweeper.Field;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Jaakko
 */
public class UI implements Runnable {
    
    private final int EMPTY = 0;
    private final int MINE = 9;
    private final int COVERED = 10;
    private final int MARKED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    
    private Field game;    
    private int[][] minefield;
    private JLabel statusbar;
    private Image[] images;
    private boolean gameStatus;
    private JFrame frame;
    private Paintboard paintboard;
    private MineAdapter mouse;
    
    public UI() {
        
        this.game = new Field(new Random());
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
    
    public void luoKomponentit(Container container) {
        statusbar = new JLabel("Start Minesweeping");
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

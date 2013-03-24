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
    
    private final int PAINT_MINE = 9;
    private final int PAINT_COVERED = 10;
    private final int PAINT_MARKED = 11;
    private final int PAINT_WRONG = 12;    
    
    private Field game;    
    private int[][] minefield;
    private JLabel statusbar;
    private Image[] images;
    private boolean gameStatus;
    private JFrame frame;
    private Paintboard paintboard;
    
    public UI(Field game) {
        
        this.game = game;
        
    }

    @Override
    public void run() {  
        
        frame = new JFrame("My Minesweeper");
        int boardWidth = game.getWidth() * 15;
        int boardHeight = game.getHeight() * 15;
        
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
        frame.pack();
        frame.setVisible(true); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);        

        statusbar = new JLabel("");
        frame.add(statusbar, BorderLayout.SOUTH);
        
        luoKomponentit(frame.getContentPane());
        
        game.createField();
        minefield = game.getField();
        gameStatus = true;
        frame.repaint();        
    }
    
    public void luoKomponentit(Container container) {
        paintboard = new Paintboard(game, statusbar, frame);
        container.add(paintboard);
        
        frame.addMouseListener(new MineAdapter(game, frame, statusbar));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.Image;
import javax.swing.JLabel;
import myminesweeper.Field;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
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
    
    public UI(Field game) {
        
        this.game = game;        
        
        images = new Image[13];
        
        for (int i = 0; i < 13; i++) {
            images[i] = (new ImageIcon(this.getClass().getResource((i) + ".png"))).getImage();
        }
        
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
        
        game.createField();
        minefield = game.getField();
        gameStatus = true;
        
    }
    
    public void paint(Graphics g) {
        
        int square;
        int uncoverCount = 0;
        
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                
                square = minefield[i][j];
                
                if (gameStatus && square == MINE) {
                    gameStatus = false;
                }
                
                if (!gameStatus) {
                    if (square == COVERED_MINE) {
                        square = PAINT_MINE;
                    } else if (square == MARKED_MINE) {
                        square = PAINT_MARKED;
                    } else if (square > COVERED_MINE) {
                        square = PAINT_WRONG;
                    } else if (square > MINE) {
                        square = PAINT_COVERED;
                    }
                } else {
                    if (square > COVERED_MINE) {
                        square = PAINT_MARKED;
                    } else if (square > MINE) {
                        square = PAINT_COVERED;
                        uncoverCount++;
                    }
                }
                
                g.drawImage(images[square], (j * 15), (i * 15), frame);
                
            }
        }
        
        if (uncoverCount == 0 && gameStatus) {
            gameStatus = false;
            statusbar.setText("You won!");
        } else if (!gameStatus) {
            statusbar.setText("You lost...");
        }
        
    }
    
}

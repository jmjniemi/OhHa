/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import myminesweeper.Field;

/**
 *
 * @author Jaakko
 */
public class Paintboard extends JPanel {
    
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
    private Image[] images;
    private JLabel statusbar;
    JFrame frame;
    
    public Paintboard(Field game, JLabel statusbar, JFrame frame) {
        
        this.game = game;
        this.minefield = game.getField();
        this.statusbar = statusbar;
        this.frame = frame;
        
        images = new Image[13];
        
        for (int i = 0; i < 13; i++) {
            images[i] = (new ImageIcon(this.getClass().getResource((i) + ".png"))).getImage();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        
        int square;
        int uncoverCount = 0;
        
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                
                square = minefield[i][j];
                
                if (game.getStatus() && square == MINE) {
                    game.setStatus(false);
                }
                
                if (!game.getStatus()) {
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
                
                g.drawImage(images[square], (j * 20), (i * 20), frame);
                
            }
        }
        
        if (uncoverCount == 0 && game.getStatus()) {
            game.setStatus(false);
            statusbar.setText("You won!");
        } else if (!game.getStatus()) {
            statusbar.setText("You lost...");
        }
        
    }
    
}

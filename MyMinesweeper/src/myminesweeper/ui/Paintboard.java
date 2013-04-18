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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import myminesweeper.functionality.Field;

/**
 *
 * 
 * 
 * Luokka luo graafisen esityksen miinakentästä.
 */
public class Paintboard extends JPanel {
    
    private final int MINE = 9;
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
    
    private Timer timer;
    private TimeCounter timeCounter;
    
    /**
     * Konstruktori tallentaa taulukkoon png-kuvatiedostot eri ruuduista.
     * 
     * @param game
     * @param statusbar
     * @param frame 
     */
    public Paintboard(Field game, JLabel statusbar, JFrame frame, Timer timer, TimeCounter timeCounter) {
        
        this.game = game;
        this.minefield = game.getField();
        this.statusbar = statusbar;
        this.frame = frame;
        
        this.timer = timer;
        this.timeCounter = timeCounter;
        
        images = new Image[13];
        
        for (int i = 0; i < 13; i++) {
            images[i] = (new ImageIcon(this.getClass().getResource((i) + ".png"))).getImage();
        }
    }
    
    public void setMinefield(int[][] mfield) {
        this.minefield = mfield;
    }
    
    /**
     * Metodi käy läpi miinakenttä-taulukon ja määrittää mitä kuvaa millekin
     * arvolle käytetään.
     * 
     * @param g 
     */
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
                        if (square != COVERED_MINE) {
                            uncoverCount++;
                        } 
                        square = PAINT_COVERED;                                               
                    }
                }
                
                g.drawImage(images[square], (j * 20), (i * 20), frame);
                
            }
        }
        
        if (uncoverCount == 0 && game.getStatus()) {
            game.setStatus(false);
            statusbar.setText("You won!");
            timer.stop();
            
            int endTime = timeCounter.getTime();
            JOptionPane.showMessageDialog(null, "Your time: " + endTime);
            
        } else if (!game.getStatus()) {
            statusbar.setText("You lost...");
            timer.stop();
        }
        
    }
    
}

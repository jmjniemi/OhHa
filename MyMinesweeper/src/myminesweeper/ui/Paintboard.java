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
import myminesweeper.ScoreHandler;
import myminesweeper.functionality.Field;

/**
 * Luokka luo graafisen esityksen miinakentästä.
 */
public class Paintboard extends JPanel {
    /**
     * ruudut ovat numeroarvoja, mutta selkeyden vuoksi käytetään nimettyjä muuttujia
     */
    private final int MINE = 9;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    /**
     * PAINT-muuttujat kertovat, mitä png-kuvaa käytetään.
     */
    private final int PAINT_MINE = 9;
    private final int PAINT_COVERED = 10;
    private final int PAINT_MARKED = 11;
    private final int PAINT_WRONG = 12;
    /**
     * Viite Field-olioon.
     */
    private Field game;
    /**
     * Viite miinakenttä-taulukkoon.
     */
    private int[][] minefield;
    /**
     * Taulukko png-kuvatiedostoista, joista miinakenttä piirretään.
     */
    private Image[] images;
    /**
     * Viite statusbariin.
     */
    private JLabel statusbar;
    /**
     * Viite kehykseen.
     */
    JFrame frame;
    /**
     * Viite timer-olioon
     */
    private Timer timer;
    /**
     * Viite timeCounter-olioon
     */
    private TimeCounter timeCounter;
    
    /**
     * Konstruktori asettaa muuttujat ja tallentaa taulukkoon png-kuvatiedostot
     * eri ruuduista.
     * 
     * @param game Field-olio
     * @param statusbar statusbar
     * @param frame frame
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
    /**
     * Resetoi miinakenttä-taulukon.
     * 
     * @param mfield uusi taulukko
     */
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
            
            if (game.scoreable()) {
                ScoreHandler sh = new ScoreHandler();
                if (sh.isHighScore(endTime)) {
                    String name = "";
                    int b = -1;
                    while (b < 0) {
                        name = JOptionPane.showInputDialog("New highscore! Please enter your name");
                        if (name.length() > 0) {
                            b++;
                        }
                    }
                    sh.newHighscore(name, endTime);                    
                }
            }
            
            
        } else if (!game.getStatus()) {
            statusbar.setText("You lost...");
            timer.stop();
        }
        
    }
    
}

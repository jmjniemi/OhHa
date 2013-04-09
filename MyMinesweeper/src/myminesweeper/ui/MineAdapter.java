/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import myminesweeper.Field;

/**
 *
 * 
 * 
 * Luokka hoitaa vuorovaikutuksen käyttäjän ja kentän välillä
 */
public class MineAdapter extends MouseAdapter {

    private final int EMPTY = 0;
    private final int MINE = 9;
    private final int COVERED = 10;
    private final int MARKED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    
    private Field game;
    private int[][] minefield;
    private JLabel statusbar;
    private Component component;

    /**
     * Konstruktori saa UI:lta tarvittavat parametrit
     * 
     * @param game
     * @param statusbar
     * @param component 
     */
    public MineAdapter(Field game, JLabel statusbar, Component component) {
        this.game = game;
        this.minefield = game.getField();
        this.statusbar = statusbar;
        this.component = component;
    }

    /**
     * Metodi tekee muutoksia miinakenttään sen mukaan, mitä näppäintä painettiin
     * ja mihin ruutuun osuttiin. Vasen näppäin merkitsee miinan, oikea paljastaa
     * ruudun, ja jos klikataan paljastettua ruutua, kaikki ympärillä olevat
     * ruudut paljastetaan.
     * 
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        int cColumn = (x-3) / 20;
        int cRow = (y-3) / 20-1;

        boolean rep = false; //tehdäänkö repaint

        if (!game.getStatus()) {
            game.createField();
            component.repaint();
        }

        if ((x < 495) && (y < 300)) {

            if (e.getButton() == MouseEvent.BUTTON3) {

                if (minefield[cRow][cColumn] > MINE) {
                    rep = true;

                    if (minefield[cRow][cColumn] <= COVERED_MINE) {
                        if (game.getMinesLeft() > 0) {
                            game.mark(cRow, cColumn);
                            game.squareMarked(true);
                            statusbar.setText(Integer.toString(game.getMinesLeft()));
                        } else {
                            statusbar.setText("No marks left");
                        }
                    } else {
                        game.unmark(cRow, cColumn);
                        game.squareMarked(false);
                        statusbar.setText(Integer.toString(game.getMinesLeft()));
                    }
                }
            } else {

                if (minefield[cRow][cColumn] > COVERED_MINE) {
                    return;
                }
                if ((minefield[cRow][cColumn] > MINE) && (minefield[cRow][cColumn] < MARKED_MINE)) {

                    game.uncover(cRow, cColumn);
                    rep = true;

                    if (minefield[cRow][cColumn] == MINE) {
                        game.setStatus(false);
                    }
                } else if (minefield[cRow][cColumn] < MINE) {
                    game.uncover(cRow-1, cColumn-1);
                    game.uncover(cRow-1, cColumn);
                    game.uncover(cRow-1, cColumn+1);
                    
                    game.uncover(cRow, cColumn-1);
                    game.uncover(cRow, cColumn+1);
                    
                    game.uncover(cRow+1, cColumn-1);
                    game.uncover(cRow+1, cColumn);
                    game.uncover(cRow+1, cColumn+1);
                    
                    rep = true;
                }
            }
            if (rep) {
                    component.repaint();
                }
        }
    }
}

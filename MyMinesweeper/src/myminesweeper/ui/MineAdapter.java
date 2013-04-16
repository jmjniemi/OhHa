/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import myminesweeper.functionality.Field;
import myminesweeper.functionality.ClickActions;

/**
 *
 * 
 * 
 * Luokka hoitaa vuorovaikutuksen käyttäjän ja kentän välillä
 */
public class MineAdapter extends MouseAdapter {
    
    private Field game;
    private int[][] minefield;
    private JLabel statusbar;
    private Paintboard component;
    private ClickActions actions;

    /**
     * Konstruktori saa UI:lta tarvittavat parametrit
     * 
     * @param game
     * @param statusbar
     * @param component 
     */
    public MineAdapter(Field game, JLabel statusbar, Paintboard component) {
        this.game = game;
        this.minefield = game.getField();
        this.statusbar = statusbar;
        this.component = component;
        this.actions = new ClickActions(game);
        actions.setMinefield(minefield);
    }

    /**
     * Metodi 
     * 
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        int cColumn = (x-3) / 20;
        int cRow = (y-3) / 20-1;

        if (!game.getStatus()) {
            game.createField();
            game.resetMinesLeft();
            game.setStatus(true);
            statusbar.setText("Start Minesweeping!");
            this.minefield = game.getField();
            actions.setMinefield(minefield);
            component.setMinefield(minefield);
            component.repaint();
        }

        if ((cColumn < game.getWidth()) && (cRow < game.getHeight())) {

            if (e.getButton() == MouseEvent.BUTTON3) {
                
                statusbar.setText(actions.rightClick(cRow, cColumn));
                
            } else {
                
                actions.leftClick(cRow, cColumn);
            }
            component.repaint();
        }
    }
}

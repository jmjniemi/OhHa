/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.Timer;
import myminesweeper.functionality.ClickActions;
import myminesweeper.functionality.Field;

/**
 * Luokka hoitaa vuorovaikutuksen käyttäjän ja kentän välillä
 */
public class MineAdapter extends MouseAdapter {

    private Field game;
    private int[][] minefield;
    /**
     * Kertoo onko klikkaus ensimmäinen vai ei.
     */
    private boolean firstClick;
    private JLabel statusbar;
    private Paintboard component;
    private ClickActions actions;
    private Timer timer;
    private TimeCounter timeCounter;

    /**
     * Konstruktori saa UI:lta tarvittavat parametrit
     *
     * @param game Field-olio
     * @param statusbar statusbar
     * @param component Paintboard-olio
     */
    public MineAdapter(Field game, JLabel statusbar, Paintboard component, Timer timer, TimeCounter timeCounter) {
        this.game = game;
        this.minefield = game.getField();
        this.firstClick = true;
        this.statusbar = statusbar;
        this.component = component;
        this.actions = new ClickActions(game);
        this.timer = timer;
        this.timeCounter = timeCounter;
        actions.setMinefield(minefield);
    }

    /**
     * Metodi huolehtii ensimmäisen klikkauksen aiheuttamista muutoksista ja
     * lähettää ClickActions-oliolle jokaisen klikkauksen kohderuudun ja
     * painetun hiirenpainikkeen. Kutsuu joka klikkauksen jälkeen Paintboardin
     * repaint-metodia.
     *
     * @param e Hiirenklikkauksen tiedot.
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        int cColumn = (x - 3) / 20;
        int cRow = (y - 41) / 20;

        if (game.checkIfInBounds(cRow, cColumn)) {

            if (!game.getStatus()) {
                game.createField();
                game.resetMinesLeft();
                game.setStatus(true);
                this.firstClick = true;
                statusbar.setText("Start Minesweeping!");

                timeCounter.resetTime();
                timer.start();

                this.minefield = game.getField();
                actions.setMinefield(minefield);
                component.setMinefield(minefield);
            }

            if (firstClick) {
                firstClick = false;
                this.statusbar.setText(Integer.toString(game.getMinesLeft()));
                game.firstClickCheck(cRow, cColumn);
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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import epiccaveadventure.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import moveable.Player;

/**
 *
 * @author Jaakko
 */
public class Listener implements KeyListener {
    
    private Player player;
    
    public Listener(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.move(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.move(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.move(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.move(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

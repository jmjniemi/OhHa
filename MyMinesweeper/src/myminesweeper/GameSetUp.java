/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.util.Random;
import myminesweeper.ui.UI;

/**
 *
 * @author Jaakko
 */
public class GameSetUp {
    
    private Field game;
    
    public GameSetUp() {
        this.game = new Field(new Random());
    }
    
    public void launch() {
        UI ui = new UI(game);
        
        ui.run();
    }
}

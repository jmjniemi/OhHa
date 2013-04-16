package myminesweeper;

import myminesweeper.functionality.Field;
import java.util.Random;
import myminesweeper.ui.UI;

/**
 *
 * 
 * 
 * Luokka luo uuden pelin ja käyttöliittymän
 */
public class GameSetUp {
    
    private Field game;
    private UI ui;
    
    public GameSetUp() {
        this.game = new Field(new Random());
        this.ui = new UI(game);
    }
    /**
     * metodi käynnistää käyttöliittymän
     */
    public void launch() {
        ui.run();
    }
}

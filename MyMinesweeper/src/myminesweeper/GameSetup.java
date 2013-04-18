package myminesweeper;

import myminesweeper.functionality.Field;
import java.util.Random;
import javax.swing.JOptionPane;
import myminesweeper.ui.UI;

/**
 *
 * 
 * 
 * Luokka luo uuden pelin ja käyttöliittymän
 */
public class GameSetup {
    
    private Field game;
    private UI ui;
    
    public GameSetup() {
    }
    
    protected void setup() {
        int g = -1;
        while (g < 0) {
            String answer = JOptionPane.showInputDialog("Do you want default settings? (yes/no)");
            if (answer.equals("yes")) {
                this.game = new Field(new Random());
                g++;
            } else if (answer.equals("no")) {
                defineCustomValues();
                g++;
            }
        }
        ui = new UI(game);
    }
    
    protected void defineCustomValues() {
        int customHeight = askValues("Height", 9, 30);
        int customWidth = askValues("Width", 9, 30);
        
        int maxMines = customHeight * customWidth / 2;
        int customMines = askValues("Mines", 10, maxMines);
        
        this.game = new Field(customHeight, customWidth, customMines, new Random());
        
    }
    
    protected int askValues(String valueType, int minValue, int maxValue) {
        int value = 0;
        int g = -1;
        
        while (g < 0) {
            String stringValue = JOptionPane.showInputDialog(valueType + "? (" + minValue + "-" + maxValue + ")");
            try {
                value = Integer.parseInt(stringValue);
                if (value < minValue) {
                    value = minValue;
                    JOptionPane.showMessageDialog(null, valueType + " set to " + minValue + ".");
                } else if (value > maxValue) {
                    value = maxValue;
                    JOptionPane.showMessageDialog(null, valueType + " set to " + maxValue + ".");
                }
                g++;
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter an integer.");
            }
        }
        return value;
    }
    
    /**
     * metodi käynnistää käyttöliittymän
     */
    public void launch() {
        
        setup();
        
        ui.run();
    }
}
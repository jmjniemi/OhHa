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
public class MyMinesweeper {
    
    public static void main(String[] args) {
        Field field = new Field(new Random());
        
        UI ui = new UI(field);
        
        ui.run();
    }
    
}

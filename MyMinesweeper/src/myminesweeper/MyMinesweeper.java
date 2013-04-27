package myminesweeper;

import java.io.IOException;

/**
 *
 * 
 */
public class MyMinesweeper {
    
    /**
     * main-metodi käynnistää pelin.
     * 
     * @param args 
     */
    public static void main(String[] args) throws IOException {
        GameSetup setup = new GameSetup();
        setup.launch();
    }
    
}

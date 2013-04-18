/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jaakko
 */
public class ScoreHandler {
    
    private FileWriter fWriter;
    
    public ScoreHandler() throws IOException {
        this.fWriter = new FileWriter("highscores.txt");
    }
    
    public void newHighScore(String name, int time) throws IOException {
        fWriter.write(name + ": " + time);
    }
    public void isHighScore(int time) {
    }
    
}

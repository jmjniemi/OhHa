/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Jaakko
 */
public class ScoreHandler {

    private File hiscore;
    private FileWriter fWriter;
    private Scanner scanner;
    private int currentTime;

    public ScoreHandler() {
        this.hiscore = new File("highscore.txt");

        try {
            this.scanner = new Scanner(this.hiscore);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }

        String iniTime = scanner.next();
        iniTime = scanner.next();
        
        this.currentTime = Integer.parseInt(iniTime);
    }

    public void newHighscore(String name, int time) {
        try {
            this.fWriter = new FileWriter(this.hiscore);
            fWriter.write(name + ": " + time);
            this.currentTime = time;
            fWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }

    }

    public boolean isHighScore(int time) {
        if (time < currentTime) {
            return true;
        } else {
            return false;
        }
    }

    public void showCurrent() {
        try {
            scanner = new Scanner(this.hiscore);
            JOptionPane.showMessageDialog(null, scanner.nextLine(), "Current Highscore", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "error");
        }

    }
    
    public void reset() {
        newHighscore("Java-Man", 999);
    }
}

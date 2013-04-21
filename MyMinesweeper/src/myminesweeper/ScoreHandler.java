/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private String currentScore;

    public ScoreHandler() {
        this.hiscore = new File("highscore.txt");

        try {
            this.scanner = new Scanner(this.hiscore);
        } catch (Exception e) {
            e.toString();
        }

        String iniName = scanner.next();
        String iniTime = scanner.next();

        this.currentScore = iniName + " " + iniTime;
        this.currentTime = Integer.parseInt(iniTime);
    }

    public void newHighscore(String name, int time) {
        try {
            this.fWriter = new FileWriter(this.hiscore);
            fWriter.write(name + ": " + time);
            this.currentTime = time;
            fWriter.close();
        } catch (Exception e) {
            e.toString();
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
        } catch (Exception e) {
            e.toString();
        }

    }
    
    public void reset() {
        newHighscore("Java-Man", 999);
    }
}

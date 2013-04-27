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
 * Luokka on vastuussa huipputuolosten tallentamisesta.
 */
public class ScoreHandler {
    /**
     * Tiedosto, johon huipputulos on tallennettu.
     */
    private File hiscore;
    /**
     * FileWriter-olio, jolla uudet huiiputulokset kirjoitetaan hiscore-tiedostoon.
     */
    private FileWriter fWriter;
    /**
     * Scanner-olio, jolla selvitetään voimassaoleva huipputulos.
     */
    private Scanner scanner;
    /**
     * Pelin alkaessa voimassaoleva huipputulos tallennetaan tähän muuttujaan.
     */
    private int currentTime;
    /**
     * Konstruktori, jossa määritetään hiscore-tiedosto ja tallennetaan siinä 
     * oleva huipputulos currentTime-muuttujaan.
     */
    public ScoreHandler() {
        this.hiscore = new File("highscore.txt");

        try {
            this.scanner = new Scanner(this.hiscore);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }

        String iniTime = scanner.next();
        iniTime = scanner.next();
        
        this.currentTime = Integer.parseInt(iniTime);
    }
    /**
     * Metodi, joka tallentaa uuden huipputuloksen hiscore-tiedostoon.
     * 
     * @param name Huipputuloksen tekijän nimi.
     * @param time Uusi huipputulos.
     */
    public void newHighscore(String name, int time) {
        try {
            this.fWriter = new FileWriter(this.hiscore);
            fWriter.write(name + ": " + time);
            this.currentTime = time;
            fWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error with FileWriter");
        }

    }
    /**
     * Metodi tarkistaa, onko parametrina saatu aika uusi huipputulos.
     * 
     * @param time Tulos, jonka huippuutta tutkitaan.
     * @return true, jos aika on uusi huipputulos, false muuten. 
     */
    public boolean isHighScore(int time) {
        if (time < currentTime) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Metodi, joka esittää nykyisen huipptuloksen ja sen tekijän.
     */
    public void showCurrent() {
        try {
            scanner = new Scanner(this.hiscore);
            JOptionPane.showMessageDialog(null, scanner.nextLine(), "Current Highscore", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "File empty!");
        }

    }
    /**
     * Metodi palauttaa oletusarvon huipputulokseksi.
     */
    public void reset() {
        newHighscore("Java-Man", 999);
    }
}

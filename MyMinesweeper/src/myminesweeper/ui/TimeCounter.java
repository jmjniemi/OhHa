/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * Luokka huolehtii ajan laskemisesta.
 */
public class TimeCounter implements ActionListener {
    /**
     * Kulloinenkin aika.
     */
    private int currentTime;
    /**
     * JLabel, jossa aika näkyy.
     */
    JLabel timebar;
    /**
     * Konstruktori asettaa alkuajaksi 0 ja saa viitteen timebariin.
     * 
     * @param timebar viite timebariin
     */
    public TimeCounter(JLabel timebar) {
        this.timebar = timebar;
        this.currentTime = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentTime < 999) {
            currentTime++;            
            timebar.setText("" + currentTime);
        }
    }
    
    public int getTime() {
        return this.currentTime;
    }
    public void resetTime() {
        this.currentTime = 0;
    }
    
}

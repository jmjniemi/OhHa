/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import cave.Cave;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Jaakko
 */
public class UI implements Runnable {
    
    private JFrame frame;
    private Cave cave;    
    private Board board;
    
    public UI(HashMap<String, int[]> stats, ArrayList<String> monsterNames) {
        this.cave = new Cave(stats, monsterNames);        
    }

    @Override
    public void run() {
        frame = new JFrame("Matopeli");
        int leveys = 1000;
        int korkeus = 800;
         
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {        
        container.add(new JTextArea(), BorderLayout.NORTH);
        container.add(new JTextArea(), BorderLayout.SOUTH);
        
        frame.addKeyListener(new Listener(cave.getPlayer()));        
    }
 
    public JFrame getFrame() {
        return frame;
    }
    
}

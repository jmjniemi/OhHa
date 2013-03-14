/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

/**
 *
 * @author Jaakko
 */
public class MyMinesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board();
        
        b.createField();
        
        b.drawMinefield();
    }
}

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
        Field b = new Field(10, 10, 10);
        
        b.createField();
        
        b.drawMinefield();
    }
}

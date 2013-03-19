/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.util.Random;

/**
 *
 * @author Jaakko
 */
public class MyMinesweeper {
    
    public static void main(String[] args) {
        Field b = new Field(10, 10, 10, new Random());
        
        b.createField();
        
        b.drawMinefield();
    }
    
}

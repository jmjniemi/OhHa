/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.functionality;

import java.util.Random;


/**
 *
 * @author Jaakko
 */
public class MyRandom extends Random {
    
    private int counter;
    
    public MyRandom() {
        this.counter = 0;
    }
        
    @Override
    public int nextInt(int luku) {
        this.counter++;
        return this.counter - 1;        
    }
    
}

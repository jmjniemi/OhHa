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
public class FieldGenerator {
    
    //ruudut ovat numeroarvoja, mutta selkeyden vuoksi käytetään nimettyjä muuttujia
    private final int EMPTY = 0;
    private final int MINE = 9;
    private final int COVERED = 10;
    private final int MARKED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    
    private int[][] minefield;
    private int height;
    private int width;
    private int mines;
    
    private Random random;
    
    public FieldGenerator(int[][] minefield, int height, int width, int mines, Random r) {
        this.minefield = minefield;
        this.height = height;
        this.width = width;
        this.mines = mines;
        this.random = r;
    }
    
    public int[][] createField() {
        
        minefield = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                minefield[i][j] = COVERED;  //alussa kaikki ruudut on peitetty
            }
        }
                
        int usedMines = 0;
        int y;
        int x;        

        while (usedMines < mines) { //sijoitetaan miinat

            //valitaan satunnainen paikka miinalle
            y = random.nextInt(height);
            x = random.nextInt(width);

            if ((minefield[y][x] != COVERED_MINE)) {

                minefield[y][x] = COVERED_MINE;
                usedMines++;

                //seuraavaksi kasvatetaan asetetun miinan viereisten ruutujen arvoja, jotka kertovat
                //ympäröivien miinojen määrän. Ympäröiviä ruutuja on 8, paitsi reunoissa. 
                addCount(y-1, x-1);
                addCount(y-1, x);
                addCount(y-1, x+1);
                
                addCount(y,   x-1);
                addCount(y,   x+1);
                
                addCount(y+1, x-1);
                addCount(y+1, x);
                addCount(y+1, x+1);
            }
        }
        return minefield;
    }
    
    protected void addCount(int y, int x) {
        try {
            if (minefield[y][x] != COVERED_MINE) {
                minefield[y][x] += 1;
            }
        } catch (Exception e) { } //jos menee ulos taulukosta, ei tehdä mitään
    }
    
}

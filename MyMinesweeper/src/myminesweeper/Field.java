/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.util.Random;

public class Field {

    //ruudut ovat numeroarvoja, mutta selkeyden vuoksi käytetään nimettyjä muuttujia
    private final int EMPTY = 0;
    private final int MINE = 9;
    private final int COVERED = 10;
    private final int MARKED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    
    private int[][] minefield; //miinakenttä 2-ulotteinen taulukko
    private int mines = 99;  //
    private int height = 16; //defaul-arvot, näillä pelaamalla lasketaan pisteet
    private int width = 30;  //
    private int minesLeft = 99;    
    private boolean countScore;

    public Field() {
        this.countScore = true;
    }

    public Field(int height, int width, int mines) {
        if (height < 9) {
            this.height = 9;
        } else if (height > 50) {
            this.height = 50;
        } else {
            this.height = height;
        }
        
        if (width < 9) {
            this.width = 9;
        } else if (width > 50) {
            this.width = 50;
        } else {
            this.width = width;
        }
        
        if (mines < 10) {
            this.mines = 10;
        } else if (mines > (this.height * this.width / 2) ) {
            this.mines = this.height * this.width / 2;
        } else {
            this.mines = mines;
        }
        
        minesLeft = this.mines;        
        this.countScore = false; //pelaaja voi pelata myös omilla arvoillaan ilman pistelaskua
    }
    
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public int getMines() {
        return this.mines;
    }
    public int[][] getField() {
        return this.minefield;
    }

    public void createField() {
        
        minefield = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                minefield[i][j] = COVERED;  //alussa kaikki ruudut on peitetty
            }
        }

        Random random = new Random();        
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
    }

    private void addCount(int y, int x) {
        try {
            if (minefield[y][x] != COVERED_MINE) {
                minefield[y][x] += 1;
            }
        } catch (Exception e) { } //jos menee ulos taulukosta, ei tehdä mitään
    }

    public void drawMinefield() { //apumetodi, ei käytetä pelissä

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (minefield[i][j] == COVERED_MINE) {
                    System.out.print("*");
                } else if (minefield[i][j] - 10 == EMPTY) {
                    System.out.print(".");
                } else {
                    System.out.print(minefield[i][j] - 10);
                }
            }
            System.out.println();
        }
    }
}

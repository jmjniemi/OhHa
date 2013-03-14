/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper;

import java.util.Random;

public class Board {

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
    private int minesLeft;    
    private boolean countScore;

    public Board() {
        this.countScore = true;
    }

    public Board(int height, int width, int mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
        this.countScore = false; //pelaaja voi pelata myös omilla arvoillaan ilman pistelaskua
    }

    public void createField() {

        Random random = new Random();
        minesLeft = mines;
        minefield = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                minefield[i][j] = COVERED;  //alussa kaikki ruudut on tietysti peitetty
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

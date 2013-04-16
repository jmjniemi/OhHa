package myminesweeper;

import java.util.Random;

/**
 *  
 * Luokka luo kaksiulootteisen Integer-taulukon miinakentäksi ja hoitaa muutokset siihen
 * 
 * 
 */

public class Field {

    //ruudut ovat numeroarvoja, mutta selkeyden vuoksi käytetään nimettyjä muuttujia
    private final int EMPTY = 0;
    private final int MINE = 9;
    private final int COVERED = 10;
    private final int MARKED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    private final int MARKED_MINE = 29; //COVERED_MINE + MARKED
    
    private int[][] minefield; //miinakenttä 2-ulotteinen taulukko
    private int mines = 30;  //
    private int height = 14; //defaul-arvot, näillä pelaamalla lasketaan pisteet
    private int width = 14;  //
    private int minesLeft = 30;    
    private boolean countScore;
    private boolean gameStatus = true;    
    
    private FieldGenerator fg;

    /**
     * Ensimmäinen konstruktori luo pelin default-arvoilla. Pistelasku sallitaan.
     * 
     * @param r Random-olio, joka lähetetään eteenpäin FieldGenerator-oliolle
     */
    public Field(Random r) {
        this.countScore = true;        
        this.fg = new FieldGenerator(minefield, height, width, mines, r);
    }

    /**
     * Toinen kostruktori, joka luo käyttäjän haluaman miinakentän tietyin rajoituksin
     * 
     * @param height käyttäjän antama korkeus
     * @param width käyttäjän antama leveys
     * @param mines käyttämän antama miinamäärä
     * @param r Random-olio, joka lähetetään eteenpäin FieldGenerator-oliolle
     */
    public Field(int height, int width, int mines, Random r) {
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
        
        this.minesLeft = this.mines;        
        this.countScore = false; //pelaaja voi pelata myös omilla arvoillaan ilman pistelaskua        
        
        this.fg = new FieldGenerator(minefield, height, width, mines, r);
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
    public int getMinesLeft() {
        return this.minesLeft;
    }    
    public void resetMinesLeft() {
        this.minesLeft = this.mines;
    }
    public int[][] getField() {
        return this.minefield;
    }
    public boolean getStatus() {
        return this.gameStatus;
    }
    public void setStatus(boolean s) {
        this.gameStatus = s;
    }
    public void squareMarked(boolean unmarked) {
        if (unmarked) {
            this.minesLeft--;
        } else {
            this.minesLeft++;
        }        
    }

    /**
     * FieldGenerator-olio asettaa miinat kenttään
     */
    public void createField() {
        this.minefield = fg.createField();
    }
    
    
    /**
     * Annettu talukon arvo paljastetaan.
     * Jos ruutu on tyhjä, paljastetaan ympäröivätkin ruudut
     * 
     * @param y
     * @param x 
     */
    public void uncover(int y, int x) {
        if (y >= 0 && y < this.height && x >= 0 && x < this.height && minefield[y][x] > MINE && minefield[y][x] < EMPTY+COVERED+MARKED) {
            minefield[y][x] -= COVERED;
            if (minefield[y][x] == EMPTY) {
                uncover(y-1, x-1);
                uncover(y-1, x);
                uncover(y-1, x+1);
                
                uncover(y, x-1);
                uncover(y, x+1);
                
                uncover(y+1, x-1);
                uncover(y+1, x);
                uncover(y+1, x+1);
            }
        }
    }
    /**
     * Annettu taulukon arvo merkitään miinaksi
     * 
     * @param y
     * @param x 
     */
    public void mark(int y, int x) {
        if (y >= 0 && y < this.height && x >= 0 && x < this.height && minefield[y][x] > MINE && minefield[y][x] < EMPTY+COVERED+MARKED) {
            minefield[y][x] += MARKED;
        }
    }
    /**
     * Merkitty miina asetetaan takaisin merkitsemättömäksi
     * 
     * @param y
     * @param x 
     */
    public void unmark(int y, int x) {
        if (y >= 0 && y < this.height && x >= 0 && x < this.height && minefield[y][x] > COVERED_MINE) {
            minefield[y][x] -= MARKED;
        }
    }

    /**
     * Apumetodi, joka tulostaa merkkiesityksen luodusta miinakentästä. Ei käytetä varsinaisessa pelissä.
     */
    public void drawMinefield() {

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

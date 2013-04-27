/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.functionality;

import java.util.Random;

/**
 * Luokka asettaa miinat taulukkoon.
 */
public class FieldGenerator {
    
    /**
     * ruudut ovat numeroarvoja, mutta selkeyden vuoksi käytetään nimettyjä muuttujia
     */
    private final int COVERED = 10;
    private final int COVERED_MINE = 19; //MINE + COVERED
    /**
     * Miinakenttä on 2-ulotteinen kokonaislukutaulukko
     */
    private int[][] minefield;
    /**
     * taulukon korkeus ja leveys
     */
    private int height;
    private int width;
    /**
     * Random-olio, jota käytetään miinojen asettamiseen.
     */
    private Random random;
    
    /**
     * Konstruktori saa tarvittavat parametrit Field-oliolta
     * 
     * @param minefield muokattava miinakenttä-taulukko
     * @param height taulukon korkeus
     * @param width taulukon leveys
     * @param mines miinojen määrä
     * @param r Random-olio, jota käytetään miinojen asettamisessa
     */
    public FieldGenerator(int[][] minefield, int height, int width, int mines, Random r) {
        this.minefield = minefield;
        this.height = height;
        this.width = width;
        this.random = r;
    }
    /**
     * Metodi asettaa miinat kenttään. Jokaisen miinanasetuksen jälkeen viereisiä
     * taulukon arvoja kasvatetaan yhdellä. Näin jokainen ruutu tietää montako
     * miinaa sen ympärillä on. Miinan paikat arvotaan Random-oliolla.
     * 
     * @return Palauttaa miinakentän, johon miinat on asetettu
     */
    public int[][] createField(int mines) {
        
        minefield = new int[height][width];

        initialize();
        
        deployMines(mines);
        
        return minefield;
    }
    /**
     * Metodi asettaa ennen miinojen asettamista kaikki ruudut peitetyiksi.
     */
    protected void initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                minefield[i][j] = COVERED;  //alussa kaikki ruudut on peitetty
            }
        }
    }
    /**
     * Metodi asettaa miinat.
     * 
     * @param mines asetettavien miinojen määrä.
     */
    protected void deployMines(int mines) {
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
                changeCount(y-1, x-1, 1);
                changeCount(y-1, x, 1);
                changeCount(y-1, x+1, 1);
                
                changeCount(y,   x-1, 1);
                changeCount(y,   x+1, 1);
                
                changeCount(y+1, x-1, 1);
                changeCount(y+1, x, 1);
                changeCount(y+1, x+1, 1);
            }
        }
    }
    /**
     * Muuttaa parametreina saadun ruudun arvoa.
     * 
     * @param y Ruudun y-koordinaatti.
     * @param x Ruudun x-koordinaatti.
     * @param change kuinka paljon arvoa muutetaan. Negatiivinen vähentää arvoa.
     */
    protected void changeCount(int y, int x, int change) {
        try {
            if (minefield[y][x] != COVERED_MINE) {
                minefield[y][x] += change;
            }
        } catch (Exception e) { } //jos menee ulos taulukosta, ei tehdä mitään
    }
    
}

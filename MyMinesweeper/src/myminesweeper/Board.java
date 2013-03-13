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
    
    private final int DRAW_MINE = 9;
    private final int DRAW_COVER = 10;
    private final int DRAW_MARK = 11;
    private final int DRAW_WRONG = 12;
    
    private int[] minefield;
    private boolean inGame;
    private int minesLeft;
    private int mines = 99;
    private int height = 16;
    private int width = 30;
    private int squares;
    
    public Board() {
        
    }
    
    public void newGame() {
        
        Random random = new Random();        
        inGame = true;
        minesLeft = mines;        
        squares = height * width;
        minefield = new int[squares];
        
//        for (int i = 0; i < squares; i++) {
//            minefield[i] = COVERED;
//        }
        
        int usedMines = 0;
        int position = 0;
        int currentColumn;
        int square = 0;
        
        while (usedMines < mines) { //sijoitetaan miinat
            
            position = (int) (squares * random.nextDouble()); //valitaan satunnainen paikka miinalle
            
            if ( (position < squares) && (minefield[position] != COVERED_MINE) ) {
                
                currentColumn = position % width; //mille pystyriville miina tulee
                minefield[position] = COVERED_MINE;
                usedMines++;
                
                //seuraavaksi kasvatetaan asetetun miinan viereisten ruutujen arvoja, jotka kertovat
                //ympäröivien miinojen määrän. Ympäröiviä ruutuja on 8, paitsi reunoissa. 
                if (currentColumn > 0) {
                    
                    square = position - 1 - width;                    
                    if (square >= 0) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }
                    
                    square = position - 1;
                    if (square >= 0) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }
                    
                    square = position + width - 1;
                    if (square < squares) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }
                    
                }
                
                square = position - width;
                if (square >= 0) {
                    if (minefield[square] != COVERED_MINE) {
                        minefield[square] += 1;
                    }
                }
                
                square = position + width;
                if (square < squares) {
                    if (minefield[square] != COVERED_MINE) {
                        minefield[square] += 1;
                    }
                }
                
                if (currentColumn < (width - 1)) {
                    
                    square = position - width + 1;
                    if (square >= 0) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }
                    
                    square = position + width + 1;
                    if (square < squares) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }
                    
                    square = position + 1;
                    if (square < squares) {
                        if (minefield[square] != COVERED_MINE) {
                            minefield[square] += 1;
                        }
                    }                    
                }                
            }            
        }        
    }
    
    public void drawMinefield() {
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (minefield[i + j] == COVERED_MINE) {
                    System.out.print("*");
                } else if (minefield[i + j] == EMPTY) {
                    System.out.print(".");
                } else {
                    System.out.print(minefield[i + j]);
                }                
            }
            System.out.println();
        }
        
        
    }
}

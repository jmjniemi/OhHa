/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.functionality;

/**
 *
 * Luokka toteuttaa hiirenklikkausten tuottamat muutokset miinakenttään
 */
public class ClickActions {
    /**
     * Viite Field, olioon, johon muutokset annetaan.
     */
    private Field game;
    /**
     * Miinakenttä-taulukko.
     */
    private int[][] minefield;
    /**
     * Konstruktori asettaa game-muuttujan.
     * 
     * @param game Field-olio
     */
    public ClickActions(Field game) {
        this.game = game;
    }
    /**
     * Asettaa ja resetoi miinakenttä-muuttujan.
     * 
     * @param mfield 
     */
    public void setMinefield(int[][] mfield) {
        this.minefield = mfield;
    }

    /**
     * Metodi merkitsee miinaksi, tai poistaa merkinnän parametrinaan saamaan
     * ruutuun ja palauttaa statusbariin asetettavan tekstin
     * 
     * @param y Ruudun y-koordinaatti.
     * @param x Ruudun x-koordinaatti.
     * @return Statusbariin asetettava teksti.
     */
    public String rightClick(int y, int x) {
        if (game.checkIfInBounds(y, x)) {

            if (minefield[y][x] <= 9) {
                return (Integer.toString(game.getMinesLeft()));
            } else if (minefield[y][x] <= 19) {
                if (game.getMinesLeft() > 0) {
                    game.mark(y, x);
                    game.squareMarked(true);
                    return (Integer.toString(game.getMinesLeft()));
                } else {
                    return "No marks left";
                }
            } else {
                game.unmark(y, x);
                game.squareMarked(false);
                return (Integer.toString(game.getMinesLeft()));
            }
        } else {
            return (Integer.toString(game.getMinesLeft()));
        }
    }

    /**
     * Paljastaa parametrinaan saaman ruudun jos mahdollista. Jos ruutu on jo
     * paljastettu, kutsuu massClick-metodia
     * 
     * @param y Ruudun y-koordinaatti.
     * @param x Ruudun x-koordinaatti.
     */
    public void leftClick(int y, int x) {
        if (game.checkIfInBounds(y, x)) {

            if (minefield[y][x] > 19) {
                return;
            }
            if ((minefield[y][x] > 9) && (minefield[y][x] < 29)) {

                game.uncover(y, x);

                if (minefield[y][x] == 9) {
                    game.setStatus(false);
                }
            } else if (minefield[y][x] < 9) {
                massClick(y, x);
            }
        }
    }

    /**
     * Paljastaa kaikki parametrinä saamansa ruudun ympärillä olevat ruudut, 
     * jos oikea määrä miinoja on merkitty
     * 
     * @param y Ruudun y-koordinaatti.
     * @param x Ruudun x-koordinaatti.
     */
    protected void massClick(int y, int x) {
        if (countAdjacentMines(y, x) == minefield[y][x]) {
            game.uncover(y - 1, x - 1);
            game.uncover(y - 1, x);
            game.uncover(y - 1, x + 1);

            game.uncover(y, x - 1);
            game.uncover(y, x + 1);

            game.uncover(y + 1, x - 1);
            game.uncover(y + 1, x);
            game.uncover(y + 1, x + 1);
        }

    }

    /**
     * Laskee parametrina saamansa ruudun ympäriltä merkityt miinat
     * 
     * @param y Ruudun y-koordinaatti.
     * @param x Ruudun x-koordinaatti.
     * @return Ympäröivien miinojen määrä.
     */
    protected int countAdjacentMines(int y, int x) {
        int adjacentMines = 0;

        adjacentMines += isMine(y - 1, x - 1);
        adjacentMines += isMine(y - 1, x);
        adjacentMines += isMine(y - 1, x + 1);

        adjacentMines += isMine(y, x - 1);
        adjacentMines += isMine(y, x + 1);

        adjacentMines += isMine(y + 1, x - 1);
        adjacentMines += isMine(y + 1, x);
        adjacentMines += isMine(y + 1, x + 1);

        return adjacentMines;
    }

    /**
     * Metodi tutkii parametrien ilmoittaman ruudun ja palauttaa 1 jos se on
     * merkitty miina, 0 muuten.
     *
     * @param y tutkittavan ruudun y-koordinaatti
     * @param x tutkittavan ruudun x-koordinaatti
     * @return 
     */
    protected int isMine(int y, int x) {
        try {
            if (minefield[y][x] > 19) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}

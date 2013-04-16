/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.functionality;

import myminesweeper.Field;

/**
 *
 * @author Jaakko
 */
public class ClickActions {

    private Field game;
    private int[][] minefield;

    public ClickActions(Field game) {
        this.game = game;
    }

    public void setMinefield(int[][] mfield) {
        this.minefield = mfield;
    }

    public String rightClick(int y, int x) {
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
    }

    public void leftClick(int y, int x) {
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

    private void massClick(int y, int x) {
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

    private int countAdjacentMines(int y, int x) {
        int adjacentMines = 0;

        if (y > 0 && x > 0) {
            if (minefield[y - 1][x - 1] == 29) {
                adjacentMines++;
            }
        }

        if (y > 0 && x < (game.getWidth() - 1)) {
            if (minefield[y - 1][x] == 29) {
                adjacentMines++;
            }
            if (minefield[y - 1][x + 1] == 29) {
                adjacentMines++;
            }
            if (minefield[y][x + 1] == 29) {
                adjacentMines++;
            }
        }

        if (y < (game.getHeight() - 1) && x > 0) {
            if (minefield[y][x - 1] == 29) {
                adjacentMines++;
            }
            if (minefield[y + 1][x] == 29) {
                adjacentMines++;
            }
            if (minefield[y + 1][x - 1] == 29) {
                adjacentMines++;
            }
        }

        if (y < (game.getHeight() - 1) && x < (game.getWidth() - 1)) {
            if (minefield[y + 1][x + 1] == 29) {
                adjacentMines++;
            }
        }
        
        return adjacentMines;
    }
}

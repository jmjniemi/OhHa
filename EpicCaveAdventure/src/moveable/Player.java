/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveable;

import epiccaveadventure.Direction;
import java.util.HashMap;

/**
 *
 * @author Jaakko
 */
public class Player extends Moveable {
    
    private String playerName;
    private int healCount;
    
    public Player(String name, int x, int y, HashMap<String, int[]> stats) {
        super("Player", x, y, stats);
        this.playerName = name;
        this.healCount = 0;
    }
    
    public String getPlayerName() {
        return this.playerName;
    }    
    
    @Override
    public void move(Direction dir) {
        super.move(dir);
        
        this.healCount++;
        
        if(this.healCount == 10) {
            super.heal();
            this.healCount = 0;
        }        
    }
    
    @Override
    public String toString() {
        return playerName + super.toString();
    }
    
}

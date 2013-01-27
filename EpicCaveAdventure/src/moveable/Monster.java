/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveable;

import java.util.HashMap;

/**
 *
 * @author Jaakko
 */
public class Monster extends Moveable {
    
    private int scorePoints;
    
    public Monster(String name, int x, int y, HashMap<String, int[]> stats) {        
        super(name, x, y, stats);
        this.scorePoints = stats.get(name)[0];
    }
    
    public int getScorePoints() {
        return this.scorePoints;
    }
    
    @Override
    public String toString() {
        return super.getName() + super.toString();
    }
    
}

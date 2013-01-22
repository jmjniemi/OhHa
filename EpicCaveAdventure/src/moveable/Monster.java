/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveable;

/**
 *
 * @author Jaakko
 */
public class Monster extends Moveable {
    
    private int scorePoints;
    
    public Monster(String name, int x, int y) {        
        super(name, x, y);
        this.scorePoints = monsterScorePoints(name);
    }
    
    private int monsterScorePoints(String type) {
        if(type.equals("Hirviö")) {
            return 5;
        }
        throw new IllegalArgumentException("No such monster");
    }
    
    public int getScorePoints() {
        return this.scorePoints;
    }
    
    @Override
    public String toString() {
        return super.getName() + super.toString();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveable;

/**
 *
 * @author Jaakko
 */
public class Player extends Moveable {
    
    private String playerName;
    private int healCount;
    
    public Player(String name, int x, int y) {
        super("Player", x, y);
        this.playerName = name;
        this.healCount = 0;
    }
    
    @Override
    public void move(int direction) {
        super.move(direction);
        
        this.healCount++;
        
        if(this.healCount == 10) {
            super.heal();
            this.healCount = 0;
        }        
    }
    
    @Override
    public String toString() {
        return playerName + " (" + super.getAppearance() + ")\n" + super.getHP() + "/15 HP";
    }
    
}

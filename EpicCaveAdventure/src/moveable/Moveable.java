/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moveable;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jaakko
 */
public class Moveable {
    
    private String name;
    private int x;
    private int y;    
    private int hp;
    private int maxHP;
    private int damage;
    private char appearance;
    
    public Moveable(String name, int x, int y, HashMap<String, int[]> stats) {
        
        if(!stats.containsKey(name)) {
            throw new IllegalArgumentException("No such monster");
        }
        
        this.name = name;
        this.x = x;
        this.y = y;        
        this.hp = stats.get(name)[0];
        this.maxHP = this.hp;
        this.damage = stats.get(name)[1];
        
        if(name.equals("Player")) {
            this.appearance = "@".charAt(0);
        } else {
            this.appearance = Character.toLowerCase(name.charAt(0));
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getHP() {
        return this.hp;
    }
    
    public char getAppearance() {
        return this.appearance;
    }
    
    public void move(int direction) {
        if (direction == 0) {
            this.y--;
        } else if (direction == 1) {
            this.x++;
        } else if (direction == 2) {
            this.y++;
        } else if (direction == 3) {
            this.x--;
        }
    }
    
    public void takeDamage(int harm) {
        this.hp -= harm;
    }
    
    public int doDamage() {
        return this.damage;
    }
    
    public void heal() {
        if(this.hp < this.maxHP) {
            this.hp++;
        }
    }
    
    @Override
    public String toString() {
        return " (" + appearance + ")\n" + hp + "/" + maxHP + " HP x:" + this.x + " y:" + this.y;
    }
    
}

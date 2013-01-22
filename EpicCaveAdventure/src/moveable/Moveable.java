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
public class Moveable {
    
    private String name;
    private int x;
    private int y;    
    private int hp;
    private int maxHP;
    private int damage;
    private char appearance;
    
    private static final String[] NAMES = new String[]{
        "Player", "Hirviö"};
    
    public Moveable(String name, int x, int y) {
        boolean exists = false;
        for(int i = 0; i < NAMES.length; ++i) {
            if(NAMES[i].equals(name)) {
                exists = true;
            }
        }
        if(!exists) {
            throw new IllegalArgumentException("No such monster");
        }
        
        this.name = name;
        this.x = x;
        this.y = y;        
        this.hp = characterHP(name);
        this.maxHP = this.hp;
        this.damage = characterDamage(name);
        this.appearance = characterAppearance(name);
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
    
    private int characterHP(String type) {
        switch (type) {
            case "Hirviö":
                return 5;
            case "Player":
                return 15;
        }
        return -1;
    }
    
    private int characterDamage(String type) {
        switch (type) {
            case "Hirviö":
                return 2;
            case "Player":
                return 3;
        }
        return -1;
    }
    
    private char characterAppearance(String type) {
        String symbols = "@h";
        switch (type) {
            case "Hirviö":
                return symbols.charAt(1);
            case "Player":
                return symbols.charAt(0);
        }
        return "-".charAt(0);
    }
    
    @Override
    public String toString() {
        return " (" + appearance + ")\n" + hp + "/" + maxHP + " HP x:" + this.x + " y:" + this.y;
    }
    
}

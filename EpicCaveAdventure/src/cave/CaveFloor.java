/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import moveable.Monster;

/**
 *
 * @author Jaakko
 */
public class CaveFloor {
    
    private int width;
    private int height;
    private List<Monster> monsters;    
    
    public CaveFloor(int width, int height) {
        this.width = width;
        this.height = height;
        this.monsters = new ArrayList<>();
        createMonsters();
    }
    
    public void drawFloor() {
        for(int i = 0; i < this.height; ++i) {
            drawRow(i);
            System.out.println();
        }        
    }
    
    public void drawRow(int layer) {
        for(int o = 0; o < this.width; ++o) {
            boolean placeTaken = false;
            for(Monster m : this.monsters) {
                if(m.getY() == layer && m.getX() == o) {
                    System.out.print(m.getAppearance());
                    placeTaken = true;
                }
            }
            if(!placeTaken) {
                System.out.print(".");
            }
        }
    }
    
    private void createMonsters() {
        Random randomizer = new Random();        
        
        for(int i = 0; i < 5; ++i) {
            int x = randomizer.nextInt(this.width);
            int y = randomizer.nextInt(this.height);
            boolean placeTaken = false;
            
            for(Monster m : this.monsters) {
                if(m.getX() == x && m.getY() == y) {
                    placeTaken = true;
                    --i;
                    break;
                }
            }
            
            if(!placeTaken) {
                this.monsters.add(new Monster("Hirviö", x, y));
            }
        }
    }
    
    public void listMonsters() {
        for(Monster m : this.monsters) {
            System.out.println(m);
        }
    }
    
}
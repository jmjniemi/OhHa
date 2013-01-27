/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import moveable.Monster;
import moveable.Player;

/**
 *
 * @author Jaakko
 */
public class CaveFloor {
    
    private Random randomizer = new Random();
    
    private int width;
    private int height;
    private List<Monster> monsters;  
    private HashMap<String, int[]> moveableStats;
    private List<String> monsterNames;
    private Player player;
    private int[] stairs;
    
    public CaveFloor(int width, int height, HashMap<String, int[]> stats, ArrayList<String> monsterNames) {
        
        if((width * height < 10) || width < 1 || height < 1) {
            throw new IllegalArgumentException("Floor too small");
        }
        
        this.width = width;
        this.height = height;
        this.moveableStats = stats;        
        this.monsterNames = monsterNames;        
        this.monsters = new ArrayList<>();
        
        this.player = new Player("Jugi", 0, 0, stats);
        this.stairs = new int[]{randomizer.nextInt(width-1)+1, randomizer.nextInt(height-1)+1};
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
            if(player.getY() == layer && player.getX() == o) {
                System.out.print("@");
                placeTaken = true;
            }
            if(stairs[1] == layer && stairs[0] == o) {
                System.out.print("/");
                placeTaken = true;
            }
            if(!placeTaken) {
                System.out.print(".");
            }
        }
    }
    
    private void createMonsters() {        
        
        for(int i = 0; i < 10; ++i) {
            int x;
            int y;
            
            while(true) {
                x = randomizer.nextInt(this.width);
                y = randomizer.nextInt(this.height);
                
                if(!(x == 0 && y == 0) && !(x == stairs[0] && y == stairs[1])) {
                    break;
                }
            }
            
            boolean placeTaken = false;            
            
            for(Monster m : this.monsters) {
                if(m.getX() == x && m.getY() == y) {
                    placeTaken = true;
                    --i;
                    break;
                }
            }
            
            if(!placeTaken) {   
                String m = monsterNames.get(randomizer.nextInt(monsterNames.size()));
                this.monsters.add(new Monster(m, x, y, this.moveableStats));
            }
        }
    }
    
    public void listMonsters() {
        for(Monster m : this.monsters) {
            System.out.println(m);
        }        
    }
    
    public void movePlayer(String d) {
        if(d.equals("w")) {
            this.player.move(0);
        } else if(d.equals("a")) {
            this.player.move(3);
        } else if(d.equals("s")) {
            this.player.move(2);
        } else if(d.equals("d")) {
            this.player.move(1);
        }
    }
    
    
    
}

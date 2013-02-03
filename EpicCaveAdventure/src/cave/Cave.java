/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import moveable.Player;

/**
 *
 * @author Jaakko
 */
public class Cave {
    
    private Scanner scanner;
    
    private CaveFloor currentFloor;
    private Map<String, int[]> stats;
    private List<String> monsterNames;
    private Player player;
    
    public Cave(HashMap<String, int[]> stats, ArrayList<String> monsterNames) {
        this.scanner = new Scanner(System.in);
        this.currentFloor = new CaveFloor(20, 10, stats, monsterNames);
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void run() {
        menu();
        
        while(true) {
            this.currentFloor.drawFloor();
            
            String playerDirection = scanner.next();   
            
            if(playerDirection.equals("exit")) {
                break;
            }
        
            turn(playerDirection);
        }
    }
    
    private void menu() {
        System.out.println("Welcome to play Epic Cave Adventure!");
        System.out.println("Move with wasd, type \"exit\" to quit.");
        System.out.println("Find your way out, good luck!\n");
    }
    
    private void turn(String dir) {
//        this.currentFloor.movePlayer(dir);
        
        currentFloor.moveMonsters();        
    }
    
}

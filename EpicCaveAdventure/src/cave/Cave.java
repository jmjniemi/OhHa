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

/**
 *
 * @author Jaakko
 */
public class Cave {
    
    private Scanner scanner;
    
    private CaveFloor currentFloor;
    private Map<String, int[]> stats;
    private List<String> monsterNames;
    
    public Cave(HashMap<String, int[]> stats, ArrayList<String> monsterNames) {
        this.scanner = new Scanner(System.in);
        this.currentFloor = new CaveFloor(20, 10, stats, monsterNames);
    }
    
    public void run() {
        while(true) {
            this.currentFloor.drawFloor();
        
            turn();
        }
    }
    
    public void turn() {
        String playerDirection = scanner.next();        
        
        if(playerDirection.equals("w") || playerDirection.equals("a") || playerDirection.equals("s") || playerDirection.equals("d")) {
            this.currentFloor.movePlayer(playerDirection);
        }
        
        
    }
    
}

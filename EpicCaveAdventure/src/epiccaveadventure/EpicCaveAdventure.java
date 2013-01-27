/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epiccaveadventure;

import cave.Cave;
import cave.CaveFloor;
import java.util.ArrayList;
import java.util.HashMap;
import moveable.*;

public class EpicCaveAdventure {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, int[]> moveableStats = new HashMap<>();
        moveableStats.put("Player", new int[]{15,3});
        moveableStats.put("Hirviö", new int[]{5,2});
        moveableStats.put("Goblin", new int[]{2,1});
        moveableStats.put("Orc", new int[]{6,3});
        moveableStats.put("Troll", new int[]{10,5});
        
        ArrayList<String> monsterNames = new ArrayList<>();
        monsterNames.add("Goblin");
        monsterNames.add("Orc");  
        monsterNames.add("Troll");        
        
        Cave pitti = new Cave(moveableStats, monsterNames);
        
        pitti.run();
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epiccaveadventure;

import cave.CaveFloor;
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
        
        
        CaveFloor pitti = new CaveFloor(20, 10, moveableStats);
        
        pitti.drawFloor();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epiccaveadventure;

import cave.CaveFloor;
import moveable.*;

public class EpicCaveAdventure {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        CaveFloor pitti = new CaveFloor(10, 10);
        
        pitti.drawFloor();
    }
}

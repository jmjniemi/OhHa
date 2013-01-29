/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cave.CaveFloor;
import java.util.ArrayList;
import java.util.HashMap;
import moveable.Monster;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class CaveTest {
    
    CaveFloor floor;
    private HashMap<String, int[]> moveableStats;
    ArrayList<String> monsterNames;
    
    
    public CaveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        moveableStats = new HashMap<>();
        moveableStats.put("Player", new int[]{15,3});
        moveableStats.put("Hirviö", new int[]{5,2});
        moveableStats.put("Goblin", new int[]{2,1});
        moveableStats.put("Orc", new int[]{6,3});
        moveableStats.put("Troll", new int[]{10,5});
        
        monsterNames = new ArrayList<>();
        monsterNames.add("Goblin");
        monsterNames.add("Orc");  
        monsterNames.add("Troll");
        
        floor = new CaveFloor(20, 10, moveableStats, monsterNames);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test (expected=IllegalArgumentException.class)
    public void constructorWorks() {
        CaveFloor liiskaaja = new CaveFloor(6, 0, moveableStats, monsterNames);
        
        CaveFloor seitsemasUlottuvuus = new CaveFloor(-3, -8, moveableStats, monsterNames);
        
        CaveFloor kolo = new CaveFloor(3, 1, moveableStats, monsterNames);
    }   
    
    @Test
    public void allMonstersCreated() {
        assertEquals(floor.getMonsters().size(), 10);
    }
    
    @Test
    public void noSameCoordinates() {
        boolean sameXY = false;
        for(Monster m : floor.getMonsters()) {
            if(m.getX() == floor.getPlayer().getX() && m.getY() == floor.getPlayer().getY()) {
                sameXY = true;
            }
            for(Monster o : floor.getMonsters()) {
                int h = 0;
                if(m.getX() == o.getX() && m.getY() == o.getY()) {
                    h++;
                }
                if(h > 1) {
                    sameXY = true;
                }
            }
        }
        assertFalse("Two movables with same coordinates", sameXY);
    }
    
    @Test
    public void movingWorks() {
        floor.movePlayer("d");
        floor.moveMonsters();
        
        noSameCoordinates();
    }
    
    @Test
    public void playerMoveWorks() {
        floor.movePlayer("pohjoinen");
        assertEquals(floor.getPlayer().getX(), 0);
        assertEquals(floor.getPlayer().getY(), 0);
        
        floor.movePlayer("q");
        assertEquals(floor.getPlayer().getX(), 0);
        assertEquals(floor.getPlayer().getY(), 0);
    }
    
}

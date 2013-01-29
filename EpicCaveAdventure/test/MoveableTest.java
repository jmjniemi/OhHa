/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import moveable.Monster;
import moveable.Moveable;
import moveable.Player;
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
public class MoveableTest {
    
    Monster eki;
    HashMap<String, int[]> moveableStats;
    ArrayList<String> monsterNames;
    
    public MoveableTest() {
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
        
        eki = new Monster("Hirviö", 1, 1, moveableStats);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//     @Test
//     public void hello() {}
    @Test
    public void constructorWorks() {         
        String status = eki.toString();
        
        assertEquals("Hirviö (h)\n5/5 HP x:1 y:1", status);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void BadNames() {
        Moveable hane = new Moveable("Hane", 1, 1, moveableStats);        
    }
    
    @Test
    public void takesDamage() {
        eki.takeDamage(3);
        
        assertEquals(eki.getHP(), 2);
    }
    
    @Test
    public void doesDamage() {
        assertEquals(eki.doDamage(), 2);
    }
    
    @Test
    public void healWorks() {
        Player pena = new Player("Pena", 1, 1, moveableStats);
        
        pena.takeDamage(2);
        
        for(int i = 0; i < 10; ++i) {
            pena.move(0);
        }
        
        assertEquals(pena.getHP(), 14);
        
        for(int i = 0; i < 9; ++i) {
            pena.move(0);
        }
        
        assertEquals(pena.getHP(), 14);        
    }
    
    @Test
    public void moveWorks() {            
                
        eki.move(0);
        
        assertEquals(eki.getX(), 1);
        assertEquals(eki.getY(), 0);
        
        eki.move(1);
        
        assertEquals(eki.getX(), 2);
        assertEquals(eki.getY(), 0);
        
        eki.move(2);
        
        assertEquals(eki.getX(), 2);
        assertEquals(eki.getY(), 1);
        
        eki.move(3);
        
        assertEquals(eki.getX(), 1);
        assertEquals(eki.getY(), 1);
        
        eki.move(4);
        
        assertEquals(eki.getX(), 1);
        assertEquals(eki.getY(), 1);
        
        eki.move(-1);
        
        assertEquals(eki.getX(), 1);
        assertEquals(eki.getY(), 1);
    }
}

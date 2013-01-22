/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cave.CaveFloor;
import java.util.HashMap;
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
        HashMap<String, int[]> moveableStats = new HashMap<>();
        moveableStats.put("Player", new int[]{15,3});
        moveableStats.put("Hirviö", new int[]{5,2});
        
        floor = new CaveFloor(20, 10, moveableStats);
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
        CaveFloor liiskaaja = new CaveFloor(6, 0, moveableStats);
        
        CaveFloor seitsemasUlottuvuus = new CaveFloor(-3, -8, moveableStats);
        
        CaveFloor kolo = new CaveFloor(3, 1, moveableStats);
    }   
    
}

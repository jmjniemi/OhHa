/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.functionality;

import java.util.Random;
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
public class FieldGeneratorTest {
    
    FieldGenerator testFG;
    int[][] minefield;
    MyRandom myrandom;
    
    public FieldGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        myrandom = new MyRandom();
        minefield = new int[][] {{19, 11, 10}, {11, 10, 10}, {10, 10, 10}};
        testFG = new FieldGenerator(minefield, 3, 3, 1, myrandom);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddCount() {
        
        testFG.changeCount(0, 0);
        assertEquals(19, minefield[0][0]);
        
        testFG.changeCount(0, 1);
        testFG.changeCount(0, 1);
        testFG.changeCount(0, 1);
        
        assertEquals(14, minefield[0][1]);
    }
    @Test
    public void testInitialize() {
        testFG.initialize();
        
        assertEquals(10, minefield[0][0]);
    }
}

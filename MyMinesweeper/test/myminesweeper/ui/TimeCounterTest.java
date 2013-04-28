/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myminesweeper.ui;

import javax.swing.JLabel;
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
public class TimeCounterTest {
    
    private TimeCounter tc;
    
    public TimeCounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tc = new TimeCounter(new JLabel(""));
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void timeTest() {
        assertEquals("Aika ei ala nollasta", 0, tc.getTime());
        
        tc.actionPerformed(null);
        
        assertEquals("Aikalaskuri ei päivity", 1, tc.getTime());
        
        for(int i = 0; i < 998; i++) {
            tc.actionPerformed(null);
        }
        
        assertEquals("Aikalaskuri ei saavuta 999", 999, tc.getTime());
        
        tc.actionPerformed(null);
        
        assertEquals("Aikalaskuri menee yli 999", 999, tc.getTime());
        
        tc.resetTime();
        
        assertEquals("Ajan resetointi ei toimi", 0, tc.getTime());
    }
}

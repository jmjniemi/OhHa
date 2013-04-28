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
public class ClickActionsTest {
    
    Field hardField;
    MyRandom myrandom;
    ClickActions ca;
    
    public ClickActionsTest() {
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
        
        hardField = new Field(20, 20, 10, myrandom);
        
        hardField.createField();
        
        ca = new ClickActions(hardField);
        ca.setMinefield(hardField.getField());
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testRightClick1() {
        int[][] f = hardField.getField();
        String text;
        
        text = ca.rightClick(0, 0);
        
        assertEquals("RightClick ei toimi miinattomaan ruutuun", 21, f[0][0]);
        assertEquals("Statusbar ei päivity", "9", text);
        
        text = ca.rightClick(0, 0);
        
        assertEquals("RightClick uudestaan ei toimi miinattomaan ruutuun", 11, f[0][0]);
        assertEquals("Statusbar ei päivity takaisinpäin", "10", text);
        
        text = ca.rightClick(0, 1);
        
        assertEquals("RightClick ei toimi miinaruutuun", 29, f[0][1]);
        assertEquals("Statusbar ei päivity", "9", text);
        
        text = ca.rightClick(-123, 3453);
        assertEquals("Statusbar päivittyy vikaklikkauksilla", "9", text);
    }
    
    @Test
    public void testRightClick2() {
        int[][] f = hardField.getField();
        String text = null;
        
        for (int i = 0; i < 10; i++) {
            text = ca.rightClick(i, 0);
        }
        
        assertEquals("Statusbar ei päivity", "0", text);
        
        text = ca.rightClick(1, 1);
        
        assertEquals("Merkityt miinat menevät negat. puolelle", 0, hardField.getMinesLeft());
        assertEquals("Statusbar ei huomioi merkkausten loppumista", "No marks left", text);
    }
    
    @Test
    public void testLeftClick1() {
        int[][] f = hardField.getField();
        
        ca.leftClick(0, 0);
        assertEquals("Miinattoman ruudun klikkaus ei toimi", 1, f[0][0]);
        
        ca.rightClick(0, 5);
        ca.leftClick(0, 5);
        assertEquals("Merkittyä ruutua pystyy klikkaamaan", 20, f[0][5]);
        
        ca.leftClick(0, 1);
        assertEquals("Miinaruutua ei pysty klikkaamaan", 9, f[0][1]);
        assertEquals("Pelin status ei päivity", false, hardField.getStatus());
    }
    @Test
    public void testLeftClick2() {
        int[][] f = hardField.getField();
        
        ca.leftClick(0, 0);
        
        ca.leftClick(0, 0);
        assertEquals("MassClick ei toimi", 19, f[0][1]);
        assertEquals("MassClick ei toimi", 11, f[1][0]);
        
        ca.rightClick(0, 1);
        ca.leftClick(0, 0);
        ca.leftClick(0, 0);
        assertEquals("Uusi klikkaus muuttaa arvoa", 1, f[1][0]);
    }
    
    @Test
    public void testCountAdjacentMines() {
        int[][] f = hardField.getField();
        
        ca.rightClick(0, 1);
        ca.rightClick(2, 3);
        
        int mines = ca.countAdjacentMines(1, 2);
        assertEquals("Viereisten miinojen laskeminen ei toimi", 2, mines);
        
        ca.rightClick(0, 2);
        ca.rightClick(0, 3);
        ca.rightClick(1, 1);
        ca.rightClick(1, 3);
        ca.rightClick(2, 1);
        ca.rightClick(2, 2);
        
        mines = ca.countAdjacentMines(1, 2);
        assertEquals("Viereisten miinojen laskeminen ei yllä 8 asti", 8, mines);
    }
}

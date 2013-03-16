package myminesweeper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class FieldTest {
    
    public FieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Field testField = new Field();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testHeight() {
        Field testField = new Field();
        assertEquals(16, testField.getHeight());
        
        Field a = new Field(8, 10, 10);
        assertEquals(9, a.getHeight());
        
        Field b = new Field(-10, 10, 10);
        assertEquals(9, b.getHeight());
        
        Field c = new Field(51, 10, 10);
        assertEquals(50, c.getHeight());
        
        Field d = new Field(234234, 10, 10);
        assertEquals(50, d.getHeight());
    }
    
    @Test
    public void testWidth() {
        Field testField = new Field();
        assertEquals(30, testField.getWidth());
        
        Field a = new Field(7, 8, 10);
        assertEquals(9, a.getWidth());
        
        Field b = new Field(10, -10, 1233);
        assertEquals(9, b.getWidth());
        
        Field c = new Field(10, 51, 7);
        assertEquals(50, c.getWidth());
        
        Field d = new Field(10, 345343, 10);
        assertEquals(50, d.getWidth());
    }
    
    @Test
    public void testMines() {
        Field testField = new Field();
        assertEquals(99, testField.getMines());
        
        Field a = new Field(10, 10, 9);
        assertEquals(10, a.getMines());
        
        Field b = new Field(10, 10, -10);
        assertEquals(10, b.getMines());
        
        Field c = new Field(10, 10, 51);
        assertEquals(50, c.getMines());
        
        Field d = new Field(10, 10, 3452);
        assertEquals(50, d.getMines());
    }
    @Test
    public void coveredBeginning() { //testaa, että alussa kaikki ruudut on peitetty
        Field testField = new Field();
        testField.createField();
        
        int[][] field = testField.getField();
        boolean uncovered = false;
        
        for (int i = 0; i < testField.getHeight(); i++) {
            for (int j = 0; j < testField.getWidth(); j++) {
                if (field[i][j] < 10) {
                    uncovered = true;
                }
            }
        }
        assertEquals(false, uncovered);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}

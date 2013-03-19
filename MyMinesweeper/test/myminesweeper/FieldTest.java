package myminesweeper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
public class FieldTest {
    
    Field testField;
    Field hardField;
    MyRandom myrandom;
    Random random;
    
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
        random = new Random();
        myrandom = new MyRandom();
        
        testField = new Field(random);
        hardField = new Field(20, 20, 10, myrandom);
        
        testField.createField();
        hardField.createField();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testHeight() {        
        assertEquals(16, testField.getHeight());
        
        Field a = new Field(8, 10, 10, random);
        assertEquals(9, a.getHeight());
        
        Field b = new Field(-10, 10, 10, random);
        assertEquals(9, b.getHeight());
        
        Field c = new Field(51, 10, 10, random);
        assertEquals(50, c.getHeight());
        
        Field d = new Field(234234, 10, 10, random);
        assertEquals(50, d.getHeight());
    }
    
    @Test
    public void testWidth() {        
        assertEquals(30, testField.getWidth());
        
        Field a = new Field(7, 8, 10, random);
        assertEquals(9, a.getWidth());
        
        Field b = new Field(10, -10, 1233, random);
        assertEquals(9, b.getWidth());
        
        Field c = new Field(10, 51, 7, random);
        assertEquals(50, c.getWidth());
        
        Field d = new Field(10, 345343, 10, random);
        assertEquals(50, d.getWidth());
    }
    
    @Test
    public void testMines() {        
        assertEquals(99, testField.getMines());
        
        Field a = new Field(10, 10, 9, random);
        assertEquals(10, a.getMines());
        
        Field b = new Field(10, 10, -10, random);
        assertEquals(10, b.getMines());
        
        Field c = new Field(10, 10, 51, random);
        assertEquals(50, c.getMines());
        
        Field d = new Field(10, 10, 3452, random);
        assertEquals(50, d.getMines());
    }
    @Test
    public void coveredBeginning() { //testaa, että alussa kaikki ruudut on peitetty        
        int[][] f = testField.getField();
        boolean uncovered = false;
        
        for (int i = 0; i < testField.getHeight(); i++) {
            for (int j = 0; j < testField.getWidth(); j++) {
                if (f[i][j] < 10) {
                    uncovered = true;
                }
            }
        }
        assertEquals(false, uncovered);
    }
    
    @Test
    public void testCounters() {
        int[][] f = hardField.getField();
        
        assertEquals(19, f[0][1]);
        
        assertEquals(11, f[0][0]);
        
        assertEquals(12, f[1][2]);
    }
    
    @Test
    public void testAddCount() {
        int[][] f = hardField.getField();
        
        hardField.addCount(0, 1);
        assertEquals(19, f[0][1]);
        
        hardField.addCount(0, 0);
        hardField.addCount(0, 0);
        hardField.addCount(0, 0);
        
        assertEquals(14, f[0][0]);
    }
    
    @Test
    public void testDrawing() throws IOException {        
        String drawn = "1*1.................\r\n" +
                       "11211...............\r\n" +
                       "..1*1...............\r\n" +
                       "..11211.............\r\n" +
                       "....1*1.............\r\n" +
                       "....11211...........\r\n" +
                       "......1*1...........\r\n" +
                       "......11211.........\r\n" +
                       "........1*1.........\r\n" +
                       "........11211.......\r\n" +
                       "..........1*1.......\r\n" +
                       "..........11211.....\r\n" +
                       "............1*1.....\r\n" +
                       "............11211...\r\n" +
                       "..............1*1...\r\n" +
                       "..............11211.\r\n" +
                       "................1*1.\r\n" +
                       "................1121\r\n" +
                       "..................1*\r\n" +
                       "..................11\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        hardField.drawMinefield();
        baos.flush();
        String whatWasPrinted = new String(baos.toByteArray());
        
        assertEquals(drawn, whatWasPrinted);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}

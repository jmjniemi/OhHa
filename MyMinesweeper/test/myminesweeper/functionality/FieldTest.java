package myminesweeper.functionality;

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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        assertEquals("Default-korkeus väärä", 16, testField.getHeight());
        
        Field a = new Field(8, 10, 10, random);
        assertEquals("Korkeuden minimi ei toimi", 9, a.getHeight());
        
        Field b = new Field(-10, 10, 10, random);
        assertEquals("Negatiivinen korkeus", 9, b.getHeight());
        
        Field c = new Field(31, 10, 10, random);
        assertEquals("Korkeuden maksimi ei toimi", 30, c.getHeight());
        
        Field d = new Field(234234, 10, 10, random);
        assertEquals("Suuret luvut sekoittavat korkeuden", 30, d.getHeight());
    }
    
    @Test
    public void testWidth() {        
        assertEquals("Default-korkeus väärä", 30, testField.getWidth());
        
        Field a = new Field(7, 8, 10, random);
        assertEquals("Leveyden minimi ei toimi", 9, a.getWidth());
        
        Field b = new Field(10, -10, 1233, random);
        assertEquals("Negatiivinen leveys", 9, b.getWidth());
        
        Field c = new Field(10, 31, 7, random);
        assertEquals("Leveyden maksimi ei toimi", 30, c.getWidth());
        
        Field d = new Field(10, 345343, 10, random);
        assertEquals("Suuret luvut sekoittavat leveyden", 30, d.getWidth());
    }
    
    @Test
    public void testMines() {        
        assertEquals("Default-miinamäärä väärä", 99, testField.getMines());
        
        Field a = new Field(10, 10, 9, random);
        assertEquals("Miinojen minimi ei toimi", 10, a.getMines());
        
        Field b = new Field(10, 10, -10, random);
        assertEquals("Negatiivinen miinamäärä", 10, b.getMines());
        
        Field c = new Field(10, 10, 51, random);
        assertEquals("Miinojen maksimi ei toimi", 50, c.getMines());
        
        Field d = new Field(10, 10, 3452, random);
        assertEquals("Suuret luvut sekoittavat miinamäärän", 50, d.getMines());
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
        assertEquals("Alussa paljastettuja ruutuja", false, uncovered);
    }
    
    @Test
    public void testCounters() {
        int[][] f = hardField.getField();
        
        assertEquals("Miinan asettaminen ei toimi", 19, f[0][1]);
        
        assertEquals("Miinojen asettaminen ei muuta ympäröiviä arvoja", 11, f[0][0]);
        
        assertEquals("Miinojen asettaminen ei muuta ympäröiviä arvoja", 12, f[1][2]);
    }
    
    @Test
    public void testSquareMarkedMethod() {
        assertEquals("Jäljelläolevien miinojen määrä alussa väärä", 99, testField.getMinesLeft());
        
        testField.squareMarked(true);
        
        assertEquals("Jäljelläolevien miinojen päivitys ei toimi", 98, testField.getMinesLeft());
        
        testField.squareMarked(false);
        
        assertEquals("Jäljelläolevien miinojen päivitys takaisinpäin ei toimi", 99, testField.getMinesLeft());
    }
    
    @Test
    public void testUncover() {        
        int[][] f = hardField.getField();
        
        assertEquals("Alkuarvo ruudulle väärä", 11, f[0][0]);        
        hardField.uncover(0, 0);        
        assertEquals("Miinattoman ruudun paljastaminen ei toimi", 1, f[0][0]);        
        hardField.uncover(0, 0);        
        assertEquals("Miinattoman ruudun paljastaminen uudestaan sekoittaa", 1, f[0][0]);
        
        assertEquals("Alkuarvo miinalle väärä", 19, f[0][1]);        
        hardField.uncover(0, 1);        
        assertEquals("Miinan paljastaminen ei toimi", 9, f[0][1]);
        
        hardField.mark(0, 2);
        hardField.uncover(0, 2);
        assertEquals("Merkityn ruudun voi paljastaa", 21, f[0][2]);
    }
    
    @Test
    public void testMarkAndUnmark() {
        int[][] f = hardField.getField();
        
        //Empty square
        assertEquals("Tyhän ruudun alkuarvo väärä", 10, f[0][3]);
        hardField.mark(0, 3);
        assertEquals("Tyhjän ruudun merkitseminen ei toimi", 20, f[0][3]);
        hardField.mark(0, 3);
        assertEquals("Merkityn tyhjän ruudun voi merkitä uudelleen", 20, f[0][3]);
        
        hardField.unmark(0, 3);
        assertEquals("Merkittyn tyhjän ruudun merkintää ei voi poistaa", 10, f[0][3]);
        hardField.unmark(0, 3);
        assertEquals("Merkkaamattoman tyhjän ruudun merkinnän poiston yritys muuttaa arvoa", 10, f[0][3]);
        
        //Mine square
        assertEquals("Miinaruudun alkuarvo väärä", 19, f[0][1]);
        hardField.mark(0, 1);
        assertEquals("Miinaruudun merkitseminen ei toimi", 29, f[0][1]);
        hardField.mark(0, 1);
        assertEquals("Merkityn miinaruudun voi merkitä uudelleen", 29, f[0][1]);
        
        hardField.unmark(0, 1);
        assertEquals("Merkittyn miinaruudun merkintää ei voi poistaa", 19, f[0][1]);
        hardField.unmark(0, 1);
        assertEquals("Merkkaamattoman miinaruudun merkinnän poiston yritys muuttaa arvoa", 19, f[0][1]);
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
        
        assertEquals("Miinakentän testitulostus ei toimi", drawn, whatWasPrinted);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}

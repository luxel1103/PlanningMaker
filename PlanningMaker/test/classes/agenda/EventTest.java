/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lesley
 */
public class EventTest {
    
    public EventTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setBeginTijd method, of class Event.
     */
    @Test
    public void testSetBeginTijd() {
        System.out.println("setBeginTijd");
        Date beginTijd = null;
        Event instance = null;
        instance.setBeginTijd(beginTijd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeginTijd method, of class Event.
     */
    @Test
    public void testGetBeginTijd() {
        System.out.println("getBeginTijd");
        Event instance = null;
        Date expResult = null;
        Date result = instance.getBeginTijd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

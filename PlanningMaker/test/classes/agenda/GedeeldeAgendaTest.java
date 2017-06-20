/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
import java.util.List;
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
public class GedeeldeAgendaTest {
    
    public GedeeldeAgendaTest() {
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
     * Test of setLeden method, of class GedeeldeAgenda.
     */
    @Test
    public void testSetLeden() {
        System.out.println("setLeden");
        List<Account> leden = null;
        GedeeldeAgenda instance = null;
        instance.setLeden(leden);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeden method, of class GedeeldeAgenda.
     */
    @Test
    public void testGetLeden() {
        System.out.println("getLeden");
        GedeeldeAgenda instance = null;
        List<Account> expResult = null;
        List<Account> result = instance.getLeden();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

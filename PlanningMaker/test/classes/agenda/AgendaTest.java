/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.util.Date;
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
public class AgendaTest {
    
    public AgendaTest() {
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
     * Test of getId method, of class Agenda.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Agenda instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTijdEnDatum method, of class Agenda.
     */
    @Test
    public void testGetTijdEnDatum() {
        System.out.println("getTijdEnDatum");
        Agenda instance = null;
        Date expResult = null;
        Date result = instance.getTijdEnDatum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTijdEnDatum method, of class Agenda.
     */
    @Test
    public void testSetTijdEnDatum() {
        System.out.println("setTijdEnDatum");
        Date tijdEnDatum = null;
        Agenda instance = null;
        instance.setTijdEnDatum(tijdEnDatum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNaam method, of class Agenda.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        Agenda instance = null;
        String expResult = "";
        String result = instance.getNaam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNaam method, of class Agenda.
     */
    @Test
    public void testSetNaam() {
        System.out.println("setNaam");
        String naam = "";
        Agenda instance = null;
        instance.setNaam(naam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAgendaItem method, of class Agenda.
     */
    @Test
    public void testAddAgendaItem() {
        System.out.println("addAgendaItem");
        AgendaItem item = null;
        Agenda instance = null;
        boolean expResult = false;
        boolean result = instance.addAgendaItem(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAgendaItems method, of class Agenda.
     */
    @Test
    public void testAddAgendaItems() {
        System.out.println("addAgendaItems");
        List<AgendaItem> items = null;
        Agenda instance = null;
        instance.addAgendaItems(items);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgendaItems method, of class Agenda.
     */
    @Test
    public void testGetAgendaItems() {
        System.out.println("getAgendaItems");
        Agenda instance = null;
        List<AgendaItem> expResult = null;
        List<AgendaItem> result = instance.getAgendaItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAgendaItem method, of class Agenda.
     */
    @Test
    public void testRemoveAgendaItem() {
        System.out.println("removeAgendaItem");
        AgendaItem item = null;
        Agenda instance = null;
        instance.removeAgendaItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAlleAgendaItems method, of class Agenda.
     */
    @Test
    public void testRemoveAlleAgendaItems() {
        System.out.println("removeAlleAgendaItems");
        Agenda instance = null;
        instance.removeAlleAgendaItems();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

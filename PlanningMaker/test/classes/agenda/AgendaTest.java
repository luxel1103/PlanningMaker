/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    Agenda testinstance;
    AgendaItem testTaak1;
    AgendaItem testTaak2;
    AgendaItem testEvent1;
    AgendaItem testEvent2;
    
    public AgendaTest() {
    }
    
    @Before
    public void setUp() throws ParseException {
        //aanmaken van de test agenda
        testinstance = new Agenda(1,"testagenda naam");
        
        //aanmaken van de test agenda items
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-12-2017 10:20:30";
        String dateInStringeind = "22-12-2017 18:20:30";
        Date eindTijd = sdf1.parse(dateInString);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date beginTijd = sdf2.parse(dateInStringeind);
        
        testTaak1 = new Taak(1, "test taak1", "test beschrijving", eindTijd);
        testTaak2 = new Taak(2, "test taak2", "test beschrijving", eindTijd);
        testEvent1 = new Event(3, "test event1", "test beschrijving", beginTijd, eindTijd);
        testEvent2 = new Event(4, "test event2", "test beschrijving", beginTijd, eindTijd);
        
        //toevoegen van 1 taak en 1 event
        List<AgendaItem> items = new ArrayList<>();
        items.add(testTaak1);
        items.add(testEvent1);
        testinstance.addAgendaItems(items);
    }
    
    @After
    public void tearDown() {
        testinstance = null;
        testTaak1 = null;
        testTaak2 = null;
        testEvent1 = null;
        testEvent2 = null;
    }

    /**
     * Test of getId method, of class Agenda.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Agenda instance = testinstance;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getTijdEnDatum method, of class Agenda.
//     */
//    @Test
//    public void testGetTijdEnDatum() {
//        System.out.println("getTijdEnDatum");
//        Agenda instance = testinstance;
//        Date expResult = new Date();
//        Date result = instance.getTijdEnDatum();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getNaam method, of class Agenda.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        Agenda instance = testinstance;
        String expResult = "testagenda naam";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNaam method, of class Agenda.
     */
    @Test
    public void testSetNaam() {
        System.out.println("setNaam");
        String naam = "nieuwe naam";
        Agenda instance = testinstance;
        instance.setNaam(naam);
        String expResult = "nieuwe naam";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setNaam method, of class Agenda with an empty name
     */
    @Test
    public void testSetNaamIncorrect() {
        System.out.println("setNaam");
        String naam = "";
        Agenda instance = testinstance;
        instance.setNaam(naam);
        String expResult = "testagenda naam";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAgendaItems method, of class Agenda.
     */
    @Test
    public void testGetAgendaItems() {
        System.out.println("getAgendaItems");
        Agenda instance = testinstance;
        int expResult = 2;
        int result = instance.getAgendaItems().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAgendaItem method, of class Agenda.
     */
    @Test
    public void testAddAgendaItem() {
        System.out.println("addAgendaItem");
        AgendaItem item1 = testTaak2;
        AgendaItem item2 = testEvent2;
        Agenda instance = testinstance;
        boolean expResult1 = true;
        int expResult2 = 4;
        boolean result1 = instance.addAgendaItem(item1);
        boolean result2 = instance.addAgendaItem(item2);
        int result3 = instance.getAgendaItems().size();
        assertEquals(expResult1, result1);
        assertEquals(expResult1, result2);
        assertEquals(expResult2, result3);
    }
    
    /**
     * Test of addAgendaItem method, of class Agenda with excisting agenda items
     */
    @Test
    public void testAddAgendaItemIncorrect() {
        System.out.println("addAgendaItem");
        AgendaItem item1 = testTaak1;
        AgendaItem item2 = testEvent1;
        Agenda instance = testinstance;
        boolean expResult1 = false;
        int expResult2 = 2;
        boolean result1 = instance.addAgendaItem(item1);
        boolean result2 = instance.addAgendaItem(item2);
        int result3 = instance.getAgendaItems().size();
        assertEquals(expResult1, result1);
        assertEquals(expResult1, result2);
        assertEquals(expResult2, result3);
    }

    /**
     * Test of addAgendaItems method, of class Agenda.
     */
    @Test
    public void testAddAgendaItems() {
        System.out.println("addAgendaItems");
        List<AgendaItem> items = new ArrayList<>();
        items.add(testTaak2);
        items.add(testEvent2);
        Agenda instance = testinstance;
        instance.addAgendaItems(items);
        int expResult = 4;
        int result = instance.getAgendaItems().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAgendaItems method, of class Agenda with excisting items
     */
    @Test
    public void testAddAgendaItemsIncorrect() {
        System.out.println("addAgendaItems");
        List<AgendaItem> items = new ArrayList<>();
        items.add(testTaak1);
        items.add(testEvent1);
        items.add(testTaak2);
        items.add(testEvent2);
        Agenda instance = testinstance;
        instance.addAgendaItems(items);
        int expResult = 4;
        int result = instance.getAgendaItems().size();
        assertEquals(expResult, result);
    }

    

    /**
     * Test of removeAgendaItem method, of class Agenda.
     */
    @Test
    public void testRemoveAgendaItem() {
        System.out.println("removeAgendaItem");
        AgendaItem item1 = testTaak1;
        AgendaItem item2 = testEvent1;
        Agenda instance = testinstance;
        instance.removeAgendaItem(item1);
        instance.removeAgendaItem(item2);
        int expResult = 0;
        int result = instance.getAgendaItems().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAlleAgendaItems method, of class Agenda.
     */
    @Test
    public void testRemoveAlleAgendaItems() {
        System.out.println("removeAlleAgendaItems");
        Agenda instance = testinstance;
        instance.removeAlleAgendaItems();
        int expResult = 0;
        int result = instance.getAgendaItems().size();
        assertEquals(expResult, result);
    }
    
}

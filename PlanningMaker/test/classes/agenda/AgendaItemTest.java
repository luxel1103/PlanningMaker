/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
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
public class AgendaItemTest {
    
    public AgendaItemTest() {
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
     * Test of getId method, of class AgendaItem.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AgendaItem instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNaam method, of class AgendaItem.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        AgendaItem instance = null;
        String expResult = "";
        String result = instance.getNaam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNaam method, of class AgendaItem.
     */
    @Test
    public void testSetNaam() {
        System.out.println("setNaam");
        String naam = "";
        AgendaItem instance = null;
        instance.setNaam(naam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeschrijving method, of class AgendaItem.
     */
    @Test
    public void testGetBeschrijving() {
        System.out.println("getBeschrijving");
        AgendaItem instance = null;
        String expResult = "";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBeschrijving method, of class AgendaItem.
     */
    @Test
    public void testSetBeschrijving() {
        System.out.println("setBeschrijving");
        String beschrijving = "";
        AgendaItem instance = null;
        instance.setBeschrijving(beschrijving);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEindTijd method, of class AgendaItem.
     */
    @Test
    public void testGetEindTijd() {
        System.out.println("getEindTijd");
        AgendaItem instance = null;
        Date expResult = null;
        Date result = instance.getEindTijd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEindTijd method, of class AgendaItem.
     */
    @Test
    public void testSetEindTijd() {
        System.out.println("setEindTijd");
        Date eindTijd = null;
        AgendaItem instance = null;
        instance.setEindTijd(eindTijd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenodigden method, of class AgendaItem.
     */
    @Test
    public void testGetGenodigden() {
        System.out.println("getGenodigden");
        AgendaItem instance = null;
        List<Account> expResult = null;
        List<Account> result = instance.getGenodigden();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenodigden method, of class AgendaItem.
     */
    @Test
    public void testSetGenodigden() {
        System.out.println("setGenodigden");
        List<Account> genodigden = null;
        AgendaItem instance = null;
        instance.setGenodigden(genodigden);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGenodigde method, of class AgendaItem.
     */
    @Test
    public void testAddGenodigde() {
        System.out.println("addGenodigde");
        Account genodigde = null;
        AgendaItem instance = null;
        boolean expResult = false;
        boolean result = instance.addGenodigde(genodigde);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeGenodigde method, of class AgendaItem.
     */
    @Test
    public void testRemoveGenodigde() {
        System.out.println("removeGenodigde");
        Account genodigde = null;
        AgendaItem instance = null;
        instance.removeGenodigde(genodigde);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComments method, of class AgendaItem.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        AgendaItem instance = null;
        List<Comment> expResult = null;
        List<Comment> result = instance.getComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComments method, of class AgendaItem.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        List<Comment> comments = null;
        AgendaItem instance = null;
        instance.setComments(comments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComment method, of class AgendaItem.
     */
    @Test
    public void testAddComment() {
        System.out.println("addComment");
        Comment comment = null;
        AgendaItem instance = null;
        instance.addComment(comment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

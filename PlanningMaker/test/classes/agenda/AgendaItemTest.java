/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
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
public class AgendaItemTest {
    
    AgendaItem testinstance;
    Date eindTijd;
    Account testAccount1;
    Account testAccount2;
    Account testAccount3;
    Comment testComment1;
    Comment testComment2;
    
    public AgendaItemTest() {
    }
    
    @Before
    public void setUp() throws ParseException {
        //aanmaken van de einddatum
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-12-2017 10:20:30";
        eindTijd = sdf.parse(dateInString);
        //aanmaken van het agendaitem dat getest gaat worden
        testinstance = new AgendaItem(1, "test item", "test beschrijving", eindTijd);
        //aanmaken van de testaccounts die nodig zijn voor deze tests
        testAccount1 = new Account(1,1,"testaccount 1","wachtwoord");
        testAccount2 = new Account(2,2,"testaccount 2","wachtwoord");
        testAccount3 = new Account(3,3,"testaccount 3","wachtwoord");
        List<Account> genodigden = new ArrayList<>();
        genodigden.add(testAccount1);
        genodigden.add(testAccount2);
        testinstance.setGenodigden(genodigden);
        //aanmaken van de comments die nodig zijn voor deze tests
        testComment1 = new Comment();
        testComment2 = new Comment();
        List<Comment> comments = new ArrayList<>();
        comments.add(testComment1);
        testinstance.setComments(comments);
    }
    
    @After
    public void tearDown() {
        testinstance = null;
        eindTijd = null;
        testAccount1 = null;
        testAccount2 = null;
        testAccount3 = null;
    }

    /**
     * Test of getId method, of class AgendaItem.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AgendaItem instance = testinstance;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNaam method, of class AgendaItem.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        AgendaItem instance = testinstance;
        String expResult = "test item";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNaam method, of class AgendaItem.
     */
    @Test
    public void testSetNaam() {
        System.out.println("setNaam");
        String naam = "nieuwe naam";
        AgendaItem instance = testinstance;
        instance.setNaam(naam);
        String expResult = "nieuwe naam";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setNaam method, of class AgendaItem with an empty string
     */
    @Test
    public void testSetNaamIncorrect() {
        System.out.println("setNaamIncorrect");
        String naam = "";
        AgendaItem instance = testinstance;
        instance.setNaam(naam);
        String expResult = "test item";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeschrijving method, of class AgendaItem.
     */
    @Test
    public void testGetBeschrijving() {
        System.out.println("getBeschrijving");
        AgendaItem instance = testinstance;
        String expResult = "test beschrijving";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeschrijving method, of class AgendaItem.
     */
    @Test
    public void testSetBeschrijving() {
        System.out.println("setBeschrijving");
        String beschrijving = "nieuwe beschrijving";
        AgendaItem instance = testinstance;
        instance.setBeschrijving(beschrijving);
        String expResult = "nieuwe beschrijving";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setBeschrijving method, of class AgendaItem with an empty string
     */
    @Test
    public void testSetBeschrijvingIncorrect() {
        System.out.println("setBeschrijving");
        String beschrijving = "";
        AgendaItem instance = testinstance;
        instance.setBeschrijving(beschrijving);
        String expResult = "test beschrijving";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEindTijd method, of class AgendaItem.
     */
    @Test
    public void testGetEindTijd() {
        System.out.println("getEindTijd");
        AgendaItem instance = testinstance;
        Date expResult = eindTijd;
        Date result = instance.getEindTijd();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEindTijd method, of class AgendaItem.
     */
    @Test
    public void testSetEindTijd() throws ParseException {
        System.out.println("setEindTijd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "15-10-2010 10:20:30";
        Date eindtijd = sdf.parse(dateInString);
        AgendaItem instance = testinstance;
        instance.setEindTijd(eindtijd);
        Date expResult = eindtijd;
        Date result = instance.getEindTijd();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getGenodigden method, of class AgendaItem.
     */
    @Test
    public void testGetGenodigden() {
        System.out.println("getGenodigden");
        AgendaItem instance = testinstance;
        int expResult = 2;
        int result = instance.getGenodigden().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setGenodigden method, of class AgendaItem.
     */
    @Test
    public void testSetGenodigden() {
        System.out.println("setGenodigden");
        List<Account> genodigden = new ArrayList<>();
        genodigden.add(testAccount1);
        genodigden.add(testAccount2);
        genodigden.add(testAccount3);
        AgendaItem instance = testinstance;
        instance.setGenodigden(genodigden);
        int expResult = 3;
        int result = instance.getGenodigden().size();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of addGenodigde method, of class AgendaItem.
     */
    @Test
    public void testAddGenodigde() {
        System.out.println("addGenodigde");
        Account genodigde = testAccount3;
        AgendaItem instance = testinstance;
        boolean expResult = true;
        boolean result = instance.addGenodigde(genodigde);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addGenodigde method, of class AgendaItem with a user that's already added
     */
    @Test
    public void testAddGenodigdeIncorrect() {
        System.out.println("addGenodigde");
        Account genodigde = testAccount2;
        AgendaItem instance = testinstance;
        boolean expResult = false;
        boolean result = instance.addGenodigde(genodigde);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeGenodigde method, of class AgendaItem.
     */
    @Test
    public void testRemoveGenodigde() {
        System.out.println("removeGenodigde");
        Account genodigde = testAccount2;
        AgendaItem instance = testinstance;
        instance.removeGenodigde(genodigde);
        int expResult = 1;
        int result = instance.getGenodigden().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComments method, of class AgendaItem.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        AgendaItem instance = testinstance;
        int expResult = 1;
        int result = instance.getComments().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of setComments method, of class AgendaItem.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        List<Comment> comments = new ArrayList<>();
        comments.add(testComment1);
        comments.add(testComment2);
        AgendaItem instance = testinstance;
        instance.setComments(comments);
        int expResult = 2;
        int result = instance.getComments().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addComment method, of class AgendaItem.
     */
    @Test
    public void testAddComment() {
        System.out.println("addComment");
        Comment comment = testComment2;
        AgendaItem instance = testinstance;
        instance.addComment(comment);
        int expResult = 2;
        int result = instance.getComments().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addComment method, of class AgendaItem with an excisting comment
     */
    @Test
    public void testAddCommentIncorrect() {
        System.out.println("addCommentIncorrect");
        Comment comment = testComment1;
        AgendaItem instance = testinstance;
        instance.addComment(comment);
        int expResult = 1;
        int result = instance.getComments().size();
        assertEquals(expResult, result);
    }
    
}

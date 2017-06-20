/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.GedeeldeAgenda;
import classes.agenda.PriveAgenda;
import java.util.ArrayList;
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
public class AccountTest {
    
    Account testaccount;
    Agenda testpriveagenda;
    Agenda testgedeeldeagenda;
    
    public AccountTest() {
    }
    
    @Before
    public void setUp() {
        testaccount = new Account(1,1,"lesley","wachtwoord");
        testpriveagenda = new PriveAgenda(1,"prive");
        testgedeeldeagenda = new GedeeldeAgenda(2,"gedeeld");
        testaccount.setPriveAgenda(testpriveagenda);
        testaccount.addGedeeldeAgenda(testgedeeldeagenda);
    }
    
    @After
    public void tearDown() {
        testaccount = null;
    }

    /**
     * Test of getId method, of class Account.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Account instance = testaccount;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriveAgendaId method, of class Account.
     */
    @Test
    public void testGetPriveAgendaId() {
        System.out.println("getPriveAgendaId");
        Account instance = testaccount;
        int expResult = 1;
        int result = instance.getPriveAgendaId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriveAgendaId method, of class Account.
     */
    @Test
    public void testSetPriveAgendaId() {
        System.out.println("setPriveAgendaId");
        int priveAgendaId = 2;
        Account instance = testaccount;
        instance.setPriveAgendaId(priveAgendaId);
        int expResult = 2;
        int result = instance.getPriveAgendaId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGebruikersnaam method, of class Account.
     */
    @Test
    public void testGetGebruikersnaam() {
        System.out.println("getGebruikersnaam");
        Account instance = testaccount;
        String expResult = "lesley";
        String result = instance.getGebruikersnaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGebruikersnaam method, of class Account.
     */
    @Test
    public void testSetGebruikersnaam() {
        System.out.println("setGebruikersnaam");
        String gebruikersnaam = "lesleypeters";
        Account instance = testaccount;
        instance.setGebruikersnaam(gebruikersnaam);
        String expResult = "lesleypeters";
        String result = instance.getGebruikersnaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWachtwoord method, of class Account.
     */
    @Test
    public void testGetWachtwoord() {
        System.out.println("getWachtwoord");
        Account instance = testaccount;
        String expResult = "wachtwoord";
        String result = instance.getWachtwoord();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWachtwoord method, of class Account.
     */
    @Test
    public void testSetWachtwoord() {
        System.out.println("setWachtwoord");
        String wachtwoord = "nieuwwachtwoord";
        Account instance = testaccount;
        instance.setWachtwoord(wachtwoord);
        String expResult = "nieuwwachtwoord";
        String result = instance.getWachtwoord();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriveAgenda method, of class Account.
     */
    @Test
    public void testSetPriveAgenda() {
        System.out.println("setPriveAgenda");
        Agenda agenda = new PriveAgenda(2,"prive");
        Account instance = testaccount;
        instance.setPriveAgenda(agenda);
        int expResult = agenda.getId();
        int result = instance.getPriveAgenda().getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriveAgenda method, of class Account.
     */
    @Test
    public void testGetPriveAgenda() {
        System.out.println("getPriveAgenda");
        Account instance = testaccount;
        Agenda expResult = testpriveagenda;
        Agenda result = instance.getPriveAgenda();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGedeeldeAgenda method, of class Account.
     */
    @Test
    public void testAddGedeeldeAgenda() {
        System.out.println("addGedeeldeAgenda");
        Agenda nieuweAgenda = new GedeeldeAgenda(3,"School");
        Account instance = testaccount;
        instance.addGedeeldeAgenda(nieuweAgenda);
        int expResult = 2;
        int result = instance.getGedeeldeAgendas().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGedeeldeAgendas method, of class Account.
     */
    @Test
    public void testAddGedeeldeAgendas() {
        System.out.println("addGedeeldeAgendas");
        List<Agenda> nieuweAgendas = new ArrayList<>();
        Agenda agenda1 = new GedeeldeAgenda(3,"agendanaam");
        nieuweAgendas.add(agenda1);
        Agenda agenda2 = new GedeeldeAgenda(4,"agendanaam");
        nieuweAgendas.add(agenda2);
        Account instance = testaccount;
        instance.addGedeeldeAgendas(nieuweAgendas);
        int expResult = 3;
        int result = instance.getGedeeldeAgendas().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGedeeldeAgendas method, of class Account.
     */
    @Test
    public void testSetGedeeldeAgendas() {
        System.out.println("setGedeeldeAgendas");
        List<Agenda> nieuweAgendas = new ArrayList<>();
        Agenda agenda1 = new GedeeldeAgenda(3,"agendanaam");
        nieuweAgendas.add(agenda1);
        Agenda agenda2 = new GedeeldeAgenda(4,"agendanaam");
        nieuweAgendas.add(agenda2);
        Account instance = testaccount;
        instance.setGedeeldeAgendas(nieuweAgendas);
        int expResult = 2;
        int result = instance.getGedeeldeAgendas().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGedeeldeAgendas method, of class Account.
     */
    @Test
    public void testGetGedeeldeAgendas() {
        System.out.println("getGedeeldeAgendas");
        Account instance = testaccount;
        int expResult = 1;
        int result = instance.getGedeeldeAgendas().size();
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
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
public class GedeeldeAgendaTest {
    
    Agenda testinstance;
    Account testAccount1;
    Account testAccount2;
    
    public GedeeldeAgendaTest() {
    }
    
    @Before
    public void setUp() {
        testinstance = new GedeeldeAgenda(3, "test naam");
        testAccount1 = new Account(1,1,"testnaam1","wachtwoord");
        testAccount2 = new Account(2,2,"testnaam2","wachtwoord");
    }
    
    @After
    public void tearDown() {
        testinstance = null;
        testAccount1 = null;
        testAccount2 = null;
    }

    /**
     * Test of setLeden method, of class GedeeldeAgenda.
     */
    @Test
    public void testSetLeden() {
        System.out.println("setLeden");
        List<Account> leden = new ArrayList<>();
        leden.add(testAccount1);
        leden.add(testAccount2);
        GedeeldeAgenda instance = (GedeeldeAgenda) testinstance;
        instance.setLeden(leden);
        int expResult = 2;
        int result = instance.getLeden().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeden method, of class GedeeldeAgenda.
     */
    @Test
    public void testGetLeden() {
        System.out.println("getLeden");
        List<Account> leden = new ArrayList<>();
        leden.add(testAccount1);
        leden.add(testAccount2);
        GedeeldeAgenda instance = (GedeeldeAgenda) testinstance;
        instance.setLeden(leden);
        int expResult = 2;
        int result = instance.getLeden().size();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of setLeden method, of class GedeeldeAgenda with excisting users
     */
    @Test
    public void testSetLedenIncorrect() {
        System.out.println("setLedenIncorrect");
        List<Account> leden = new ArrayList<>();
        leden.add(testAccount1);
        leden.add(testAccount1);
        GedeeldeAgenda instance = (GedeeldeAgenda) testinstance;
        instance.setLeden(leden);
        int expResult = 1;
        int result = instance.getLeden().size();
        assertEquals(expResult, result);
    }
    
    
}

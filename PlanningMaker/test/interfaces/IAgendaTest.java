/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.HostInfo;
import classes.agenda.Agenda;
import classes.agenda.Comment;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lesley
 */
public class IAgendaTest {
    
    public IAgendaTest() {
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
     * Test of setAgendaHost method, of class IAgenda.
     */
    @Test
    public void testSetAgendaHost() throws Exception {
        System.out.println("setAgendaHost");
        HostInfo host = null;
        IAgenda instance = new IAgendaImpl();
        instance.setAgendaHost(host);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGedeeldeAgenda method, of class IAgenda.
     */
    @Test
    public void testGetGedeeldeAgenda() throws Exception {
        System.out.println("getGedeeldeAgenda");
        int agendaId = 0;
        IAgenda instance = new IAgendaImpl();
        Agenda expResult = null;
        Agenda result = instance.getGedeeldeAgenda(agendaId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAccount method, of class IAgenda.
     */
    @Test
    public void testAddAccount() throws Exception {
        System.out.println("addAccount");
        int agendaId = 0;
        String gebruikersnaam = "";
        IAgenda instance = new IAgendaImpl();
        boolean expResult = false;
        boolean result = instance.addAccount(agendaId, gebruikersnaam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComment method, of class IAgenda.
     */
    @Test
    public void testAddComment() throws Exception {
        System.out.println("addComment");
        int agendaId = 0;
        Comment comment = null;
        IAgenda instance = new IAgendaImpl();
        boolean expResult = false;
        boolean result = instance.addComment(agendaId, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeComment method, of class IAgenda.
     */
    @Test
    public void testRemoveComment() throws Exception {
        System.out.println("removeComment");
        Comment comment = null;
        IAgenda instance = new IAgendaImpl();
        boolean expResult = false;
        boolean result = instance.removeComment(comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verlaatAgenda method, of class IAgenda.
     */
    @Test
    public void testVerlaatAgenda() throws Exception {
        System.out.println("verlaatAgenda");
        int gebruikersId = 0;
        int agendaId = 0;
        IAgenda instance = new IAgendaImpl();
        boolean expResult = false;
        boolean result = instance.verlaatAgenda(gebruikersId, agendaId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IAgendaImpl implements IAgenda {

        public void setAgendaHost(HostInfo host) throws RemoteException {
        }

        public Agenda getGedeeldeAgenda(int agendaId) throws RemoteException {
            return null;
        }

        public boolean addAccount(int agendaId, String gebruikersnaam) throws RemoteException {
            return false;
        }

        public boolean addComment(int agendaId, Comment comment) throws RemoteException {
            return false;
        }

        public boolean removeComment(Comment comment) throws RemoteException {
            return false;
        }

        public boolean verlaatAgenda(int gebruikersId, int agendaId) throws RemoteException {
            return false;
        }
    }
    
}

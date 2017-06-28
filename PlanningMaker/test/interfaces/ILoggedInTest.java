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
 * @author Lesley
 */
public class ILoggedInTest {
    
    public ILoggedInTest() {
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
     * Test of logout method, of class ILoggedIn.
     */
    @Test
    public void testLogout() throws Exception {
        System.out.println("logout");
        int gebruikersId = 0;
        ILoggedIn instance = new ILoggedInImpl();
        instance.logout(gebruikersId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriveAgenda method, of class ILoggedIn.
     */
    @Test
    public void testGetPriveAgenda() throws Exception {
        System.out.println("getPriveAgenda");
        int agendaid = 0;
        ILoggedIn instance = new ILoggedInImpl();
        Agenda expResult = null;
        Agenda result = instance.getPriveAgenda(agendaid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGedeeldeAgendas method, of class ILoggedIn.
     */
    @Test
    public void testGetGedeeldeAgendas() throws Exception {
        System.out.println("getGedeeldeAgendas");
        int gebruikersid = 0;
        ILoggedIn instance = new ILoggedInImpl();
        List<Agenda> expResult = null;
        List<Agenda> result = instance.getGedeeldeAgendas(gebruikersid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agendaItemToevoegen method, of class ILoggedIn.
     */
    @Test
    public void testAgendaItemToevoegen() throws Exception {
        System.out.println("agendaItemToevoegen");
        int agendaId = 0;
        String naam = "";
        String beschrijving = "";
        Date begintijd = null;
        Date eindtijd = null;
        String type = "";
        ILoggedIn instance = new ILoggedInImpl();
        boolean expResult = false;
        boolean result = instance.agendaItemToevoegen(agendaId, naam, beschrijving, begintijd, eindtijd, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commentToevoegen method, of class ILoggedIn.
     */
    @Test
    public void testCommentToevoegen() throws Exception {
        System.out.println("commentToevoegen");
        int agendaId = 0;
        Comment comment = null;
        ILoggedIn instance = new ILoggedInImpl();
        boolean expResult = false;
        boolean result = instance.commentToevoegen(agendaId, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gedeeldeAgendaAanmaken method, of class ILoggedIn.
     */
    @Test
    public void testGedeeldeAgendaAanmaken() throws Exception {
        System.out.println("gedeeldeAgendaAanmaken");
        int gebruikersId = 0;
        String naam = "";
        ILoggedIn instance = new ILoggedInImpl();
        boolean expResult = false;
        boolean result = instance.gedeeldeAgendaAanmaken(gebruikersId, naam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgendaHost method, of class ILoggedIn.
     */
    @Test
    public void testGetAgendaHost() throws Exception {
        System.out.println("getAgendaHost");
        int agendaId = 0;
        ILoggedIn instance = new ILoggedInImpl();
        HostInfo expResult = null;
        HostInfo result = instance.getAgendaHost(agendaId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ILoggedInImpl implements ILoggedIn {

        public void logout(int gebruikersId) throws RemoteException {
        }

        public Agenda getPriveAgenda(int agendaid) throws RemoteException {
            return null;
        }

        public List<Agenda> getGedeeldeAgendas(int gebruikersid) throws RemoteException {
            return null;
        }

        public boolean agendaItemToevoegen(int agendaId, String naam, String beschrijving, Date begintijd, Date eindtijd, String type) throws RemoteException {
            return false;
        }

        public boolean commentToevoegen(int agendaId, Comment comment) throws RemoteException {
            return false;
        }

        public boolean gedeeldeAgendaAanmaken(int gebruikersId, String naam) throws RemoteException {
            return false;
        }

        public HostInfo getAgendaHost(int agendaId) throws RemoteException {
            return null;
        }
    }
    
}

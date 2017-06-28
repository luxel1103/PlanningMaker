/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Let op!
 * Voordat deze test uitgevoerd word moet de connection string in de klasse Connection worden aangepast naar de test connection string!
 *
 * @author Lesley
 */
public class IAccesAgendaTest {
    
    public IAccesAgendaTest() {
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
     * Test of addAccount method, of class IAccesAgenda.
     */
    @Test
    public void testAddAccount() throws Exception {
        System.out.println("addAccount");
        String gebruikersnaam = "";
        IAccesAgenda instance = new IAccesAgendaImpl();
        boolean expResult = false;
        boolean result = instance.addAccount(gebruikersnaam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAccount method, of class IAccesAgenda.
     */
    @Test
    public void testRemoveAccount() throws Exception {
        System.out.println("removeAccount");
        int gebruikersId = 0;
        IAccesAgenda instance = new IAccesAgendaImpl();
        boolean expResult = false;
        boolean result = instance.removeAccount(gebruikersId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAgenda method, of class IAccesAgenda.
     */
    @Test
    public void testUpdateAgenda() throws Exception {
        System.out.println("updateAgenda");
        IAccesAgenda instance = new IAccesAgendaImpl();
        instance.updateAgenda();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAgendaItem method, of class IAccesAgenda.
     */
    @Test
    public void testRemoveAgendaItem() throws Exception {
        System.out.println("removeAgendaItem");
        int itemId = 0;
        IAccesAgenda instance = new IAccesAgendaImpl();
        boolean expResult = false;
        boolean result = instance.removeAgendaItem(itemId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of koppelGebruikerAanItem method, of class IAccesAgenda.
     */
    @Test
    public void testKoppelGebruikerAanItem() throws Exception {
        System.out.println("koppelGebruikerAanItem");
        int gebruikerId = 0;
        int itemId = 0;
        IAccesAgenda instance = new IAccesAgendaImpl();
        boolean expResult = false;
        boolean result = instance.koppelGebruikerAanItem(gebruikerId, itemId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ontkoppelGebbruikerVanItem method, of class IAccesAgenda.
     */
    @Test
    public void testOntkoppelGebbruikerVanItem() throws Exception {
        System.out.println("ontkoppelGebbruikerVanItem");
        int gebruikerId = 0;
        int itemId = 0;
        IAccesAgenda instance = new IAccesAgendaImpl();
        boolean expResult = false;
        boolean result = instance.ontkoppelGebbruikerVanItem(gebruikerId, itemId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    //Alle methodes zijn gekopieerd uit de klasse AgendaHost
    public class IAccesAgendaImpl implements IAccesAgenda {

        public boolean addAccount(String gebruikersnaam) throws RemoteException {
            return false;
        }

        public boolean removeAccount(int gebruikersId) throws RemoteException {
            return false;
        }

        public void updateAgenda() throws RemoteException {
        }

        public boolean removeAgendaItem(int itemId) throws RemoteException {
            return false;
        }

        public boolean koppelGebruikerAanItem(int gebruikerId, int itemId) throws RemoteException {
            return false;
        }

        public boolean ontkoppelGebbruikerVanItem(int gebruikerId, int itemId) throws RemoteException {
            return false;
        }
    }
    
}

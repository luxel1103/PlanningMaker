/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.agenda.Agenda;
import fontyspublisher.IRemotePropertyListener;
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
public class ILookAgendaTest {
    
    public ILookAgendaTest() {
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
     * Test of agendaInladen method, of class ILookAgenda.
     */
    @Test
    public void testAgendaInladen() throws Exception {
        System.out.println("agendaInladen");
        ILookAgenda instance = new ILookAgendaImpl();
        Agenda expResult = null;
        Agenda result = instance.agendaInladen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subscribe method, of class ILookAgenda.
     */
    @Test
    public void testSubscribe() throws Exception {
        System.out.println("subscribe");
        IRemotePropertyListener listener = null;
        String property = "";
        ILookAgenda instance = new ILookAgendaImpl();
        instance.subscribe(listener, property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ILookAgendaImpl implements ILookAgenda {

        public Agenda agendaInladen() throws RemoteException {
            return null;
        }

        public void subscribe(IRemotePropertyListener listener, String property) throws RemoteException {
        }
    }
    
}

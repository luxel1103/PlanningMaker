/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
public class HostInfoTest {
    
    HostInfo testhost;
    
    public HostInfoTest() {
    }
    
    @Before
    public void setUp() {
        testhost = new HostInfo(2,"192.168.151.20",2002);
    }
    
    @After
    public void tearDown() {
        testhost = null;
    }

    /**
     * Test of getAgendaId method, of class HostInfo.
     */
    @Test
    public void testGetAgendaId() {
        System.out.println("getAgendaId");
        HostInfo instance = testhost;
        int expResult = 2;
        int result = instance.getAgendaId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIp method, of class HostInfo.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = "127.0.0.1";
        HostInfo instance = testhost;
        instance.setIp(ip);
        String expResult = "127.0.0.1";
        String result = instance.getIp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIp method, of class HostInfo.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");
        HostInfo instance = testhost;
        String expResult = "192.168.151.20";
        String result = instance.getIp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPortNumber method, of class HostInfo.
     */
    @Test
    public void testGetPortNumber() {
        System.out.println("getPortNumber");
        HostInfo instance = testhost;
        int expResult = 2002;
        int result = instance.getPortNumber();
        assertEquals(expResult, result);
    }
    
}

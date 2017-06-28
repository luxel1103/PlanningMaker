/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EventTest {
    
    AgendaItem testinstance;
    Date testBeginTijd;
    Date testEindTijd;
    
    public EventTest() {
    }
    
    
    @Before
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString1 = "22-12-2017 10:20:30";
        testEindTijd = sdf.parse(dateInString1);
        String dateInString2 = "22-12-2017 16:20:30";
        testBeginTijd = sdf.parse(dateInString2);
        //aanmaken van het agendaitem dat getest gaat worden
        testinstance = new Event(1, "test event", "test beschrijving", testBeginTijd, testEindTijd);
    }
    
    @After
    public void tearDown() {
        testinstance = null;
        testBeginTijd = null;
        testEindTijd = null;
    }
    
    /**
     * Test of getBeginTijd method, of class Event.
     */
    @Test
    public void testGetBeginTijd() {
        System.out.println("getBeginTijd");
        Event instance = (Event) testinstance;
        Date expResult = testBeginTijd;
        Date result = instance.getBeginTijd();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginTijd method, of class Event.
     */
    @Test
    public void testSetBeginTijd() throws ParseException {
        System.out.println("setBeginTijd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString1 = "22-12-2017 9:20:30";
        Date beginTijd = sdf.parse(dateInString1);
        Event instance = (Event) testinstance;
        instance.setBeginTijd(beginTijd);
        Date expResult = beginTijd;
        Date result = instance.getBeginTijd();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setBeginTijd method, of class Event with a begin time higher than the end time.
     */
    @Test
    public void testSetBeginTijdIncorrect() throws ParseException {
        System.out.println("setBeginTijd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString1 = "23-12-2017 9:20:30";
        Date beginTijd = sdf.parse(dateInString1);
        Event instance = (Event) testinstance;
        instance.setBeginTijd(beginTijd);
        Date expResult = testBeginTijd;
        Date result = instance.getBeginTijd();
        assertEquals(expResult, result);
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Account;
import database.AccountConnection;
import database.AgendaConnection;
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
public class IVisitorTest {

    public IVisitorTest() {
    }

    @Before
    public void setUp() throws RemoteException {
        //maak de database leeg
        new AccountConnection().clearDatabase();
        //aanmaken van een test gebruiker
        IVisitor instance = new IVisitorImpl();
        instance.registreerGebruiker("test account", "wachtwoord");
    }

    @After
    public void tearDown() {
        //maak de database leeg
        new AccountConnection().clearDatabase();
    }

    /**
     * Test of getGebruiker method, of class IVisitor.
     */
    @Test
    public void testGetGebruiker() throws Exception {
        System.out.println("getGebruiker");
        String gebruikersnaam = "test account";
        String wachtwoord = "wachtwoord";
        IVisitor instance = new IVisitorImpl();
        String expResult = "test account";
        String result = instance.getGebruiker(gebruikersnaam, wachtwoord).getGebruikersnaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGebruiker method, of class IVisitor while password is
     * incorrect
     */
    @Test
    public void testGetGebruikerIncorrect1() throws Exception {
        System.out.println("getGebruiker");
        String gebruikersnaam = "test account";
        String wachtwoord = "wachtwoo";
        IVisitor instance = new IVisitorImpl();
        Account result = instance.getGebruiker(gebruikersnaam, wachtwoord);
        assertEquals(null, result);
    }

    /**
     * Test of getGebruiker method, of class IVisitor while user doesn't excist
     */
    @Test
    public void testGetGebruikerIncorrect2() throws Exception {
        System.out.println("getGebruiker");
        String gebruikersnaam = "test acc";
        String wachtwoord = "wachtwoord";
        IVisitor instance = new IVisitorImpl();
        Account result = instance.getGebruiker(gebruikersnaam, wachtwoord);
        assertEquals(null, result);
    }

    /**
     * Test of registreerGebruiker method, of class IVisitor.
     */
    @Test
    public void testRegistreerGebruiker() throws Exception {
        System.out.println("registreerGebruiker");
        String gebruikersnaam = "testgebruiker";
        String wachtwoord = "wachtwoord";
        IVisitor instance = new IVisitorImpl();
        String expResult = gebruikersnaam;
        String result = instance.registreerGebruiker(gebruikersnaam, wachtwoord).getGebruikersnaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of registreerGebruiker method, of class IVisitor while user already
     * excists
     */
    @Test
    public void testRegistreerGebruikerIncorrect() throws Exception {
        System.out.println("registreerGebruiker");
        String gebruikersnaam = "test account";
        String wachtwoord = "wachtwoord";
        IVisitor instance = new IVisitorImpl();
        Account result = instance.registreerGebruiker(gebruikersnaam, wachtwoord);
        assertEquals(null, result);
    }

    //Alle methodes zijn gekopieerd uit de PlanningMaker klasse
    public class IVisitorImpl implements IVisitor {

        AccountConnection accountConn = new AccountConnection();
        AgendaConnection agendaConn = new AgendaConnection();

        @Override
        public Account getGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
            Account account = null;
            //account ophalen uit de database
            try {
                account = accountConn.getAccount(gebruikersnaam, wachtwoord);
            } catch (Exception ex) {
                System.out.println("Het ophalen van gebruiker: " + gebruikersnaam + " is mislukt");
                System.out.println(ex.getMessage());
                return null;
            }
            return account;
        }

        @Override
        public Account registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
            int accountid = accountConn.getAccount(gebruikersnaam);
            if (accountid == 0) {
                int agendaid = agendaConn.addGedeeldeAgenda("prive", 0);
                if (agendaid != 0) {
                    if (accountConn.registreerAccount(gebruikersnaam, wachtwoord, agendaid)) {
                        return accountConn.getAccount(gebruikersnaam, wachtwoord);
                    } else {
                        System.out.println("Kon gebruiker met gebruikersnaam: " + gebruikersnaam + " niet registreren");
                        return null;
                    }
                } else {
                    System.out.println("Kon geen agenda aanmaken voor gebruiker: " + gebruikersnaam);
                    return null;
                }
            } else {
                System.out.println("Er bestaat al een gebruiker met de gebruikersnaam: " + gebruikersnaam);
                return null;
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class AccountConnection {

    private java.sql.Connection myConn;
    private PreparedStatement pstmt;
    private ResultSet myRs;

    private Connection conn = new Connection();

    // SQL codes
    static final String GET_FROM_USER_BYLOGININFO = "SELECT * FROM ACCOUNT WHERE gebruikersnaam = ? and wachtwoord = ?";
    static final String GET_FROM_USER_ID = "SELECT * FROM ACCOUNT WHERE id = ?";
    static final String GET_ACCOUNT_ID_BY_NAME = "SELECT id FROM ACCOUNT WHERE gebruikersnaam = ?";
    static final String SET_ACCOUNT_NEW = "INSERT INTO ACCOUNT(gebruikersnaam, wachtwoord, priveagendaid) VALUES (?, ?, ?)";
    static final String GET_ACCOUNT_IDS = "SELECT gebruikersid FROM AGENDA_LEDEN WHERE agendaid = ?";

    public List<Integer> getAccountIds(int agendaid) {

        List<Integer> gebruikerids = new ArrayList<>();
        int id = 0;

        try {
            conn.getConnection();
            pstmt = conn.getMyConn().prepareStatement(GET_ACCOUNT_IDS);
            pstmt.setInt(1, agendaid);
            myRs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (myRs.next()) {
                id = myRs.getInt("gebruikersid");
                if (id != 0) {
                    gebruikerids.add(id);
                }
                id = 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("mislukt om gebruiker id's op te halen voor agenda: " + agendaid);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return gebruikerids;
    }
    
    public Account getAccount(String gebruikersnaam, String wachtwoord) {
        Account account = null;
        int priveAgendaId = 0;
        try {
            conn.getConnection();
            myConn = conn.getMyConn();
            pstmt = myConn.prepareStatement(GET_FROM_USER_BYLOGININFO);
            pstmt.setString(1, gebruikersnaam);
            pstmt.setString(2, wachtwoord);

            myRs = pstmt.executeQuery();
            myRs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int gebruikersid = myRs.getInt("id");
            String naam = myRs.getString("gebruikersnaam");
            String pass = myRs.getString("wachtwoord");
            priveAgendaId = myRs.getInt("priveagendaid");
            account = new Account(gebruikersid, priveAgendaId, naam, wachtwoord);
            conn.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Geen gebruiker gevonden met gebruikersnaam: " + gebruikersnaam);
            conn.closeConnection();
        }

        return account;
    }
    
    public Account getAccountById(int gebruikersid) {
        Account account = null;
        int priveAgendaId = 0;
        try {
            conn.getConnection();
            myConn = conn.getMyConn();
            pstmt = myConn.prepareStatement(GET_FROM_USER_ID);
            pstmt.setInt(1, gebruikersid);

            myRs = pstmt.executeQuery();
            myRs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            String naam = myRs.getString("gebruikersnaam");
            String pass = myRs.getString("wachtwoord");
            priveAgendaId = myRs.getInt("priveagendaid");
            account = new Account(gebruikersid, priveAgendaId, naam, pass);
            conn.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Geen gebruiker gevonden met gebruiker met id: " + gebruikersid);
            conn.closeConnection();
        }

        return account;
    }

    public int getAccount(String gebruikersnaam) {
        int accountid = 0;
        try {
            conn.getConnection();
            myConn = conn.getMyConn();
            pstmt = myConn.prepareStatement(GET_ACCOUNT_ID_BY_NAME);
            pstmt.setString(1, gebruikersnaam);

            myRs = pstmt.executeQuery();
            myRs.next();
        } catch (SQLException ex) {
            System.out.println("Geen account in de database gevonden met gebruikersnaam: " + gebruikersnaam);
            return 0;
        }
        try {
            accountid = myRs.getInt("id");
            conn.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Geen account in de database gevonden met gebruikersnaam: " + gebruikersnaam);
            conn.closeConnection();
            return 0;
        }

        return accountid;
    }

    public boolean registreerAccount(String gebruikersnaam, String wachtwoord, int agendaid) {
        

                try {
                    conn.getConnection();
                    myConn = conn.getMyConn();
                    pstmt = myConn.prepareStatement(SET_ACCOUNT_NEW);
                    pstmt.setString(1, gebruikersnaam);
                    pstmt.setString(2, wachtwoord);
                    pstmt.setInt(3, agendaid);

                    if (pstmt.executeUpdate() > 0) {
                        System.out.println("Account geregistreerd met gebruikersnaam: " + gebruikersnaam);
                        return true;
                    } else {
                        System.out.println("Gebruiker aan database toevoegen is mislukt.");
                        return false;
                    }
                } catch (SQLException ex) {
                    System.out.println("failed to register new user. SQLException");
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    return false;
                }finally{
                    conn.closeConnection();                    
                }
    }
    
}

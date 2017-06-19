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
    static final String GET_ACCOUNT_ID_BY_NAME = "SELECT id FROM ACCOUNT WHERE gebruikersnaam = ?";
//    static final String GET_FROM_USER_BYUSERNAME = "SELECT * FROM user WHERE BINARY username = ?";
//    static final String GET_FROM_USER_ID = "SELECT * FROM user WHERE id = ?";
//    static final String SET_USER_NEW = "INSERT INTO user(username, password, alias, email, verified, imageURL, saldo) VALUES (?, ?, ?, ?, ?, ?, ?)";
//    static final String GET_FROM_FEEDBACK_TOSELLER = "SELECT * FROM feedback WHERE sellerid = ?";
//    static final String GET_FROM_FEEDBACK_FROMBUYER = "SELECT * FROM feedback WHERE buyerid = ?";
//    static final String REMOVE_USER_BYUSERNAME = "DELETE FROM user WHERE BINARY username = ?";
//    static final String GET_FROM_USER_ALLUSERS = "SELECT * FROM user";
//    static final String SET_FEEDBACK_TOSELLER = "INSERT INTO feedback(timeCreated, rating, description, sellerid, buyerid) VALUES(CURRENT_TIMESTAMP, ?, ?, ?, ?)";
//    static final String SET_ISAUTHORIZED = "UPDATE user SET isAuthorized = ? WHERE username = ?";
//    static final String GET_HASBOUGHT_FROM_SELLER = "SELECT * from transaction t, auction a where a.id = t.auctionID and a.buyerID = ? and t.sellerID = ?;";

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
            System.out.println("User not found");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accountid = myRs.getInt("id");
            conn.closeConnection();
        } catch (SQLException ex) {
            System.out.println("User not found");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            conn.closeConnection();
        }

        return accountid;
    }

}

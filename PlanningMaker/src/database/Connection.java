/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class Connection {

    //Connection string voor de database
    private final String connectionString = "jdbc:mysql://vserver213.axc.nl:3306/lesleya213_gso?zeroDateTimeBehavior=convertToNull";
    //Connection string voor de test database voor het testen van de interface tests
    //private final String connectionString = "jdbc:mysql://vserver213.axc.nl:3306/lesleya213_gsotest?zeroDateTimeBehavior=convertToNull";
    private final String dbUser = "lesleya213_gso";
    private final String dbPass = "wachtwoord";

    private java.sql.Connection myConn;
    private PreparedStatement pstmt;
    private ResultSet myRs;

    public Connection() {

    }

    /**
     * Connects with the database.
     *
     * @return true if connected, false if failed to connect.
     */
    public boolean getConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(connectionString, dbUser, dbPass);
            //System.out.println("started connection to database...");
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Failed to start connection to database...");
            return false;
        }
    }

    public java.sql.Connection getMyConn() {
        return this.myConn;
    }

    /**
     * Closes the connection with the database
     *
     * @return true if the connection is closed, false if it failed.
     */
    public boolean closeConnection() {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myConn != null) {
                myConn.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            //System.out.println("Closing connection to database...");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.agenda.Agenda;
import classes.agenda.PriveAgenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class AgendaConnection {

    private java.sql.Connection myConn;
    private PreparedStatement pstmt;
    private ResultSet myRs;

    private Connection conn = new Connection();

    // SQL codes
    static final String GET_PRIVEAGENDA = "SELECT * FROM AGENDA WHERE id = ?";

    public Agenda getPriveAgenda(int agendaid) {
        Agenda agenda = null;
        try {
            conn.getConnection();
            myConn = conn.getMyConn();
            pstmt = myConn.prepareStatement(GET_PRIVEAGENDA);
            pstmt.setInt(1, agendaid);
            myRs = pstmt.executeQuery();
            myRs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int id = myRs.getInt("id");
            int isgedeeld = myRs.getInt("isgedeeld");
            if (isgedeeld == 0) {
                agenda = new PriveAgenda(id);
            } else {
                agenda = null;
                System.out.println("Er werd geprobeerd om een gedeelde agenda op te halen als een prive agenda.");
            }
            conn.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Agenda met id:" + agendaid + " niet gevonden");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            conn.closeConnection();
        }

        return agenda;
    }
}

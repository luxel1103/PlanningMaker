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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
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
    static final String ADD_AGENDA_ITEM = "INSERT INTO AGENDAITEM(agendaid, naam, beschrijving, begintijd, eindtijd) VALUES (?,?,?,?,?)";

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
    
    public Boolean insertAgendaItem(int agendaid, String naam, String beschrijving, Date begintijd, Date eindtijd) {
        conn.getConnection();
        
        try {
            pstmt = conn.getMyConn().prepareStatement(ADD_AGENDA_ITEM);
            pstmt.setInt(1, agendaid);
            pstmt.setString(2, naam);
            pstmt.setString(3, beschrijving);
            if(begintijd == null){
                pstmt.setNull(4, Types.TIMESTAMP);
            }else{
                pstmt.setTimestamp(4, new Timestamp(begintijd.getTime()));
            }
            pstmt.setTimestamp(5, new Timestamp(eindtijd.getTime()));

            if (pstmt.executeUpdate() > 0) {
                System.out.println("Agenda Item succesvol toegevoegd aan de agenda");
                conn.closeConnection();
                return true;
            } else {
                System.out.println("Agenda Item kon niet worden toegevoegd aan de database");
                conn.closeConnection();
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            conn.closeConnection();
            return false;
        }

    }
    
}

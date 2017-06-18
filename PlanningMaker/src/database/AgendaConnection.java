/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Event;
import classes.agenda.GedeeldeAgenda;
import classes.agenda.PriveAgenda;
import classes.agenda.Taak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    static final String GET_AGENDA = "SELECT * FROM AGENDA WHERE id = ?";
    static final String ADD_AGENDA_ITEM = "INSERT INTO AGENDAITEM(agendaid, naam, beschrijving, begintijd, eindtijd) VALUES (?,?,?,?,?)";
    static final String ADD_GEDEELDE_AGENDA = "INSERT INTO AGENDA(isgedeeld, naam) VALUES (?,?)";
    static final String ADD_LID = "INSERT INTO AGENDA_LEDEN(agendaid, gebruikersid, istoegevoegd, rechten) VALUES (?,?,?,?)";

    // SQL codes
    static final String GET_GEDEELDE_AGENDA_IDS = "SELECT agendaid FROM AGENDA_LEDEN WHERE gebruikersid = ?";

    public List<Integer> getGedeeldeAgendaIds(int gebruikersid) {

        List<Integer> agendaids = new ArrayList<>();
        int id = 0;

        try {
            conn.getConnection();
            pstmt = conn.getMyConn().prepareStatement(GET_GEDEELDE_AGENDA_IDS);
            pstmt.setInt(1, gebruikersid);
            myRs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (myRs.next()) {
                id = myRs.getInt("agendaid");
                if(id!=0){
                    agendaids.add(id);
                }
                id = 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("mislukt om gedeelde agenda id's op te halen voor gebruiker: " + gebruikersid);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return agendaids;
    }
    
    public Agenda getAgenda(int agendaid) {
        Agenda agenda = null;
        try {
            conn.getConnection();
            myConn = conn.getMyConn();
            pstmt = myConn.prepareStatement(GET_AGENDA);
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
            String naam = myRs.getString("naam");
            if (isgedeeld == 0) {
                agenda = new PriveAgenda(id,naam);
            } else if(isgedeeld == 1){
                agenda = new GedeeldeAgenda(id,naam);
            } else{
                agenda = null;
                System.out.println("Het ophalen van de agenda is mislukt");
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
            if (begintijd == null) {
                pstmt.setNull(4, Types.TIMESTAMP);
            } else {
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

    public int addGedeeldeAgenda(String agendaNaam) {
        conn.getConnection();
        try {

            pstmt = conn.getMyConn().prepareStatement(ADD_GEDEELDE_AGENDA, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 1);
            pstmt.setString(2, agendaNaam);

            if (pstmt.executeUpdate() > 0) {
                int productid = 0;
                System.out.println("Gedeelde agenda succesvol toegevoegd ");
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productid = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Geen agenda id terug gekregen.");
                    }
                }
                conn.closeConnection();
                return productid;
            } else {
                System.out.println("Couldn't insert new agenda. Rows are unaffected.");
                conn.closeConnection();
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            conn.closeConnection();
            return 0;
        }
    }
    
    public boolean LidToevoegenAanGedeeldeAgenda(int agendaid, int gebruikersid, boolean isEigenaar, boolean heeftRechten) {
        conn.getConnection();
        int istoegevoegd = 1;
        int rechten = 0;
        if(isEigenaar){
            istoegevoegd = 1;
        }
        if(heeftRechten){
            rechten = 1;
        }
        try {

            pstmt = conn.getMyConn().prepareStatement(ADD_LID);
            pstmt.setInt(1, agendaid);
            pstmt.setInt(2, gebruikersid);
            pstmt.setInt(3, istoegevoegd);
            pstmt.setInt(4, rechten);

            if (pstmt.executeUpdate() > 0) {
                System.out.println("Lid succesvol toegevoegd aan de gedeelde agenda");
                conn.closeConnection();
                return true;
            } else {
                System.out.println("Lid niet toegevoegd aan de gedeelde agenda");
                conn.closeConnection();
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            conn.closeConnection();
            return false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.agenda.AgendaItem;
import classes.agenda.Event;
import classes.agenda.Taak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class AgendaItemConnection {

    private java.sql.Connection myConn;
    private PreparedStatement pstmt;
    private ResultSet myRs;

    private Connection conn = new Connection();

    // SQL codes
    static final String GET_FROM_AUCTIONS = "SELECT * FROM AGENDAITEM WHERE agendaid = ?";

    public List<AgendaItem> getAgendaItems(int agendaid) {

        List<AgendaItem> agendaitems = new ArrayList<>();
        AgendaItem item = null;

        try {
            conn.getConnection();
            pstmt = conn.getMyConn().prepareStatement(GET_FROM_AUCTIONS);
            pstmt.setInt(1, agendaid);
            myRs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String naam = myRs.getString("naam");
                String beschrijving = myRs.getString("beschrijving");
                Date begintijd = myRs.getDate("begintijd");
                Date eindtijd = myRs.getDate("eindtijd");

                //maak een event
                if (begintijd != null) {
                    item = new Event(id, naam, beschrijving, begintijd, eindtijd);

                } else { //maak een taak
                    item = new Taak(id, naam, beschrijving, eindtijd);
                }

                agendaitems.add(item);
                item = null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Geen agendaitems ontvangen van agenda met id: " + agendaid);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return agendaitems;
    }

}

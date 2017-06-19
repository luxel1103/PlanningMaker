/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.util.Date;

/**
 *
 * @author Lesley Peters
 */
public class Event extends AgendaItem {

    private Date beginTijd;

    public Event(int id, String naam, String beschrijving, Date beginTijd, Date eindTijd) {
        super(id, naam, beschrijving, eindTijd);
        this.beginTijd = beginTijd;
    }

    public void setBeginTijd(Date beginTijd) {
        this.beginTijd = beginTijd;
    }

    public Date getBeginTijd() {
        return beginTijd;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class AgendaItem implements Serializable {

    private int id;
    private String naam;
    private Date eindTijd;
    private List<Account> genodigden;
    private List<Comment> comments;

    public AgendaItem() {

    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(Date eindTijd) {
        this.eindTijd = eindTijd;
    }

    public List<Account> getGenodigden() {
        return genodigden;
    }

    public void setGenodigden(List<Account> genodigden) {
        this.genodigden = genodigden;
    }

    public boolean addGenodigde(Account genodigde) {
        genodigden.add(genodigde);
        return true;
    }

    public void removeGenodigde(Account genodigde) {
        genodigden.remove(genodigde);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}

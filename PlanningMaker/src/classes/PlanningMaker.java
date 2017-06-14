/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Comment;
import interfaces.IAgenda;
import interfaces.ILoggedIn;
import interfaces.IVisitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class PlanningMaker extends UnicastRemoteObject implements ILoggedIn, IAgenda, IVisitor {

    private Date huidigeDatumEnTijd;
    private List<Account> ingelogdeGebruikers;

    public PlanningMaker() throws RemoteException {
    }

    /**
     * Get the value of huidigeDatumEnTijd
     *
     * @return the value of huidigeDatumEnTijd
     */
    public Date getHuidigeDatumEnTijd() {
        return huidigeDatumEnTijd;
    }

    /**
     * Set the value of huidigeDatumEnTijd
     *
     * @param huidigeDatumEnTijd new value of huidigeDatumEnTijd
     */
    public void setHuidigeDatumEnTijd(Date huidigeDatumEnTijd) {
        this.huidigeDatumEnTijd = huidigeDatumEnTijd;
    }

    //ILoggedIn methodes
    @Override
    public void logout(int gebruikersId) throws RemoteException {
        for (Account account : ingelogdeGebruikers) {
            if (account.getId() == gebruikersId) {
                ingelogdeGebruikers.remove(account);
            }
        }
    }

    @Override
    public Agenda getPriveAgenda(int gebruikersId) throws RemoteException {
        //get agenda from database
        //select agendaid from priveagendas where gebruikersid = 1
        //select * from agenda where agendaid = agendaid
        return null;
    }

    @Override
    public boolean agendaItemToevoegen(AgendaItem item) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean commentToevoegen(int agendaId, Comment comment) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gedeeldeAgendaAanmaken() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //IAgenda methodes
    @Override
    public Agenda getGedeeldeAgenda(int agendaId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addComment(int agendaId, Comment comment) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeComment(Comment comment) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verlaatAgenda(int gebruikersId, int agendaId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //IVisitor methodes
    @Override
    public ILoggedIn login(int id, String gebruikersnaam, String wachtwoord) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGebruikersId(String gebruikersnaam, String wachtwoord) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

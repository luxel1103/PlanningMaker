/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Comment;
import classes.agenda.Event;
import classes.agenda.Taak;
import database.AccountConnection;
import database.AgendaConnection;
import database.AgendaItemConnection;
import database.Connection;
import interfaces.IAgenda;
import interfaces.ILoggedIn;
import interfaces.IVisitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class PlanningMaker extends UnicastRemoteObject implements ILoggedIn, IAgenda, IVisitor {

    private Date huidigeDatumEnTijd;
    private List<Account> ingelogdeGebruikers;

    private AccountConnection accountConn;
    private AgendaConnection agendaConn;
    private AgendaItemConnection agendaItemConn;

    public PlanningMaker() throws RemoteException {
        accountConn = new AccountConnection();
        agendaConn = new AgendaConnection();
        agendaItemConn = new AgendaItemConnection();
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
//        for (Account account : ingelogdeGebruikers) {
//            if (account.getId() == gebruikersId) {
//                System.out.println("Gebruiker: " + account.getGebruikersnaam() + " is succesvol uitgelogd.");
//                ingelogdeGebruikers.remove(account);
//            }
//        }
        System.out.println("logout");
    }

    @Override
    public Agenda getPriveAgenda(int agendaid) throws RemoteException {
        Agenda agenda = null;

        //priveagenda ophalen uit de database 
        try {
            if (agendaid != 0) {
                //ophalen van de agenda
                Agenda priveAgenda = agendaConn.getPriveAgenda(agendaid);
                if (priveAgenda != null) {
                    agenda = priveAgenda;
                    System.out.println("Prive agenda met id: " + agendaid + " succesvol opgehaald uit de database");
                } else {
                    System.out.println("Agenda met id: " + agendaid + " kon niet worden ingeladen.");
                    return null;
                }
            } else {
                System.out.println("Priveagenda met id: " + agendaid + " bestaat niet");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Het ophalen van de agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            return null;
        }

        //toevoegen van de agenda items
        try {
            List<AgendaItem> items = agendaItemConn.getAgendaItems(agenda.getId());
            agenda.addAgendaItems(items);
            System.out.println("Agenda items voor agenda met id: " + agendaid + " succesvol toegevoegd aan de agenda");
        } catch (Exception ex) {
            System.out.println("Het toevoegen van agendaitems voor de agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            Logger.getLogger(PlanningMaker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return agenda;
    }

    @Override
    public boolean agendaItemToevoegen(int agendaId, String naam, String beschrijving, Date begintijd, Date eindtijd, String type) throws RemoteException {
        if(type.equals("taak")){
            return agendaConn.insertAgendaItem(agendaId, naam, beschrijving, null, eindtijd);
        }
        else{
            return agendaConn.insertAgendaItem(agendaId, naam, beschrijving, begintijd, eindtijd);
        }
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
    public Account getGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
        Account account = null;

        //account ophalen uit de database
        try {
            account = accountConn.getAccount(gebruikersnaam, wachtwoord);
            System.out.println("Gebruiker: " + account.getGebruikersnaam() + " succesvol opgehaald uit de database ");
        } catch (Exception ex) {
            System.out.println("Het ophalen van gebruiker: " + gebruikersnaam + " is mislukt");
            System.out.println(ex.getMessage());
            return null;
        }

        System.out.println("Gebruiker: " + account.getGebruikersnaam() + " is succesvol ingelogd");
        return account;
    }

    @Override
    public int registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

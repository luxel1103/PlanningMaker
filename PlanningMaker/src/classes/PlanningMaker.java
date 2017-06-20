/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Comment;
import classes.agenda.GedeeldeAgenda;
import database.AccountConnection;
import database.AgendaConnection;
import database.AgendaItemConnection;
import interfaces.IAgenda;
import interfaces.ILoggedIn;
import interfaces.IVisitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
    private List<HostInfo> agendaHosts;

    private AccountConnection accountConn;
    private AgendaConnection agendaConn;
    private AgendaItemConnection agendaItemConn;

    public PlanningMaker() throws RemoteException {
        accountConn = new AccountConnection();
        agendaConn = new AgendaConnection();
        agendaItemConn = new AgendaItemConnection();
        agendaHosts = new ArrayList<>();
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
                Agenda priveAgenda = agendaConn.getAgenda(agendaid);
                if (priveAgenda != null) {
                    agenda = priveAgenda;
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
        } catch (Exception ex) {
            System.out.println("Het toevoegen van agendaitems voor de agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            Logger.getLogger(PlanningMaker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return agenda;
    }

    @Override
    public List<Agenda> getGedeeldeAgendas(int gebruikersid) throws RemoteException {

        List<Integer> agendaids = new ArrayList<>();
        List<Agenda> gedeeldeAgendas = new ArrayList<>();

        //gedeelde agenda ids ophalen uit de database
        try {
            agendaids = agendaConn.getGedeeldeAgendaIds(gebruikersid);
        } catch (Exception ex) {
            System.out.println("Het ophalen van de gedeelde agenda ids voor gebruiker: " + gebruikersid);
            System.out.println(ex.getMessage());
            return null;
        }

        //gedeelde agendas ophalen uit de database
        for (Integer agendaid : agendaids) {
            Agenda agenda = null;
            try {
                if (agendaid != 0) {
                    //ophalen van de agenda
                    Agenda gedeeldeAgenda = agendaConn.getAgenda(agendaid);
                    if (gedeeldeAgenda != null) {
                        agenda = gedeeldeAgenda;
                    } else {
                        System.out.println("Agenda met id: " + agendaid + " kon niet worden ingeladen.");
                        return null;
                    }
                } else {
                    System.out.println("Gedeelde agenda met id: " + agendaid + " bestaat niet");
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Het ophalen van de agenda met id: " + agendaid + " is mislukt");
                System.out.println(ex.getMessage());
                return null;
            }

            //toevoegen van de agenda items
            try {
                List<AgendaItem> items = agendaItemConn.getAgendaItems(agendaid);
                agenda.addAgendaItems(items);
                gedeeldeAgendas.add(agenda);
            } catch (Exception ex) {
                System.out.println("Het toevoegen van agendaitems voor de agenda met id: " + agendaid + " is mislukt");
                System.out.println(ex.getMessage());
                Logger.getLogger(PlanningMaker.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return gedeeldeAgendas;
    }

    @Override
    public boolean agendaItemToevoegen(int agendaId, String naam, String beschrijving, Date begintijd, Date eindtijd, String type) throws RemoteException {
        if (type.equals("taak")) {
            return agendaConn.insertAgendaItem(agendaId, naam, beschrijving, null, eindtijd);
        } else {
            return agendaConn.insertAgendaItem(agendaId, naam, beschrijving, begintijd, eindtijd);
        }
    }

    @Override
    public boolean commentToevoegen(int agendaId, Comment comment) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gedeeldeAgendaAanmaken(int gebruikersid, String naam) throws RemoteException {
        int agendaid = agendaConn.addGedeeldeAgenda(naam,1);
        if (agendaid != 0) {
            return agendaConn.LidToevoegenAanGedeeldeAgenda(agendaid, gebruikersid, true, true);
        } else {
            System.out.println("Agenda is niet toegevoegd");
            return false;
        }
    }

    //IAgenda methodes
    @Override
    public Agenda getGedeeldeAgenda(int agendaid) throws RemoteException {
        //gedeelde agenda ophalen uit de database
        Agenda agenda = null;
        try {
            if (agendaid != 0) {
                //ophalen van de agenda
                Agenda gedeeldeAgenda = agendaConn.getAgenda(agendaid);
                if (gedeeldeAgenda != null) {
                    agenda = gedeeldeAgenda;
                } else {
                    System.out.println("Agenda met id: " + agendaid + " kon niet worden ingeladen.");
                    return null;
                }
            } else {
                System.out.println("Gedeelde agenda met id: " + agendaid + " bestaat niet");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Het ophalen van de agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            return null;
        }

        //toevoegen van de agenda items
        try {
            List<AgendaItem> items = agendaItemConn.getAgendaItems(agendaid);
            agenda.addAgendaItems(items);
        } catch (Exception ex) {
            System.out.println("Het toevoegen van agendaitems voor de agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            Logger.getLogger(PlanningMaker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        //toevoegen van de leden aan de gedeelde agenda
        try {
            List<Integer> gebruikerids = accountConn.getAccountIds(agendaid);
            List<Account> accounts = new ArrayList<>();
            for(int id : gebruikerids){
                Account account = accountConn.getAccountById(id);
                accounts.add(account);
            }
            if(accounts.size() > 0){
                GedeeldeAgenda gedeeldeAgenda = (GedeeldeAgenda) agenda;
                gedeeldeAgenda.setLeden(accounts);
                return gedeeldeAgenda;
            }else{
                System.out.println("Geen leden gevonden voor de gedeelde agenda met id: " + agendaid);
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Het toevoegen van gebruikers aan de gedeelde agenda met id: " + agendaid + " is mislukt");
            System.out.println(ex.getMessage());
            Logger.getLogger(PlanningMaker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
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
        } catch (Exception ex) {
            System.out.println("Het ophalen van gebruiker: " + gebruikersnaam + " is mislukt");
            System.out.println(ex.getMessage());
            return null;
        }
        return account;
    }

    @Override
    public Account registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException {
        int accountid = accountConn.getAccount(gebruikersnaam);
        if(accountid == 0){
            int agendaid = agendaConn.addGedeeldeAgenda("prive", 0);
            if(agendaid != 0){
                if(accountConn.registreerAccount(gebruikersnaam, wachtwoord, agendaid)){
                return accountConn.getAccount(gebruikersnaam, wachtwoord);
            }else{
                System.out.println("Kon gebruiker met gebruikersnaam: " + gebruikersnaam + " niet registreren");
                return null;
            }
            }else{
                System.out.println("Kon geen agenda aanmaken voor gebruiker: " + gebruikersnaam);
                return null;
            }
        }else{
            System.out.println("Er bestaat al een gebruiker met de gebruikersnaam: " + gebruikersnaam);
            return null;
        }
    }

    @Override
    public HostInfo getAgendaHost(int agendaId) throws RemoteException {
        for (HostInfo host : agendaHosts) {
            if (host.getAgendaId() == agendaId) {
                return host;
            }
        }
        return null;
    }

    @Override
    public void setAgendaHost(HostInfo hostInfo) throws RemoteException {
        for (HostInfo host : agendaHosts) {
            if (host.getAgendaId() == hostInfo.getAgendaId()) {
                agendaHosts.remove(host);
            }
        }
        agendaHosts.add(hostInfo);
        System.out.println("Agenda met id: " + hostInfo.getAgendaId() + " word gehost door: " + hostInfo.getIp() + " op poort nummer: " + hostInfo.getPortNumber());
    }

    @Override
    public boolean addAgendaItem(AgendaItem item) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAccount(int agendaId, String gebruikersnaam) throws RemoteException {
        int gebruikersId = accountConn.getAccount(gebruikersnaam);
        boolean result = false;
        if(gebruikersId != 0){
            result = agendaConn.LidToevoegenAanGedeeldeAgenda(agendaId, gebruikersId, true, true);
        }else {
            return false;
        }
        return result;
    }

}

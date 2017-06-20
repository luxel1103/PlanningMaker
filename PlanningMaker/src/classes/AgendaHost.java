/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.RemotePublisher;
import interfaces.IAccesAgenda;
import interfaces.IAgenda;
import interfaces.ILookAgenda;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import planningmaker.RegistryManager;

/**
 *
 * @author Lesley Peters
 */
public class AgendaHost extends UnicastRemoteObject implements ILookAgenda, IAccesAgenda {

    RegistryManager RM;
    IAgenda agendaInterface;
    Agenda agenda;
    HostInfo hostInfo;

    RemotePublisher rp;

    public AgendaHost(int agendaid, HostInfo hostInfo) throws RemoteException {
        rp = new RemotePublisher();
        rp.registerProperty("agenda");
        RM = new RegistryManager();
        RM.getAgendaInterface();
        this.hostInfo = hostInfo;
        agendaInterface = RM.getAgenda();
        agendaInterface.setAgendaHost(hostInfo);
        Agenda nieuweagenda = agendaInterface.getGedeeldeAgenda(agendaid);
        if (nieuweagenda != null) {
            agenda = nieuweagenda;
            System.out.println("Gedeelde agenda: " + agenda.getNaam() + " is opgehaald van de server.");
        } else {
            System.out.println("gedeelde agenda kon niet worden opgehaald");
        }
    }

    @Override
    public Agenda agendaInladen() throws RemoteException {
        return this.agenda;
    }

    @Override
    public boolean addAccount(String gebruikersnaam) throws RemoteException {
        return agendaInterface.addAccount(agenda.getId(), gebruikersnaam);
    }

    @Override
    public boolean removeAccount(int gebruikersId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAgenda() throws RemoteException {
        Agenda nieuweagenda = agendaInterface.getGedeeldeAgenda(agenda.getId());
        if (nieuweagenda != null) {
            agenda = nieuweagenda;
            System.out.println("Gedeelde agenda: " + agenda.getNaam() + " is opgehaald van de server.");
            rp.inform("agenda", null, this.agenda);
        } else {
            System.out.println("gedeelde agenda kon niet worden opgehaald");
        }
    }

    @Override
    public boolean removeAgendaItem(int itemId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean koppelGebruikerAanItem(int gebruikerId, int itemId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ontkoppelGebbruikerVanItem(int gebruikerId, int itemId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void subscribe(IRemotePropertyListener listener, String property) throws RemoteException {
        rp.subscribeRemoteListener(listener, property);
    }
}

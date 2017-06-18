/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import interfaces.IAccesAgenda;
import interfaces.IAgenda;
import interfaces.ILookAgenda;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import planningmaker.AgendaServer;
import planningmaker.RegistryManager;

/**
 *
 * @author Lesley Peters
 */
public class AgendaHost extends UnicastRemoteObject implements ILookAgenda, IAccesAgenda {
    
    RegistryManager RM;
    IAgenda agendaInterface;
    Agenda agenda;
    
    public AgendaHost(int agendaid) throws RemoteException{
        RM = new RegistryManager();
        RM.getAgendaInterface();
        agendaInterface = RM.getAgenda();
        Agenda nieuweagenda = agendaInterface.getGedeeldeAgenda(agendaid);
        if(nieuweagenda != null){
            agenda = nieuweagenda;
            System.out.println("Gedeelde agenda: "+agenda.getNaam() + " is opgehaald van de server.");
        }else{
            System.out.println("gedeelde agenda kon niet worden opgehaald");
        }
    }

    @Override
    public Agenda agendaInladen() throws RemoteException {
        return this.agenda;
    }

    @Override
    public boolean agendaOpslaan(Agenda agenda) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAccount(int gebruikersId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAccount(int gebruikersId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAgendaItem(AgendaItem item) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import interfaces.IAccesAgenda;
import interfaces.ILookAgenda;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class Agenda implements Serializable, ILookAgenda, IAccesAgenda {

    private int id;
    private Date tijdEnDatum;
    private List<AgendaItem> agendaItems;

    public Agenda() {
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the value of tijdEnDatum
     *
     * @return the value of tijdEnDatum
     */
    public Date getTijdEnDatum() {
        return tijdEnDatum;
    }

    /**
     * Set the value of tijdEnDatum
     *
     * @param tijdEnDatum new value of tijdEnDatum
     */
    public void setTijdEnDatum(Date tijdEnDatum) {
        this.tijdEnDatum = tijdEnDatum;
    }

    public boolean addAgendaItem(AgendaItem item) {
        this.agendaItems.add(item);
        return true;
    }

    public void addAgendaItems(List<AgendaItem> items) {
        for (AgendaItem item : items) {
            addAgendaItem(item);
        }
    }

    public void removeAgendaItem(AgendaItem item) {
        agendaItems.remove(item);
    }

    @Override
    public Agenda agendaInladen(int agendaId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

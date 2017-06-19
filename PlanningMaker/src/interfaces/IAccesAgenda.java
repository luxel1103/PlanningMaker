/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.agenda.AgendaItem;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lesley Peters
 */
public interface IAccesAgenda extends Remote {

    boolean addAccount(String gebruikersnaam) throws RemoteException;

    boolean removeAccount(int gebruikersId) throws RemoteException;

    boolean addAgendaItem(AgendaItem item) throws RemoteException;

    boolean removeAgendaItem(int itemId) throws RemoteException;

    boolean koppelGebruikerAanItem(int gebruikerId, int itemId) throws RemoteException;

    boolean ontkoppelGebbruikerVanItem(int gebruikerId, int itemId) throws RemoteException;
}

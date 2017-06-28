/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.agenda.Agenda;
import fontyspublisher.IRemotePropertyListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * ILookAgenda.java 
 * Interface die methodes aanbied voor gebruikers die een
 * gedeelde agenda bekijken
 *
 * @author Lesley Peters
 */
public interface ILookAgenda extends Remote {

    /**
     * Deze methode word gebruikt voor het inladen van de gedeelde agenda
     *
     * @return PriveAgenda de agenda die de gebruiker wenst te bekijken
     * @throws java.rmi.RemoteException
     */
    Agenda agendaInladen() throws RemoteException;

    /**
     * Deze methode word gebruikt voor het registreren bij een gedeelde agenda
     * host
     *
     * @param listener Object that subscribes for changes
     * @param property the property that's binded for receiving changes
     * @throws java.rmi.RemoteException
     */
    void subscribe(IRemotePropertyListener listener, String property) throws RemoteException;
}

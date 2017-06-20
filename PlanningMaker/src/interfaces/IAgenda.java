/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.HostInfo;
import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Comment;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lesley Peters
 */
public interface IAgenda extends Remote {

    void setAgendaHost(HostInfo host) throws RemoteException;

    Agenda getGedeeldeAgenda(int agendaId) throws RemoteException;
    
    boolean addAccount(int agendaId, String gebruikersnaam) throws RemoteException;

    boolean addComment(int agendaId, Comment comment) throws RemoteException;

    boolean removeComment(Comment comment) throws RemoteException;

    boolean verlaatAgenda(int gebruikersId, int agendaId) throws RemoteException;
}

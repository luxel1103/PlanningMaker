/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Comment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public interface ILoggedIn extends Remote{
    
    void logout(int gebruikersId) throws RemoteException;
    
    Agenda getPriveAgenda(int agendaid) throws RemoteException;
    
    List<Agenda> getGedeeldeAgendas(int gebruikersid) throws RemoteException;
    
    boolean agendaItemToevoegen(int agendaId, String naam, String beschrijving, Date begintijd, Date eindtijd, String type) throws RemoteException;
    
    boolean commentToevoegen(int agendaId, Comment comment) throws RemoteException;
    
    boolean gedeeldeAgendaAanmaken(int gebruikersid, String naam) throws RemoteException;
}

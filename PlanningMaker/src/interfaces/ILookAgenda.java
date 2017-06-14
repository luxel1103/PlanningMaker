/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.agenda.Agenda;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lesley Peters
 */
public interface ILookAgenda extends Remote {

    Agenda agendaInladen(int agendaId) throws RemoteException;
    
    boolean agendaOpslaan(Agenda agenda) throws RemoteException;
}

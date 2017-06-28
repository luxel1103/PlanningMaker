/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.HostInfo;
import classes.agenda.Agenda;
import classes.agenda.Comment;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * IAgenda.java 
 * Interface die methodes aanbied voor de host van een gedeelde
 * agenda, deze methodes worden gebruik om op de server aanpassingen te maken
 * aan een gedeelde agenda.
 *
 * @author Lesley Peters
 */
public interface IAgenda extends Remote {

    /**
     * Deze methode word gebruikt om het ip en port nummer door te geven aan de
     * server zodat andere clients deze informatie kunnen ophalen en gebruiken
     * om met de betreffende host te verbinden.
     *
     * @param host Object van klasse HostInfo die een ip adres en port nummer
     * bevat
     * @throws java.rmi.RemoteException
     */
    void setAgendaHost(HostInfo host) throws RemoteException;

    /**
     * Deze methode word gebruikt om de meest recente versie van een gedeelde
     * agenda op te vragen van de server
     *
     * @param agendaId id van de agenda die opgehaald moet worden
     * @return Agenda het daadwerkelijke agenda object word teruggegeven.
     * @throws java.rmi.RemoteException
     */
    Agenda getGedeeldeAgenda(int agendaId) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het toevoegen van een account aan een
     * gedeelde agenda
     *
     * @param agendaId id van de betreffende agenda
     * @param gebruikersnaam van de gebruiker die toegevoegd moet worden
     * @return true indien de gebruiker succesvol toegevoegd is geworden, false
     * indien de gebruiker al in de gedeelde agenda zit, of de gebruiker niet
     * bestaat
     * @throws java.rmi.RemoteException
     */
    boolean addAccount(int agendaId, String gebruikersnaam) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het toevoegen van een comment aan een
     * agenda
     *
     * @param agendaId id van de betreffende agenda
     * @param comment Comment object met informatie van de toe te voegen comment
     * @return true indien de comment succesvol toegevoegd is geworden, false
     * indien de niet toegevoegd is geworden.
     * @throws java.rmi.RemoteException
     */
    boolean addComment(int agendaId, Comment comment) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het verwijderen van een comment
     *
     * @param comment de comment die verwijderd moet worden
     * @return true indien de comment succesvol verwijderd is geworden, false
     * indien het verwijderen niet is gelukt
     * @throws java.rmi.RemoteException
     */
    boolean removeComment(Comment comment) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het ontkoppelen van een gebruiker aan een
     * gedeelde agenda
     *
     * @param gebruikersId id van de betreffende gebruiker
     * @param agendaId id van de betreffende agenda
     * @return true indien de gebruiker succesvol verwijderd is geworden, false
     * indien het verwijderen is mislukt
     * @throws java.rmi.RemoteException
     */
    boolean verlaatAgenda(int gebruikersId, int agendaId) throws RemoteException;
}

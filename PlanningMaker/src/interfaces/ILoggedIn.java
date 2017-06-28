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
import java.util.Date;
import java.util.List;

/**
 *
 * ILoggedIn.java 
 * Interface die methodes aanbied voor gebruikers die ingelogd zijn.
 *
 * @author Lesley Peters
 */
public interface ILoggedIn extends Remote {

    /**
     * Deze methode word gebruikt voor het uitloggen van een gebruiker
     *
     * @param gebruikersId id van de gebruiker die uitgelogd word
     * @throws java.rmi.RemoteException
     */
    void logout(int gebruikersId) throws RemoteException;

    /**
     * Deze methode word gebruikt om de prive agenda van een gebruiker op te
     * halen
     *
     * @param agendaid id van de prive agenda die opgehaald moet worden
     * @return PriveAgenda de prive agenda van een gebruiker
     * @throws java.rmi.RemoteException
     */
    Agenda getPriveAgenda(int agendaid) throws RemoteException;

    /**
     * Deze methode word gebruikt om gedeelde agendas op te halen waar de
     * gebruiker aan deel neemt.
     *
     * @param gebruikersid id van de betreffende gebruiker
     * @return List<Agenda> lijst met gedeelde agendas waar de gebruiker aan
     * deel neemt.
     * @throws java.rmi.RemoteException
     */
    List<Agenda> getGedeeldeAgendas(int gebruikersid) throws RemoteException;

    /**
     * Deze methode word gebruikt om een agenda item toe te voegen aan een prive
     * agenda
     *
     * @param agendaId id van de agenda waar een item aan toegevoegd gaat worden
     * @param naam naam van het agenda item
     * @param beschrijving bevat de beschrijving van een agenda item
     * @param begintijd bevat de begintijd van een event of is null als het
     * agenda item een taak is
     * @param eindtijd bevat de eindtijd van een agendaitem
     * @param type bevat het type "taak" of "event"
     * @return true indien het toevoegen gelukt is, false indien het toevoegen
     * mislukt is.
     * @throws java.rmi.RemoteException
     */
    boolean agendaItemToevoegen(int agendaId, String naam, String beschrijving, Date begintijd, Date eindtijd, String type) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het toevoegen van een comment aan een
     * prive agenda
     *
     * @param agendaId id van de prive agenda
     * @param comment Comment die toegevoegd moet worden
     * @return true indien het toevoegen gelukt is, false indien het toevoegen
     * mislukt is.
     * @throws java.rmi.RemoteException
     */
    boolean commentToevoegen(int agendaId, Comment comment) throws RemoteException;

    /**
     * Deze methode word gebruikt voor aanmaken van een nieuwe gedeelde agenda
     *
     * @param gebruikersId id van gebruiker die de agenda aanmaakt
     * @param naam de naam van de nieuwe gedeelde agenda
     * @return true indien het toevoegen gelukt is, false indien het toevoegen
     * mislukt is.
     * @throws java.rmi.RemoteException
     */
    boolean gedeeldeAgendaAanmaken(int gebruikersId, String naam) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het ophalen van informatie van de host
     * van een gedeelde agenda
     *
     * @param agendaId id van de betreffende gedeelde agenda
     * @return HostInfo object indien iemand de betreffende agenda aan het
     * hosten is, null indien niemand deze agenda aan het hosten is.
     * @throws java.rmi.RemoteException
     */
    HostInfo getAgendaHost(int agendaId) throws RemoteException;

}

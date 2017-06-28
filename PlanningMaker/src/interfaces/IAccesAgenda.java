/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * IAccesAgenda.java 
 * Interface die methodes aan gebruikers aanbied om bepaalde
 * functies aan te roepen voor het maken van wijzigingen aan een gedeelde
 * agenda. Deze interface word alleen toegekend aan gebruikers van een gedeelde
 * agenda die extra rechten hebben om een gedeelde agenda aan te passen.
 *
 * @author Lesley Peters
 */
public interface IAccesAgenda extends Remote {

    /**
     * Deze methode word gebruikt voor het toevoegen van een gebruiker aan een
     * gedeelde agenda
     *
     * @param gebruikersnaam van de gebruiker die toegevoegd gaat worden
     * @return true indien de gebruiker succesvol toegevoegd is geworden, false
     * indien de gebruiker al in de gedeelde agenda zit, of de gebruiker niet
     * bestaat
     * @throws java.rmi.RemoteException
     */
    boolean addAccount(String gebruikersnaam) throws RemoteException;

    /**
     * Deze methode word gebruikt voor het verwijderen van een gebruiker uit een
     * gedeelde agenda
     *
     * @param gebruikersId van de gebruiker die verwijderd gaat worden
     * @return true indien de gebruiker verwijderd is geworden, false indien het
     * verwijderen van de gebruiker niet is gelukt
     * @throws java.rmi.RemoteException
     */
    boolean removeAccount(int gebruikersId) throws RemoteException;

    /**
     * Deze methode kan aangeroepen worden zodat een gedeelde agenda geupdate
     * kan worden en vervolgens alle changes pusht naar andere clients
     *
     * @throws java.rmi.RemoteException
     */
    void updateAgenda() throws RemoteException;

    /**
     * Deze methode geeft de mogelijkheid om een agenda item van een gedeelde agenda te verwijderen.
     *
     * @param itemId het id van het betreffende agenda item dat verwijderd moet worden
     * @return true indien het agenda item succesvol is verwijderd, false indien het verwijderen niet gelukt is.
     * @throws java.rmi.RemoteException
     */
    boolean removeAgendaItem(int itemId) throws RemoteException;

    /**
     * Deze methode kan gebruikt worden om een gebruiker aan een bepaald agenda item te koppelen
     *
     * @param gebruikerId id van de gebruiker die toegevoegd gaat worden
     * @param itemId id van het item waar de gebruiker aan gekoppeld word
     * @return true indien de gebruiker succesvol gekoppeld is geworden, false
     * indien het koppelen niet gelukt is
     * @throws java.rmi.RemoteException
     */
    boolean koppelGebruikerAanItem(int gebruikerId, int itemId) throws RemoteException;

    /**
     * Deze methode kan gebruikt worden om een gebruiker aan een bepaald agenda item te ontkoppelen
     *
     * @param gebruikerId id van de gebruiker die ontkoppeld gaat worden
     * @param itemId id van het item waar de gebruiker aan ontkoppeld word
     * @return true indien de gebruiker succesvol ontkoppeld is geworden, false
     * indien het ontkoppelen niet gelukt is
     * @throws java.rmi.RemoteException
     */
    boolean ontkoppelGebbruikerVanItem(int gebruikerId, int itemId) throws RemoteException;
}

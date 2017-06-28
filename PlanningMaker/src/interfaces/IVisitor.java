/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * IVisitor.java 
 * Interface die methodes aanbied voor een bezoeker die niet is
 * ingelogd.
 *
 * @author Lesley Peters
 */
public interface IVisitor extends Remote {

    /**
     * Methede die een gebruiker ophaald uit de database en daarna zal inloggen.
     *
     * @param gebruikersnaam ingevoerde gebruikersnaam door de gebruiker
     * @param wachtwoord ingevoerde wachtwoord door de gebruiker
     * @return Account object dat moet worden opgehaald, null indien het ophalen
     * mislukt is.
     * @throws RemoteException
     */
    Account getGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException;

    /**
     * Deze methode kan gebruikt worden voor het registreren van een nieuwe
     * gebruiker
     *
     * @param gebruikersnaam voor het nieuwe account
     * @param wachtwoord het ingevoerde wachtwoord door de gebruiker
     * @return Account object indien het registreren gelukt is, null indien dit
     * niet gelukt is geworden
     * @throws RemoteException
     */
    Account registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException;
}

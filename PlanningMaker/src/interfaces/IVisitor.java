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
 * @author Lesley Peters
 */
public interface IVisitor extends Remote {


    /**
     *
     * @param gebruikersnaam ingevoerde gebruikersnaam door de gebruiker
     * @param wachtwoord ingevoerde wachtwoord door de gebruiker
     * @return het gebruikers id van het bijbehorende account, 0 indien het
     * account niet bestaat of de gegevens incorrect zijn
     * @throws RemoteException
     */
    Account getGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException;

    /**
     *
     * @param gebruikersnaam voor het nieuwe account
     * @param wachtwoord het ingevoerde wachtwoord door de gebruiker
     * @return het gebruikers id van het nieuwe account, 0 indien het
     * registreren mislukt is
     * @throws RemoteException
     */
     Account registreerGebruiker(String gebruikersnaam, String wachtwoord) throws RemoteException;
}

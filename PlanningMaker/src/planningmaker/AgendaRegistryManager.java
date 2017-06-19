/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planningmaker;

import classes.Account;
import interfaces.IAccesAgenda;
import interfaces.ILoggedIn;
import interfaces.ILookAgenda;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class AgendaRegistryManager {

    //User
    Account account;

    private ILookAgenda lookagenda;
    private IAccesAgenda accesagenda;

    // Set port number
    private static int portNumber = 2004;

    // Set binding name for Grand Exchange
    private static final String bindingName = "AgendaServer";

    // References to registry and Grand Exchange
    private Registry registry = null;
    private InetAddress localhost;
    private String ipAddress;

    public AgendaRegistryManager(String ip, int port) {
        this.ipAddress = ip;
        this.portNumber = port;
        //getLocalHostIp();
        setupRegistry();
    }

    public ILookAgenda getLookAgenda() {
        return lookagenda;
    }

    public IAccesAgenda getAccesAgenda() {
        return accesagenda;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void getLookAgendaInterface() {
        if (registry != null) {
            try {
                System.out.println("Trying to lookup Visitor Interface...");
                lookagenda = (ILookAgenda) registry.lookup(bindingName);
                System.out.println("Interface reference IS bound.");

                this.lookagenda = lookagenda;

            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Client: Cannot bind Visitor interface");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                System.out.println("Interface reference is NOT bound");
                lookagenda = null;

            }
        }
    }

    public void getAccesAgendaInterface() {
        if (registry != null) {
            try {
                System.out.println("Trying to lookup Visitor Interface...");
                accesagenda = (IAccesAgenda) registry.lookup(bindingName);
                System.out.println("Interface reference IS bound.");

                this.accesagenda = accesagenda;

            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Client: Cannot bind Visitor interface");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                System.out.println("Interface reference is NOT bound");
                accesagenda = null;

            }
        }
    }

    public void getLocalHostIp() {

        try {
            localhost = InetAddress.getLocalHost();
            ipAddress = localhost.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(RegistryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setupRegistry() {

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);

            if (registry != null) {
                System.out.println("Client: Registry located");
            }

        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
            System.out.println("Client reference = null");
        }

    }
}

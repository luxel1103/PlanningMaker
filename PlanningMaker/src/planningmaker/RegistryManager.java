/*
 * This project is for PTS3 Fontys Eindhoven
 * Jorian Vas, Kyle van Raaij, Pieter Beukelman, Sam Dirkx, Lesley Peeters, Robin Welten
 * �2016-2017
 */
package planningmaker;

import classes.Account;
import interfaces.IVisitor;
import interfaces.ILoggedIn;
import interfaces.IAgenda;
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
public class RegistryManager {

    //User
    Account account;

    //Interfaces
    private IVisitor visitor;
    private ILoggedIn loggedin;
    private IAgenda agenda;

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for Grand Exchange
    private static final String bindingName = "PlanningMakerServer";

    // References to registry and Grand Exchange
    private Registry registry = null;
    private InetAddress localhost;
    private String ipAddress = "localhost";

    public RegistryManager() {
        getLocalHostIp();
        setupRegistry();
    }

    public IVisitor getVisitor() {
        return visitor;
    }

    public ILoggedIn getLoggedIn() {
        return loggedin;
    }

    public IAgenda getAgenda() {
        return agenda;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void getVisitorInterface() {
        if (registry != null) {
            try {
                System.out.println("Trying to lookup Visitor Interface...");
                visitor = (IVisitor) registry.lookup(bindingName);
                System.out.println("Interface reference IS bound.");

                this.visitor = visitor;

            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Client: Cannot bind Visitor interface");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                System.out.println("Interface reference is NOT bound");
                visitor = null;

            }
        }
    }

    public void getLoggedInInterface() {
        if (registry != null) {
            try {
                System.out.println("Trying to lookup Visitor Interface...");
                loggedin = (ILoggedIn) registry.lookup(bindingName);
                System.out.println("Interface reference IS bound.");

                this.loggedin = loggedin;

            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Client: Cannot bind Visitor interface");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                System.out.println("Interface reference is NOT bound");
                loggedin = null;

            }
        }
    }

    public void getAgendaInterface() {
        if (registry != null) {
            try {
                System.out.println("Trying to lookup Visitor Interface...");
                agenda = (IAgenda) registry.lookup(bindingName);
                System.out.println("Interface reference IS bound.");

                this.agenda = agenda;

            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Client: Cannot bind Visitor interface");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                System.out.println("Interface reference is NOT bound");
                agenda = null;

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

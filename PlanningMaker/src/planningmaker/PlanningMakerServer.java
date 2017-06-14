/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planningmaker;
import classes.PlanningMaker;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesley Peters
 */
public class PlanningMakerServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for Grand Exchange
    private static final String bindingName = "PlanningMakerServer";

    // References to registry and Grand Exchange
    private Registry registry = null;
    private PlanningMaker planningMaker = null;

    public PlanningMakerServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create Grand Exchange
        try {
            planningMaker = new PlanningMaker();
            System.out.println("Server: PlanningMaker created !");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create PlanningMaker Object");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            planningMaker = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind Grand Exchange using registry
        try {
            registry.rebind(bindingName, planningMaker);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind PlanningMaker");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps;

            allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(PlanningMakerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        PlanningMakerServer server = new PlanningMakerServer();
    }

}

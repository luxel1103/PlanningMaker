/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planningmaker;

import classes.agenda.Agenda;
import classes.AgendaHost;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import planningmaker.PlanningMakerServer;

/**
 *
 * @author Lesley Peters
 */
public class AgendaServer extends Thread{
    
    // Set port number
    private static int portNumber;
    private int agendaid;

    // Set binding name for Grand Exchange
    private static final String bindingName = "AgendaServer";

    // References to registry and Grand Exchange
    private Registry registry = null;
    private AgendaHost host = null;
    
    public AgendaServer(int agendaid) {
        this.agendaid = agendaid;
        System.out.println(agendaid);
        int length = String.valueOf(agendaid).length();
        if (length == 1) {
            portNumber = Integer.parseInt("200" + agendaid);
        }else if(length == 2){
            portNumber = Integer.parseInt("20" + agendaid);
        } else{
            String id = Integer.toString(agendaid);
            portNumber = Integer.parseInt("2"+id.substring(id.length() - 3));
        }
        
        // Print port number for registry
        System.out.println("Agenda: Port number " + portNumber);

        
    }
    
    @Override
    public void run(){
        // Create Grand Exchange
        System.out.println("agendaid = "+agendaid);
        try {
            host = new AgendaHost(agendaid);
        } catch (RemoteException ex) {
            Logger.getLogger(AgendaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Agenda: agenda created !");
        

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Agenda: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Agenda: Cannot create registry");
            System.out.println("Agenda: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind Grand Exchange using registry
        try {
            registry.rebind(bindingName, host);
        } catch (RemoteException ex) {
            System.out.println("Agenda: Cannot bind Agenda");
            System.out.println("Agenda: RemoteException: " + ex.getMessage());
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
    
    
    
    
}

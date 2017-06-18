/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author Lesley Peters
 */
public class HostInfo implements Serializable {
    
    private int agendaId;
    private String ip;
    private int portNumber;
    
    public HostInfo(int agendaId, String ip, int portNumber){
        this.agendaId = agendaId;
        this.ip = ip;
        this.portNumber = portNumber;
    }
    
    public int getAgendaId(){
        return this.agendaId;
    }
    
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public String getIp(){
        return this.ip;
    }
    
    public int getPortNumber(){
        return this.portNumber;
    }
    
}

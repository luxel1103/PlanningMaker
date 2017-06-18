/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import interfaces.IAccesAgenda;
import interfaces.ILookAgenda;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class Agenda implements Serializable {

    private int id;
    private String naam;

    private Date tijdEnDatum;
    private List<AgendaItem> agendaItems;
    private List<AgendaItem> gedeeldeItems;

    public Agenda(int id, String naam) {
        this.id = id;
        this.naam = naam;
        agendaItems = new ArrayList<>();
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the value of tijdEnDatum
     *
     * @return the value of tijdEnDatum
     */
    public Date getTijdEnDatum() {
        return tijdEnDatum;
    }

    /**
     * Set the value of tijdEnDatum
     *
     * @param tijdEnDatum new value of tijdEnDatum
     */
    public void setTijdEnDatum(Date tijdEnDatum) {
        this.tijdEnDatum = tijdEnDatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
    
    public boolean addAgendaItem(AgendaItem item) {
        if(item != null){
            this.agendaItems.add(item);
            return true;
        }
        else{
            System.out.println("Agenda item is null en kan niet aan de lijst worden toegevoegd.");
            return false;
        }
    }

    public void addAgendaItems(List<AgendaItem> items) {
        for (AgendaItem item : items) {
            if(item != null){
                this.agendaItems.add(item);
            }
            else{
                System.out.println("Agenda item is null en kan niet aan de lijst worden toegevoegd.");
            }
        }
    }
    
    public List<AgendaItem> getAgendaItems(){
        return agendaItems;
    }

    public void removeAgendaItem(AgendaItem item) {
        agendaItems.remove(item);
    }
    
    public void removeAlleAgendaItems(){
        agendaItems.clear();
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.agenda.Agenda;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class Account implements Serializable {

    private int id;
    private String gebruikersnaam;
    private String wachtwoord;
    private Agenda priveAgenda;
    private List<Agenda> gedeeldeAgendas;

    public Account() {
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
     * Get the value of gebruikersnaam
     *
     * @return the value of gebruikersnaam
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    /**
     * Set the value of gebruikersnaam
     *
     * @param gebruikersnaam new value of gebruikersnaam
     */
    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Get the value of wachtwoord
     *
     * @return the value of wachtwoord
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * Set the value of wachtwoord
     *
     * @param wachtwoord new value of wachtwoord
     */
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setPriveAgenda(Agenda agenda){
        this.priveAgenda = agenda;
    }
    
    public Agenda getPriveAgenda(){
        return this.priveAgenda;
    }
    
    public void addGedeeldeAgenda(Agenda nieuweAgenda){
        this.gedeeldeAgendas.add(priveAgenda);
        //todo: add taken van de gedeelde agenda toe aan de prive agenda
    }
    
    public void addGedeeldeAgendas(List<Agenda> nieuweAgendas){
        for(Agenda nieuweAgenda : nieuweAgendas){
            addGedeeldeAgenda(nieuweAgenda);
        }
    }
    
    public void removeGedeeldeAgenda(Agenda agenda){
        gedeeldeAgendas.remove(agenda);
    }
}

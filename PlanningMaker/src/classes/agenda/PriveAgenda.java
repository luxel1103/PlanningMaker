/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class PriveAgenda extends Agenda {

    private List<Agenda> gedeeldeAgendas;
    

    public PriveAgenda(int id) {
        super(id);
    }
    
}

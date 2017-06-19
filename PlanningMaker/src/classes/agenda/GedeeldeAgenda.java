/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import classes.Account;
import java.util.List;

/**
 *
 * @author Lesley Peters
 */
public class GedeeldeAgenda extends Agenda {

    private List<Account> leden;
    private List<Account> uitgenodigden;
    private List<Account> ledenMetRechten;

    public GedeeldeAgenda(int id, String naam) {
        super(id, naam);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.agenda;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Lesley Peters
 */
public class Comment implements Serializable{
    
    private int accountId;
    private int itemId;
    private String bericht;
    private Date verzendDatum;
    
    public Comment(){
        
    }
}

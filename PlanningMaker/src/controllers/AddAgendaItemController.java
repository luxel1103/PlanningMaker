/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.ILoggedIn;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import planningmaker.RegistryManager;

/**
 * FXML Controller class
 *
 * @author Lesley Peters
 */
public class AddAgendaItemController implements Initializable {

    @FXML
    private Label lblToevoegen;
    @FXML
    private Label lblBeginTijd;
    @FXML
    private Label lblError;
    @FXML
    private Button btnToevoegen;
    @FXML
    private TextField tbNaam;
    @FXML
    private TextArea tbBeschrijving;
    @FXML
    private DatePicker dtpBeginTijd;
    @FXML 
    private DatePicker dtpEindTijd;
    

    private RegistryManager RM;
    private ILoggedIn loggedin;
    private String type;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setUp(RegistryManager RM, String type) throws RemoteException {
        this.RM = RM;
        this.loggedin = RM.getLoggedIn();
        this.type = type;
        if(type.equals("taak")){
            lblToevoegen.setText("Taak toevoegen");
            lblBeginTijd.setVisible(false);
            dtpBeginTijd.setVisible(false);
        }else{
            lblToevoegen.setText("Event toevoegen");
        }
    }
    
    public void addAgendaItem(){
        System.out.println(tbNaam.getText());
        System.out.println(tbBeschrijving.getText());
        System.out.println(dtpBeginTijd.getValue());
        System.out.println(dtpEindTijd.getValue());
    }

}

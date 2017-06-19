/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.HostInfo;
import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Event;
import classes.agenda.Taak;
import fontyspublisher.IRemotePropertyListener;
import interfaces.IAccesAgenda;
import interfaces.ILookAgenda;
import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import planningmaker.AgendaRegistryManager;
import planningmaker.AgendaServer;
import planningmaker.RegistryManager;

/**
 * FXML Controller class
 *
 * @author lesley
 */
public class GedeeldeAgendaController extends UnicastRemoteObject implements Initializable, IRemotePropertyListener {

    @FXML
    private Label lblAgendaNaam;
    @FXML
    private Label lblDatumEnTijd;
    @FXML
    private ListView lvAgendaItems;
    @FXML
    private ListView lvLeden;
    @FXML
    private Button btnAddTaak;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnVerwijderItem;
    @FXML
    private TextField tbGebruikersnaam;
    @FXML
    private Button btnGebruikerToevoegen;
    @FXML
            private Label lblError;

    RegistryManager RM;
    AgendaRegistryManager ARM;
    ILookAgenda lookagenda;
    IAccesAgenda accesagenda;
    Agenda agenda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public GedeeldeAgendaController() throws RemoteException {

    }

    void setUp(RegistryManager RM, HostInfo host) throws RemoteException {
        this.RM = RM;
        this.ARM = new AgendaRegistryManager(host.getIp(), host.getPortNumber());
        ARM.getLookAgendaInterface();
        this.lookagenda = ARM.getLookAgenda();
        ARM.getAccesAgendaInterface();
        this.accesagenda = ARM.getAccesAgenda();
        
        this.agenda = lookagenda.agendaInladen();
        
        try {
            lookagenda.subscribe(this, "agenda");
            System.out.println("Kan nu changes ontvangen van de agenda server.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateGui() {
        lvAgendaItems.getItems().clear();
        for (AgendaItem item : agenda.getAgendaItems()) {
            if (item instanceof Taak) {
                lvAgendaItems.getItems().add("Taak: " + item.getNaam() + " -> " + item.getBeschrijving());
            } else if (item instanceof Event) {
                lvAgendaItems.getItems().add("Event: " + item.getNaam() + " -> " + item.getBeschrijving());
            }
        }

    }

    public void addTaak() throws RemoteException {
        lookagenda.agendaOpslaan(agenda);
    }

    public void addEvent() {

    }

    public void verwijderItem() {

    }

    public void gebruikerToevoegen() throws RemoteException {
        if(tbGebruikersnaam.getText().equalsIgnoreCase("")){
            lblError.setText("Er is geen gebruikersnaam ingevuld.");
        }else{
            if(accesagenda != null){
                if(accesagenda.addAccount(tbGebruikersnaam.getText())){
                    lblError.setText(tbGebruikersnaam.getText() + " is toegevoegd aan de agenda.");
                }else{
                    lblError.setText("Het is ons niet gelukt om de gebruiker toe te voegen aan de agenda.");
                }
            }else{
                lblError.setText("Je hebt geen rechten om een nieuwe gebruiker uit te nodigen.");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        try {
            agenda = (Agenda) evt.getNewValue();
            System.out.println("Agenda ontvangen van agenda server: " + agenda.getNaam());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

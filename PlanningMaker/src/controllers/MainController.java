/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import planningmaker.AgendaServer;
import classes.agenda.Agenda;
import classes.agenda.AgendaItem;
import classes.agenda.Event;
import classes.agenda.Taak;
import interfaces.ILoggedIn;
import interfaces.ILookAgenda;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import planningmaker.AgendaRegistryManager;
import planningmaker.RegistryManager;

/**
 * FXML Controller class
 *
 * @author Lesley Peters
 */
public class MainController implements Initializable {

    @FXML
    private Button btTest;
    @FXML
    private Label lblError;
    @FXML
    private Button btnAddTaak;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button maakGedeeldeAgenda;
    @FXML
    private Button opgenGedeeldeAgenda;
    @FXML
    private ListView<String> lvGedeeldeAgendas;
    @FXML
    private ListView<String> lvTakenEnEvents;
    @FXML
    private TextField tbGedeeldeAgendaNaam;
    @FXML
    private Label lblWelkom;
    @FXML
    private Label lblDatumTijd;

    private RegistryManager RM;
    private AgendaRegistryManager ARM;
    private ILoggedIn loggedin;
    private ILookAgenda lookagenda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUp(RegistryManager RM) throws RemoteException {
        this.RM = RM;
        RM.getLoggedInInterface();
        this.loggedin = RM.getLoggedIn();
        lblWelkom.setText("Welkom: " + RM.getAccount().getGebruikersnaam());


        updateGui();

    }

    public void verwijderItem() {

    }

    public void updateGui() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //Datum en tijd weergeven
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    lblDatumTijd.setText(dateFormat.format(date));

                    //Priveagenda ophalen en weergeven
                    try {
                        RM.getAccount().setPriveAgenda(loggedin.getPriveAgenda(RM.getAccount().getPriveAgendaId()));
                        lvTakenEnEvents.getItems().clear();
                        for (AgendaItem item : RM.getAccount().getPriveAgenda().getAgendaItems()) {
                            if (item instanceof Taak) {
                                lvTakenEnEvents.getItems().add("Taak: " + item.getNaam() + " -> " + item.getBeschrijving());
                            } else if (item instanceof Event) {
                                lvTakenEnEvents.getItems().add("Event: " + item.getNaam() + " -> " + item.getBeschrijving());
                            }
                        }
                    } catch (Exception ex) {
                        lblError.setText("Het is niet gelukt om de prive agenda op te halen");
                        System.out.println("Ophalen van de prive agenda is mislukt.");
                        System.out.println(ex.getMessage());
                    }

                    //gedeelde agendas ophalen
                    try {
                        List<Agenda> gedeeldeAgendas = loggedin.getGedeeldeAgendas(RM.getAccount().getId());
                        RM.getAccount().setGedeeldeAgendas(gedeeldeAgendas);
                        lvGedeeldeAgendas.getItems().clear();
                        for (Agenda gedeeldeAgenda : RM.getAccount().getGedeeldeAgendas()) {
                            lvGedeeldeAgendas.getItems().add(gedeeldeAgenda.getNaam());
                        }
                    } catch (Exception ex) {
                        lblError.setText("Het is niet gelukt om de gedeelde agendas op te halen");
                        System.out.println("Ophalen van de gedeelde agendas is mislukt.");
                        System.out.println(ex.getMessage());
                    }
                    lblError.setText("");
                });
            }
        }, 0, 5000);
    }

    public void button() throws RemoteException {
        RM.getAccount().setPriveAgenda(loggedin.getPriveAgenda(RM.getAccount().getPriveAgendaId()));
        System.out.println("Todo voor gebruiker: " + RM.getAccount().getGebruikersnaam());
        for (AgendaItem item : RM.getAccount().getPriveAgenda().getAgendaItems()) {
            System.out.println(item.getNaam() + " -> " + item.getBeschrijving());
        }
    }

    public void addTaak() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddAgendaItem.fxml"));
        Parent root = loader.load();
        AddAgendaItemController controller = (AddAgendaItemController) loader.getController();
        controller.setUp(RM, "taak");
        Stage inputStage = new Stage();
        Scene newScene = new Scene(root);
        inputStage.setScene(newScene);
        inputStage.setTitle("Planning Maker - " + RM.getAccount().getGebruikersnaam());
        inputStage.show();

    }

    public void addEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddAgendaItem.fxml"));
        Parent root = loader.load();
        AddAgendaItemController controller = (AddAgendaItemController) loader.getController();
        controller.setUp(RM, "event");
        Stage inputStage = new Stage();
        Scene newScene = new Scene(root);
        inputStage.setScene(newScene);
        inputStage.setTitle("Planning Maker - " + RM.getAccount().getGebruikersnaam());
        inputStage.show();
    }

    public void maakGedeeldeAgenda() throws RemoteException {
        boolean succes = loggedin.gedeeldeAgendaAanmaken(RM.getAccount().getId(), tbGedeeldeAgendaNaam.getText());
        if (succes) {
            lblError.setText("Gedeelde agenda is aangemaakt.");
        } else {
            lblError.setText("Gedeelde agenda kon niet worden aangemaakt.");
        }
    }

    public void openGedeeldeAgenda() {
        AgendaServer agendaServer = new AgendaServer(4);
        agendaServer.start();       
    }
    
    public void Connect() throws RemoteException{
        this.ARM = new AgendaRegistryManager("192.168.178.39",2004);
        ARM.getLookAgendaInterface();
        this.lookagenda = ARM.getLookAgenda();
        Agenda agenda = lookagenda.agendaInladen();
        System.out.println("Agenda: " + agenda.getNaam() + " ontvangen van de agenda server.");
    }
}

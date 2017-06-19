/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.agenda.AgendaItem;
import interfaces.ILoggedIn;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    @FXML
    private TextField tbBeginTijdUur;
    @FXML
    private TextField tbBeginTijdMinuut;
    @FXML
    private TextField tbEindTijdUur;
    @FXML
    private TextField tbEindTijdMinuut;
    @FXML
    private Label lblEventLabel1;
    @FXML
    private Label lblEventLabel2;

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
        if (type.equals("taak")) {
            lblToevoegen.setText("Taak toevoegen");
            lblBeginTijd.setVisible(false);
            dtpBeginTijd.setVisible(false);
            tbBeginTijdUur.setVisible(false);
            tbBeginTijdMinuut.setVisible(false);
            lblEventLabel1.setVisible(false);
            lblEventLabel2.setVisible(false);
        } else {
            lblToevoegen.setText("Event toevoegen");
        }
    }

    public void addAgendaItem() throws ParseException, RemoteException {
        int agendaId = RM.getAccount().getPriveAgendaId();
        AgendaItem nieuwItem = null;
        if (type.equals("taak")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String eindTijdJaar = Integer.toString(dtpEindTijd.getValue().getYear());
            String eindTijdMaand = Integer.toString(dtpEindTijd.getValue().getMonthValue());
            String eindTijdDag = Integer.toString(dtpEindTijd.getValue().getDayOfMonth());
            String eindTijdUur = tbEindTijdUur.getText();
            String eindTijdMinuut = tbEindTijdMinuut.getText();
            String dateInString = eindTijdDag + "-" + eindTijdMaand + "-" + eindTijdJaar + " " + eindTijdUur + ":" + eindTijdMinuut + ":00";
            Date eindtijd = sdf.parse(dateInString);
            if (loggedin.agendaItemToevoegen(agendaId, tbNaam.getText(), tbBeschrijving.getText(), null, eindtijd, type)) {
                Stage stage = (Stage) tbNaam.getScene().getWindow();
                stage.close();
            } else {
                lblError.setText("Het is niet gelukt om de taak toe te voegen, probeer het opnieuw.");
            }
        } else {
            SimpleDateFormat sdfBegin = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String beginTijdJaar = Integer.toString(dtpBeginTijd.getValue().getYear());
            String beginTijdMaand = Integer.toString(dtpBeginTijd.getValue().getMonthValue());
            String beginTijdDag = Integer.toString(dtpBeginTijd.getValue().getDayOfMonth());
            String beginTijdUur = tbBeginTijdUur.getText();
            String beginTijdMinuut = tbBeginTijdMinuut.getText();
            String dateInStringBegin = beginTijdDag + "-" + beginTijdMaand + "-" + beginTijdJaar + " " + beginTijdUur + ":" + beginTijdMinuut + ":00";
            Date begintijd = sdfBegin.parse(dateInStringBegin);

            SimpleDateFormat sdfEind = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String eindTijdJaar = Integer.toString(dtpEindTijd.getValue().getYear());
            String eindTijdMaand = Integer.toString(dtpEindTijd.getValue().getMonthValue());
            String eindTijdDag = Integer.toString(dtpEindTijd.getValue().getDayOfMonth());
            String eindTijdUur = tbEindTijdUur.getText();
            String eindTijdMinuut = tbEindTijdMinuut.getText();
            String dateInStringEind = eindTijdDag + "-" + eindTijdMaand + "-" + eindTijdJaar + " " + eindTijdUur + ":" + eindTijdMinuut + ":00";
            Date eindtijd = sdfEind.parse(dateInStringEind);
            if (loggedin.agendaItemToevoegen(agendaId, tbNaam.getText(), tbBeschrijving.getText(), begintijd, eindtijd, type)) {
                Stage stage = (Stage) tbNaam.getScene().getWindow();
                stage.close();
            } else {
                lblError.setText("Het is niet gelukt om het event toe te voegen.");
            }
        }
        System.out.println(tbNaam.getText());
        System.out.println(tbBeschrijving.getText());
        System.out.println(dtpBeginTijd.getValue());
        System.out.println(dtpEindTijd.getValue());
    }

}

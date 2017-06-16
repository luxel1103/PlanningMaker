/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.agenda.AgendaItem;
import interfaces.ILoggedIn;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
    
    private RegistryManager RM;
    private ILoggedIn loggedin;

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
        try{
            RM.getAccount().setPriveAgenda(loggedin.getPriveAgenda(RM.getAccount().getPriveAgendaId()));
            System.out.println("Priveagenda succesvol opgehaald.");
        }catch(Exception ex){
            lblError.setText("Het is niet gelukt om de prive agenda op te halen");
            System.out.println("Ophalen van de prive agenda is mislukt.");
            System.out.println(ex.getMessage());
        }
    }
    
    public void button(){
        System.out.println("Todo voor gebruiker: " + RM.getAccount().getGebruikersnaam());
        for(AgendaItem item : RM.getAccount().getPriveAgenda().getAgendaItems()){
            System.out.println(item.getNaam() + " -> " + item.getBeschrijving());
        }
    }
    
    public void addTaak() throws IOException{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddAgendaItem.fxml"));
            Parent root = loader.load();
            AddAgendaItemController controller = (AddAgendaItemController) loader.getController();
            controller.setUp(RM,"taak");
            Stage inputStage = new Stage();
            Scene newScene = new Scene(root);
            inputStage.setScene(newScene);
            inputStage.setTitle("Planning Maker - " + RM.getAccount().getGebruikersnaam());
            inputStage.show();
            Stage stage = (Stage) lblError.getScene().getWindow();
            stage.close();
        
            lblError.setVisible(true);
            lblError.setText("Kan geen agenda taak toevoegen.");
        
    }
    
    public void addEvent() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddAgendaItem.fxml"));
            Parent root = loader.load();
            AddAgendaItemController controller = (AddAgendaItemController) loader.getController();
            controller.setUp(RM,"event");
            Stage inputStage = new Stage();
            Scene newScene = new Scene(root);
            inputStage.setScene(newScene);
            inputStage.setTitle("Planning Maker - " + RM.getAccount().getGebruikersnaam());
            inputStage.show();
            Stage stage = (Stage) lblError.getScene().getWindow();
            stage.close();
        
            lblError.setVisible(true);
            lblError.setText("Kan geen agenda taak toevoegen.");
    }
}

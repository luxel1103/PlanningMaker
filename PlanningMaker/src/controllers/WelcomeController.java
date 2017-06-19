/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Account;
import interfaces.ILoggedIn;
import planningmaker.RegistryManager;
import interfaces.IVisitor;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lesley Peters
 */
public class WelcomeController implements Initializable {

    @FXML
    TextField tbGebruikersnaam;
    @FXML
    TextField tbWachtwoord;
    @FXML
    Button btInloggen;
    @FXML
    Label lblError;

    private Account account;
    private RegistryManager RM;
    private IVisitor visitor;
    private ILoggedIn loggedIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RM = new RegistryManager();
        RM.getVisitorInterface();
        visitor = RM.getVisitor();
    }

    public void login() throws RemoteException, IOException {
        try {
            account = visitor.getGebruiker(tbGebruikersnaam.getText(), tbWachtwoord.getText());
            System.out.print(account);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (account != null) {
            RM.setAccount(account);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Main.fxml"));
            Parent root = loader.load();
            MainController controller = (MainController) loader.getController();
            controller.setUp(RM);
            Stage inputStage = new Stage();
            Scene newScene = new Scene(root);
            inputStage.setScene(newScene);
            inputStage.setTitle("Planning Maker - " + account.getGebruikersnaam());
            inputStage.show();
            Stage stage = (Stage) tbGebruikersnaam.getScene().getWindow();
            stage.close();
            System.out.println(account.getGebruikersnaam() + " is succesvol ingelogd");
            lblError.setText(account.getGebruikersnaam() + " is ingelogd.");
        } else {
            lblError.setVisible(true);
            System.out.println("inloggen is mislukt");
            lblError.setText(" Onze poging om je in te loggen is mislukt. Controleer je inloggegevens en probeer het opnieuw.");
        }
    }

}

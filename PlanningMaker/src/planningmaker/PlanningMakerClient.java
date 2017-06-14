/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planningmaker;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Lesley Peters
 */
public class PlanningMakerClient extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));
        Parent root = loader.load();
        primaryStage.getIcons().add(new Image("/Icon/scale.png"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root,Color.TRANSPARENT));
        //primaryStage.setTitle("Grand Exchange-Login");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author user
 */
public class login extends Application {
    
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws Exception {
        try {
    
       Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Scene scene = new Scene(root);
       stage.initStyle(StageStyle.UNDECORATED);
       stage.setScene(scene);
       stage.show();
        root.setOnMousePressed((MouseEvent event)-> {
            x= event.getSceneX();
            y= event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event)-> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
         
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
 


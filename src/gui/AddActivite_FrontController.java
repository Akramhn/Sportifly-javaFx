/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Activiter;
import entities.Categorie;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ActiviterService;
import services.CategorieService;
import util.SessionManager;



/**
 * FXML Controller class
 *
 * @author hadjn
 */
public class AddActivite_FrontController implements Initializable {

   


@FXML
    private TextField titre;
@FXML
    private DatePicker date_deb;
@FXML
    private DatePicker date_fin;
 @FXML
    private Button btn_ajouter;
ActiviterService as = new ActiviterService();
CategorieService cs = new CategorieService();
private SessionManager session;
    @FXML
    private ComboBox<String> categ;
    @FXML
    private Button btnRetour;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Button btn = new Button();
       
         try {
        for (Categorie cat : cs.recuperer()) {
            categ.getItems().add(cat.getNom() );
        }   } catch (SQLException ex) {
        Logger.getLogger(AddActivite_FrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
       

       
    }

    


 @FXML
private void ajouteract(ActionEvent event) throws IOException, SQLException {
    // Check if the required fields are filled
    if (titre.getText().isEmpty() || date_deb.getValue() == null || date_fin.getValue() == null || categ.getValue() == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the required fields!");
        alert.showAndWait();
        return;
    }

    // Check if date_deb is more than today
    LocalDate today = LocalDate.now();
    if (date_deb.getValue().isBefore(today)) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The start date must be more than today!");
        alert.showAndWait();
        return;
    }

    // Check if date_fin is more than date_deb
    if (date_fin.getValue().isBefore(date_deb.getValue())) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The end date must be more than the start date!");
        alert.showAndWait();
        return;
    }

    Activiter ac = new Activiter();
    ac.setId_user(session.getId());
    
    Timestamp timestamp = Timestamp.valueOf(date_deb.getValue().atStartOfDay());

    ac.setDate_debut(timestamp);
    timestamp = Timestamp.valueOf(date_fin.getValue().atStartOfDay());
    ac.setDate_fin(timestamp);
    ac.setTitre(titre.getText());
    Categorie id;
    try {
        id = cs.recupererBynom(categ.getValue());
        ac.setRef_categ(id.getId());
    } catch (SQLException ex) {
        Logger.getLogger(ActiviteEditController.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
        as.ajouter(ac);
        btn_ajouter.setDisable(true);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Operation completed successfully!");
        alert.showAndWait();
    } catch (SQLException ex) {
        Logger.getLogger(ActiviteEditController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

     @FXML
    private void returndisplay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/templateActivite.fxml"));
        AddActivite_FrontController aec = loader.getController();
        Parent root = loader.load();
        btnRetour.getScene().setRoot(root);
    
    
}
    
}

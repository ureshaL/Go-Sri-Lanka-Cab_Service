/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.main.StartUp;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class LoginFormController implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnSignIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSignIn.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                Parent parent = FXMLLoader.load(this.getClass()
                        .getResource("/lk/ijse/gosrilanka/view/MainForm.fxml"));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Go Sri Lanka Cab Service");
                stage.show();
                StartUp.primaryStage.hide();
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}

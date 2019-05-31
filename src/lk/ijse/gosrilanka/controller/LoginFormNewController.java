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
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.ConfigBO;
import lk.ijse.gosrilanka.dto.ConfigDTO;
import lk.ijse.gosrilanka.main.StartUp;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.Constans;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class LoginFormNewController implements Initializable {
    
    private ConfigBO configBO;

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnSignIn;

    public LoginFormNewController() {
        configBO = (ConfigBO) BOFactory.getInstance().getBO(BOFactory.BOType.CONFIG);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSignIn.setOnAction((event) -> {
            try {
                ConfigDTO username = configBO.getConfigValue(
                        Constans.ConfigIds.USERNAME);
                ConfigDTO password = configBO.getConfigValue(
                        Constans.ConfigIds.PASSWORD);
                
                String enteredUsername = txtUsername.getText();
                String enteredPassword = encryptPassword(txtPassword.getText());
                
                if (enteredUsername.equals(username.getValue())
                        && enteredPassword.equals(password.getValue())) {
                    loadMainForm();
                } else {
                    AppUtil.showAlert(
                            Alert.AlertType.ERROR,
                            "Username or Password you entered is incorrect!", 
                            "Login Failed!"
                    );
                    txtUsername.requestFocus();
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginFormNewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        txtUsername.setOnAction((event) -> {
            txtPassword.requestFocus();
        });
        
        txtPassword.setOnAction((event) -> {
            btnSignIn.fire();
        });
    }
    
    private String encryptPassword(String password){
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(password.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    private void loadMainForm(){
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
            Logger.getLogger(LoginFormNewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

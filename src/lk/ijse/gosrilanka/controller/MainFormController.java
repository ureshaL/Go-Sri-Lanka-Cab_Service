/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class MainFormController implements Initializable {

    @FXML
    private AnchorPane mainLoadPane;
    @FXML
    private JFXButton btnVehicle;
    @FXML
    private JFXButton btnRent;
    @FXML
    private JFXButton btnDriver;
    @FXML
    private JFXButton btnReport;
    @FXML
    private JFXButton btnHome;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblMainTitle;
    @FXML
    private JFXButton btnCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDateTime();
        btnHome.setOnAction((event) -> {
            loadNewForm("Home","HomeForm.fxml");
        });
        btnHome.fire();
        btnVehicle.setOnAction((event) -> {
            loadNewForm("Vehicle", "VehicleForm.fxml");
        });
        btnDriver.setOnAction((event) -> {
            loadNewForm("Driver", "DriverForm.fxml");
        });
        btnRent.setOnAction((event) -> {
            loadNewForm("Rent", "RentForm.fxml");
        });
        btnCustomer.setOnAction((event) -> {
            loadNewForm("Customer", "CustomerForm.fxml");
        });
        btnReport.setOnAction((event) -> {
            loadNewForm("Reports", "ReportForm.fxml");
        });
    }

    private void loadNewForm(String title ,String fileName) {
        try {
            lblMainTitle.setText(title);
            AnchorPane childForm = FXMLLoader.load(this.getClass()
                    .getResource("/lk/ijse/gosrilanka/view/" + fileName));
            mainLoadPane.getChildren().clear();
            mainLoadPane.getChildren().add(childForm);
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initDateTime() {
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(1), (event) -> {
            lblDate.setText(LocalDate.now().toString());
            lblTime.setText(LocalTime.now().toString().split("[.]")[0]);
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

}

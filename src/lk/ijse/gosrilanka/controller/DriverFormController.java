/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.DriverBO;
import lk.ijse.gosrilanka.dto.DriverDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class DriverFormController implements Initializable {

    @FXML
    private TextField txtDriverSearch;
    @FXML
    private JFXButton btnDriverAdd;
    @FXML
    private JFXButton btnDriverEdit;
    @FXML
    private TableView<DriverDTO> tblDriver;

    private DriverBO driverBO;

    public DriverFormController() {
        driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTables();
        loadDriverTable();

        btnDriverAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("DriverInputForm.fxml", "Driver", this);
        });
        
        btnDriverEdit.setOnAction((event) -> {
            if (!tblDriver.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm("DriverInputForm.fxml", "Driver", this, tblDriver.getSelectionModel().getSelectedItem());
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a Driver from the table!", "Error!");
            }            
        });
        
        txtDriverSearch.setOnKeyReleased((event) -> {
            loadDriverTable();
        });
    }

    public void loadDriverTable() {
        try {
            ArrayList<DriverDTO> search = driverBO.search(txtDriverSearch.getText());
            tblDriver.getItems().clear();
            tblDriver.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(DriverFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTables() {
        //Driver Table
        tblDriver.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("licenceNo"));
        tblDriver.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("driverName"));
        tblDriver.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("driverAddress"));
        tblDriver.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("driverMobile"));
    }

}

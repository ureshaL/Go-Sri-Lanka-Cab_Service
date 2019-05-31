/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Nipun Lakshan
 */
public class AlertMarkInputFormController extends SuperInputForm implements Initializable {

    private HomeFormController homeFormController;
    private CustomDTO customDTO;
    private VehicleBO vehicleBO;
    
    @FXML
    private DatePicker dcNewDate;
    @FXML
    private JFXButton btnUpdateValue;

    public AlertMarkInputFormController() {
        vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnUpdateValue.setOnAction((event) -> {
            if (dcNewDate.getValue() != null) {
                try {
                    boolean updateVehicleAlert = vehicleBO.updateVehicleAlert(
                            customDTO.getVehicleNo(),
                            dcNewDate.getValue().toString(),
                            customDTO.getAlertType()
                    );
                    if (updateVehicleAlert) {
                        AppUtil.showAlert(Alert.AlertType.INFORMATION, 
                                "New Value updated successfully!", "Done!");
                        homeFormController.loadAlertsTbl();
                        ((Stage) btnUpdateValue.getScene().getWindow()).close();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AlertMarkInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, 
                        "Please enter the New Date", 
                        "Error!");
                dcNewDate.requestFocus();
            }
        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.homeFormController = (HomeFormController) parentController;
        this.customDTO = (CustomDTO) dto;
    }
    
}

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.VehicleOwnerBO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;

/**
 * FXML Controller class
 *
 * @author Nipun Lakshan
 */
public class VehicleOwnerDetailsFormController extends SuperInputForm implements Initializable {
    
    private VehicleOwnerBO vehicleOwnerBO;
    
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtMobile;
    @FXML
    private JFXButton btnDone;

    public VehicleOwnerDetailsFormController() {
        vehicleOwnerBO = (VehicleOwnerBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE_OWNER);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnDone.setOnAction((event) -> {
            ((Stage) btnDone.getScene().getWindow()).close();
        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        try {
            String ownnerNic = (String) dto;
            VehicleOwnerDTO vehicleOwner = vehicleOwnerBO.getVehicleOwner(ownnerNic);
            txtNic.setText(vehicleOwner.getOwnerNic());
            txtName.setText(vehicleOwner.getName());
            txtAddress.setText(vehicleOwner.getAddress());
            txtMobile.setText(vehicleOwner.getMobile()+"");
        } catch (Exception ex) {
            Logger.getLogger(VehicleOwnerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

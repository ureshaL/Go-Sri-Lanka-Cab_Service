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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.DriverBO;
import lk.ijse.gosrilanka.dto.DriverDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class DriverInputFormController extends SuperInputForm implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtLicenceNo;
    @FXML
    private TextField txtDriverName;
    @FXML
    private TextField txtDriverAddress;
    @FXML
    private TextField txtMobile;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    private DriverDTO driverDTO;
    private DriverFormController driverFormController;
    private DriverBO driverBO;

    public DriverInputFormController() {
        driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnSave.setOnAction((event) -> {
            if (formType == InputFormType.ADD) {
                try {
                    if (validateForm()) {
                        boolean addDriver = driverBO.addDriver(new DriverDTO(
                                txtLicenceNo.getText(),
                                txtDriverName.getText(), 
                                txtDriverAddress.getText(),
                                Integer.parseInt(txtMobile.getText())
                            )
                        );
                        if (addDriver) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Driver added successfully!", "Done!");
                            driverFormController.loadDriverTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DriverInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == InputFormType.EDIT){
                try {
                    if (validateForm()) {
                        boolean updateDriver = driverBO.updateDriver(
                                new DriverDTO(
                                        txtLicenceNo.getText(),
                                        txtDriverName.getText(),
                                        txtDriverAddress.getText(),
                                        Integer.parseInt(txtMobile.getText())
                                )
                        );
                        if (updateDriver) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Driver updated successfully!", "Done!");
                            driverFormController.loadDriverTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DriverInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnDelete.setOnAction((event) -> {
            try {
                boolean deleteDriver = driverBO.deleteDriver(txtLicenceNo.getText());
                if (deleteDriver) {
                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Driver deleted successfully!", "Done!");
                    driverFormController.loadDriverTable();
                    ((Stage) btnSave.getScene().getWindow()).close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DriverInputFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.driverDTO = (DriverDTO) dto;
        lblTitle.setText(formTitle);
        this.driverFormController = (DriverFormController) parentController;
        if (formType == InputFormType.ADD) {
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT) {
            txtLicenceNo.setText(driverDTO.getLicenceNo());
            txtDriverName.setText(driverDTO.getDriverName());
            txtDriverAddress.setText(driverDTO.getDriverAddress());
            txtMobile.setText(driverDTO.getDriverMobile() + "");
        }
    }
    
    private boolean validateForm(){
        if (!FormValidator.isNotEmpty(txtLicenceNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Licence No cannot be empty!", "Error!");
            txtLicenceNo.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtDriverName.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Name cannot be empty!", "Error!");
            txtLicenceNo.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtDriverAddress.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Driver Address cannot be empty!", "Error!");
            txtDriverAddress.requestFocus();
            return false;
        }else if (!FormValidator.isValidLicenceNo(txtLicenceNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Licence No!", "Error!");
            txtLicenceNo.requestFocus();
            return false;
        } else if (!FormValidator.isValidMobile(txtMobile.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Mobile!", "Error!");
            txtMobile.requestFocus();
            return false;
        }
        return true;
    }

}

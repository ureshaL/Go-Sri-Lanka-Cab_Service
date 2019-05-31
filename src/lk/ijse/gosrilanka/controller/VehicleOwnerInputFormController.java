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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.VehicleOwnerBO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class VehicleOwnerInputFormController extends SuperInputForm implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtMobile;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TextField txtOwnerNIC;
    @FXML
    private TextField txtOwnerName;
    @FXML
    private TextArea txtOwnerAddress;

    private VehicleFormController vehicleFormController;
    private VehicleOwnerBO vehicleOwnerBO;
    private VehicleOwnerDTO vehicleOwnerDTO;

    public VehicleOwnerInputFormController() {
        vehicleOwnerBO = (VehicleOwnerBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE_OWNER);
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
                        boolean addVehicleOwner = vehicleOwnerBO.addVehicleOwner(new VehicleOwnerDTO(
                                txtOwnerNIC.getText(),
                                txtOwnerName.getText(),
                                txtOwnerAddress.getText(),
                                Integer.parseInt(txtMobile.getText())
                        )
                        );
                        if (addVehicleOwner) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Owner added successfully!", "Done!");
                            vehicleFormController.loadVehicleOwnerTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == InputFormType.EDIT) {
                try {
                    if (validateForm()) {
                        boolean updateVehicleOwner = vehicleOwnerBO.updateVehicleOwner(
                                new VehicleOwnerDTO(
                                        txtOwnerNIC.getText(),
                                        txtOwnerName.getText(),
                                        txtOwnerAddress.getText(),
                                        Integer.parseInt(txtMobile.getText())
                                )
                        );
                        if (updateVehicleOwner) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Owner updated successfully!", "Done!");
                            vehicleFormController.loadVehicleOwnerTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnDelete.setOnAction((event) -> {
            try {
                boolean deleteVehicleOwner = vehicleOwnerBO.deleteVehicleOwner(txtOwnerNIC.getText());
                if (deleteVehicleOwner) {
                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Owner deleted successfully!", "Done!");
                    vehicleFormController.loadVehicleOwnerTable();
                    ((Stage) btnSave.getScene().getWindow()).close();
                }
            } catch (Exception ex) {
                Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.vehicleOwnerDTO = (VehicleOwnerDTO) dto;
        lblTitle.setText(formTitle);
        this.vehicleFormController = (VehicleFormController) parentController;
        if (formType == InputFormType.ADD) {
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT) {
            txtOwnerNIC.setText(vehicleOwnerDTO.getOwnerNic());
            txtOwnerName.setText(vehicleOwnerDTO.getName());
            txtOwnerAddress.setText(vehicleOwnerDTO.getAddress());
            txtMobile.setText(vehicleOwnerDTO.getMobile() + "");
        }

    }

    private boolean validateForm() {
        if (!FormValidator.isNotEmpty(txtOwnerNIC.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Owner NIC cannot be empty!", "Error!");
            txtOwnerNIC.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtOwnerName.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Owner Name cannot be empty!", "Error!");
            txtOwnerName.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtOwnerAddress.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Owner Address cannot be empty!", "Error!");
            txtOwnerAddress.requestFocus();
            return false;
        } else if (!FormValidator.isValidNic(txtOwnerNIC.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Owner NIC!", "Error!");
            txtOwnerNIC.requestFocus();
            return false;
        } else if (!FormValidator.isValidMobile(txtMobile.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Mobile!", "Error!");
            txtMobile.requestFocus();
            return false;
        }
        return true;
    }

}

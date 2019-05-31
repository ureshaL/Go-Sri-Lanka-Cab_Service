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
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class MakeInputFormController extends SuperInputForm implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtMakeId;
    @FXML
    private TextField txtMakeName;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    
    private MakeDTO makeDTO;
    private VehicleFormController vehicleFormController;
    private MakeBO makeBO;

    public MakeInputFormController() {
        makeBO = (MakeBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAKE);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnSave.setOnAction((event) -> {
            if (formType == InputFormType.ADD) {
                try {
                    if (FormValidator.isNotEmpty(txtMakeName.getText())) {
                        boolean addMake = makeBO.addMake(new MakeDTO(txtMakeName.getText()));
                        if (addMake) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Make added successfully!", "Done!");
                            vehicleFormController.loadMakeTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    } else {
                        AppUtil.showAlert(Alert.AlertType.ERROR, "Please enter a valid name!", "Error!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == InputFormType.EDIT){
                try {
                    boolean updateMake = makeBO.updateMake(new MakeDTO(Integer.parseInt(txtMakeId.getText()), txtMakeName.getText()));
                    if (updateMake) {
                        AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Make updated successfully!", "Done!");
                        vehicleFormController.loadMakeTable();
                        ((Stage) btnSave.getScene().getWindow()).close();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnDelete.setOnAction((event) -> {
            try {
                boolean deleteMake = makeBO.deleteMake(Integer.parseInt(txtMakeId.getText()));
                if (deleteMake) {
                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Make deleted successfully!", "Done!");
                    vehicleFormController.loadMakeTable();
                    ((Stage) btnSave.getScene().getWindow()).close();
                }
            } catch (Exception ex) {
                Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.makeDTO = (MakeDTO) dto;
        lblTitle.setText(formTitle);
        this.vehicleFormController = (VehicleFormController) parentController;
        if (formType == InputFormType.ADD) {
            loadNextId();
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT){
            txtMakeId.setText(makeDTO.getMakeId()+"");
            txtMakeName.setText(makeDTO.getMakeName());
        }
    }
    
    private void loadNextId(){
        try {
            txtMakeId.setText(makeBO.getNextId()+"");
        } catch (Exception ex) {
            Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

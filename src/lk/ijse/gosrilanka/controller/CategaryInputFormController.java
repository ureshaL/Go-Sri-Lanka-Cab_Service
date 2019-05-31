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
import lk.ijse.gosrilanka.bo.custom.CategaryBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class CategaryInputFormController extends SuperInputForm implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtCategoryId;
    @FXML
    private TextField txtCategoryName;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    private CategoryDTO categoryDTO;
    private VehicleFormController vehicleFormController;
    private CategaryBO categaryBO;

    public CategaryInputFormController() {
        categaryBO = (CategaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGARY);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.setOnAction((event) -> {
            if (formType == SuperInputForm.InputFormType.ADD) {
                try {
                    if (FormValidator.isNotEmpty(txtCategoryName.getText())) {
                        boolean addCategary = categaryBO.addCategary(new CategoryDTO(txtCategoryName.getText()));
                        if (addCategary) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Categary added successfully!", "Done!");
                            vehicleFormController.loadCategaryTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    } else {
                        AppUtil.showAlert(Alert.AlertType.ERROR, "Please enter a valid name!", "Error!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CategaryInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == SuperInputForm.InputFormType.EDIT) {
                try {
                    boolean updateCategary = categaryBO.updateCategary(new CategoryDTO(Integer.parseInt(txtCategoryId.getText()), txtCategoryName.getText()));
                    if (updateCategary) {
                        AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Categary updated successfully!", "Done!");
                        vehicleFormController.loadCategaryTable();
                        ((Stage) btnSave.getScene().getWindow()).close();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CategaryInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnDelete.setOnAction((event) -> {
            try {
                boolean deleteCategary = categaryBO.deleteCategary(Integer.parseInt(txtCategoryId.getText()));
                if (deleteCategary) {
                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Categary deleted successfully!", "Done!");
                    vehicleFormController.loadCategaryTable();
                    ((Stage) btnSave.getScene().getWindow()).close();
                }
            } catch (Exception ex) {
                Logger.getLogger(CategaryInputFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void setValues(SuperInputForm.InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.categoryDTO = (CategoryDTO) dto;
        lblTitle.setText(formTitle);
        this.vehicleFormController = (VehicleFormController) parentController;
        if (formType == SuperInputForm.InputFormType.ADD) {
            loadNextId();
            btnDelete.setVisible(false);
        } else if (formType == SuperInputForm.InputFormType.EDIT) {
            txtCategoryId.setText(categoryDTO.getCategoryId() + "");
            txtCategoryName.setText(categoryDTO.getCategoryName());
        }
    }

    private void loadNextId() {
        try {
            txtCategoryId.setText(categaryBO.getNextId() + "");
        } catch (Exception ex) {
            Logger.getLogger(CategaryInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


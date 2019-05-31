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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.CategaryBO;
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.bo.custom.ModelBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class ModelInputFormController extends SuperInputForm implements Initializable {

    private CategaryBO categaryBO;
    private MakeBO makeBO;
    private ModelBO modelBO;
    
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtModelID;
    @FXML
    private TextField txtModelName;
    @FXML
    private ComboBox<CategoryDTO> cmbCategory;
    @FXML
    private ComboBox<MakeDTO> cmbMake;
    @FXML
    private TextField txtNoOfSeats;
    @FXML
    private TextField txtDepositAmount;
    @FXML
    private TextField txtPricePerKM;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    private ModelDTO modelDTO;
    private VehicleFormController vehicleFormController;

    public ModelInputFormController() {
        categaryBO = (CategaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGARY);
        makeBO = (MakeBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAKE);
        modelBO = (ModelBO) BOFactory.getInstance().getBO(BOFactory.BOType.MODEL);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCategoryCmb();
        loadMakeCmb();
        
        btnSave.setOnAction((event) -> {
            if (formType == InputFormType.ADD) {
                try {
                    if (validateForm()) {
                        boolean addModel = modelBO.addModel(
                                new ModelDTO(
                                        txtModelName.getText(),
                                        Integer.parseInt(txtNoOfSeats.getText()),
                                        Double.parseDouble(txtPricePerKM.getText()),
                                        Double.parseDouble(txtDepositAmount.getText()),
                                        cmbMake.getSelectionModel().getSelectedItem().getMakeId(),
                                        cmbCategory.getSelectionModel().getSelectedItem().getCategoryId()
                                )
                        );
                        if (addModel) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Make added successfully!", "Done!");
                            vehicleFormController.loadModelTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == InputFormType.EDIT){
                try {
                    if (validateForm()) {
                        boolean updateModel = modelBO.updateModel(
                                new ModelDTO(
                                        Integer.parseInt(txtModelID.getText()),
                                        txtModelName.getText(),
                                        Integer.parseInt(txtNoOfSeats.getText()),
                                        Double.parseDouble(txtPricePerKM.getText()),
                                        Double.parseDouble(txtDepositAmount.getText()),
                                        cmbMake.getSelectionModel().getSelectedItem().getMakeId(),
                                        cmbCategory.getSelectionModel().getSelectedItem().getCategoryId()
                                )
                        );
                        if (updateModel) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Model updated successfully!", "Done!");
                            vehicleFormController.loadModelTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnDelete.setOnAction((event) -> {
            try {
                boolean deleteModel = modelBO.deleteModel(Integer.parseInt(txtModelID.getText()));
                if (deleteModel) {
                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle Model deleted successfully!", "Done!");
                    vehicleFormController.loadModelTable();
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
        this.modelDTO = (ModelDTO) dto;
        lblTitle.setText(formTitle);
        this.vehicleFormController = (VehicleFormController) parentController;
        if (formType == InputFormType.ADD) {
            loadNextId();
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT){
            txtModelID.setText(modelDTO.getModelId()+"");
            txtModelName.setText(modelDTO.getModelName());
            txtNoOfSeats.setText(modelDTO.getNoOfseats()+"");
            txtDepositAmount.setText(AppUtil.formatDouble(modelDTO.getDepositAmount()));
            txtPricePerKM.setText(AppUtil.formatDouble(modelDTO.getPricePerKm()));
            for (int i = 0; i < cmbCategory.getItems().size(); i++) {
                if (cmbCategory.getItems().get(i).getCategoryId() == modelDTO.getCategoryId()) {
                    cmbCategory.getSelectionModel().select(i);
                    break;
                }
            }
            for (int i = 0; i < cmbMake.getItems().size(); i++) {
                if (cmbMake.getItems().get(i).getMakeId()== modelDTO.getCategoryId()) {
                    cmbMake.getSelectionModel().select(i);
                    break;
                }
            }
        }
    }

    private void loadCategoryCmb() {
        try {
            ArrayList<CategoryDTO> search = categaryBO.search("");
            cmbCategory.getItems().clear();
            cmbCategory.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(ModelInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMakeCmb() {
        try {
            ArrayList<MakeDTO> search = makeBO.search("");
            cmbMake.getItems().clear();
            cmbMake.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(ModelInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadNextId(){
        try {
            txtModelID.setText(modelBO.getNextId()+"");
        } catch (Exception ex) {
            Logger.getLogger(MakeInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validateForm(){
        if (!FormValidator.isNotEmpty(txtModelName.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Model Name cannot be empty!", "Error!");
            txtModelName.requestFocus();
            return false;
        } else if (cmbCategory.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Category cannot be empty!", "Error!");
            cmbCategory.requestFocus();
            return false;
        } else if (cmbMake.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Make cannot be empty!", "Error!");
            cmbMake.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtNoOfSeats.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "No of Seats cannot be empty!", "Error!");
            txtNoOfSeats.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtDepositAmount.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Deposit Amount cannot be empty!", "Error!");
            txtDepositAmount.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtPricePerKM.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Price Per KM cannot be empty!", "Error!");
            txtPricePerKM.requestFocus();
            return false;
        } else {
            if (!FormValidator.isNumber(txtNoOfSeats.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field No of Seats!", "Error!");
                txtNoOfSeats.requestFocus();
                return false;
            } else if (!FormValidator.isDecimal(txtDepositAmount.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Deposit Amount!", "Error!");
                txtDepositAmount.requestFocus();
                return false;
            } else if (!FormValidator.isDecimal(txtPricePerKM.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Price Per KM!", "Error!");
                txtPricePerKM.requestFocus();
                return false;
            }
        }
        return true;
    }
}

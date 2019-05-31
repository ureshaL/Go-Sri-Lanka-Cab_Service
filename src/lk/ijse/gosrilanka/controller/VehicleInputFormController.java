/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.CategaryBO;
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.bo.custom.ModelBO;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.bo.custom.VehicleOwnerBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;
import lk.ijse.gosrilanka.dto.VehicleDTO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.Constans;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class VehicleInputFormController extends SuperInputForm implements Initializable {
    
    private CategaryBO categaryBO;
    private MakeBO makeBO;
    private ModelBO modelBO;
    private VehicleDTO vehicleDTO;
    private VehicleFormController vehicleFormController;
    private VehicleOwnerBO vehicleOwnerBO;
    private VehicleBO vehicleBO;

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtVehicleNo;
    @FXML
    private ComboBox<String> cmbOwnershipType;
    @FXML
    private DatePicker dcInsuranceExpDate;
    @FXML
    private DatePicker dcLicenceExpDate;
    @FXML
    private ComboBox<CategoryDTO> cmbCategory;
    @FXML
    private ComboBox<MakeDTO> cmbMake;
    @FXML
    private ComboBox<ModelDTO> cmbModel;
    @FXML
    private TextField txtOwnerNic;
    @FXML
    private JFXButton btnOwnerCheck;
    @FXML
    private TextField txtOwnerName;
    @FXML
    private TextArea txtOwnerAddress;
    @FXML
    private TextField txtOwnerMobile;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    public VehicleInputFormController() {
        categaryBO = (CategaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGARY);
        makeBO = (MakeBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAKE);
        modelBO = (ModelBO) BOFactory.getInstance().getBO(BOFactory.BOType.MODEL);
        vehicleOwnerBO = (VehicleOwnerBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE_OWNER);
        vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadOwnershipTypeCmb();
        loadCategoryCmb();
        
        cmbOwnershipType.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            switch(cmbOwnershipType.getSelectionModel().getSelectedItem()){
                case "Own":
                    AppUtil.toggleStatusOfUiElements(
                            true, 
                            txtOwnerNic,
                            btnOwnerCheck,
                            txtOwnerName,
                            txtOwnerAddress,
                            txtOwnerMobile
                    );
                    AppUtil.clearUiElements(
                            txtOwnerNic,
                            txtOwnerName,
                            txtOwnerAddress,
                            txtOwnerMobile
                    );
                    break;
                    case "Recruited":
                    case "Immediate":
                    AppUtil.toggleStatusOfUiElements(
                            false, 
                            txtOwnerNic,
                            btnOwnerCheck
                    );
                    break;
            }
        });
        
        cmbCategory.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            loadMakeCmb();
        });
        
        cmbMake.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            loadModelCmb();
        });
        
        btnOwnerCheck.setOnAction((event) -> {
            if (FormValidator.isValidNic(txtOwnerNic.getText())) {
                try {
                    VehicleOwnerDTO vehicleOwner = vehicleOwnerBO.getVehicleOwner(txtOwnerNic.getText());
                    if (vehicleOwner != null) {
                        txtOwnerName.setText(vehicleOwner.getName());
                        txtOwnerAddress.setText(vehicleOwner.getAddress());
                        txtOwnerMobile.setText(vehicleOwner.getMobile()+"");
                    } else {
                        AppUtil.toggleStatusOfUiElements(
                                false, 
                                txtOwnerName, txtOwnerAddress, txtOwnerMobile
                        );
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please enter a valid NIC!", "Error!");
            }
        });
        
        txtOwnerNic.setOnAction((event) -> {
            btnOwnerCheck.fire();
        });
        
        btnSave.setOnAction((event) -> {
            if (formType == InputFormType.ADD) {
                try {
                    if (validateForm()) {
                        boolean addVehicle = false;
                        if (cmbOwnershipType.getSelectionModel().getSelectedItem().equals("Own")) {
                            addVehicle = vehicleBO.addOwnVehicle(
                                    new VehicleDTO(
                                            txtVehicleNo.getText(),
                                            cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                            AppUtil.getCurrentTimestamp(),
                                            dcInsuranceExpDate.getValue().toString(),
                                            dcLicenceExpDate.getValue().toString(),
                                            cmbModel.getSelectionModel().getSelectedItem().getModelId()
                                    )
                            );
                        } else {
                            if (txtOwnerName.isDisable()) {
                                addVehicle = vehicleBO.addOtherVehicle(
                                        new VehicleDTO(
                                                txtVehicleNo.getText(),
                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                                AppUtil.getCurrentTimestamp(),
                                                dcInsuranceExpDate.getValue().toString(),
                                                dcLicenceExpDate.getValue().toString(),
                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
                                                txtOwnerNic.getText()
                                        ),
                                        null
                                );
                            } else {
                                addVehicle = vehicleBO.addOtherVehicle(
                                        new VehicleDTO(
                                                txtVehicleNo.getText(),
                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                                AppUtil.getCurrentTimestamp(),
                                                dcInsuranceExpDate.getValue().toString(),
                                                dcLicenceExpDate.getValue().toString(),
                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
                                                txtOwnerNic.getText()
                                        ),
                                        new VehicleOwnerDTO(
                                                txtOwnerNic.getText(),
                                                txtOwnerName.getText(),
                                                txtOwnerAddress.getText(),
                                                txtOwnerMobile.getText().isEmpty() ? 0 : Integer.parseInt(txtOwnerMobile.getText())
                                        )
                                );
                            }
                        }
                        if (addVehicle) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle added successfully!", "Done!");
                            vehicleFormController.loadVehicleTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    AppUtil.showAlert(Alert.AlertType.ERROR, "Vehicle alreay exists!", "Error!");
                    ex.printStackTrace();
                }
            } else if (formType == InputFormType.EDIT){
                try {
                    if (validateForm()) {
                        boolean updateVehicle = false;
                        if (cmbOwnershipType.getSelectionModel().getSelectedItem().equals("Own")) {
                            updateVehicle = vehicleBO.updateOwnVehicle(
                                    new VehicleDTO(
                                            txtVehicleNo.getText(),
                                            cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                            dcInsuranceExpDate.getValue().toString(),
                                            dcLicenceExpDate.getValue().toString(),
                                            cmbModel.getSelectionModel().getSelectedItem().getModelId()
                                    )
                            );
                        } else {
                            if (txtOwnerName.isDisable()) {
                                updateVehicle = vehicleBO.updateOtherVehicle(
                                        new VehicleDTO(
                                                txtVehicleNo.getText(),
                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                                dcInsuranceExpDate.getValue().toString(),
                                                dcLicenceExpDate.getValue().toString(),
                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
                                                txtOwnerNic.getText()
                                        ),
                                        null
                                );
                            } else {
                                updateVehicle = vehicleBO.updateOtherVehicle(
                                        new VehicleDTO(
                                                txtVehicleNo.getText(),
                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
                                                dcInsuranceExpDate.getValue().toString(),
                                                dcLicenceExpDate.getValue().toString(),
                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
                                                txtOwnerNic.getText()
                                        ),
                                        new VehicleOwnerDTO(
                                                txtOwnerNic.getText(),
                                                txtOwnerName.getText(),
                                                txtOwnerAddress.getText(),
                                                txtOwnerMobile.getText().isEmpty() ? 0 : Integer.parseInt(txtOwnerMobile.getText())
                                        )
                                );
                            }
                        }
                        if (updateVehicle) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle updated successfully!", "Done!");
                            vehicleFormController.loadVehicleTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    AppUtil.showAlert(Alert.AlertType.ERROR, "Vehicle update failed!", "Error!");
                    ex.printStackTrace();
                }
            }
        });
        
        txtOwnerNic.textProperty().addListener((event) -> {
            if (!txtVehicleNo.isDisable()) {
                AppUtil.toggleStatusOfUiElements(
                        true,
                        txtOwnerName,
                        txtOwnerAddress,
                        txtOwnerMobile
                );
                AppUtil.clearUiElements(
                        txtOwnerName,
                        txtOwnerAddress,
                        txtOwnerMobile
                );
            }
        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.vehicleDTO = (VehicleDTO) dto;
        lblTitle.setText(formTitle);
        this.vehicleFormController = (VehicleFormController) parentController;
        if (formType == InputFormType.ADD) {
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT){
            txtVehicleNo.setText(vehicleDTO.getVehicleNo());
            for (int i = 0; i < cmbOwnershipType.getItems().size(); i++) {
                if (cmbOwnershipType.getItems().get(i).equals(vehicleDTO.getOwnershipType())) {
                    cmbOwnershipType.getSelectionModel().select(i);
                    break;
                }
            }
            dcInsuranceExpDate.setValue(LocalDate.parse(vehicleDTO.getInsuarenceExpireDate()));
            dcLicenceExpDate.setValue(LocalDate.parse(vehicleDTO.getLicenceExpireDate()));
            try {
                CategoryDTO categoryByModelId = categaryBO.getCategoryByModelId(vehicleDTO.getModelId());
                for (int i = 0; i < cmbCategory.getItems().size(); i++) {
                    if (cmbCategory.getItems().get(i).getCategoryId() == categoryByModelId.getCategoryId()) {
                        cmbCategory.getSelectionModel().select(i);
                        break;
                    }
                }
                loadMakeCmb();
                MakeDTO makeByModelId = makeBO.getMakeByModelId(vehicleDTO.getModelId());
                for (int i = 0; i < cmbMake.getItems().size(); i++) {
                    if (cmbMake.getItems().get(i).getMakeId()== makeByModelId.getMakeId()) {
                        cmbMake.getSelectionModel().select(i);
                        break;
                    }
                }
                loadModelCmb();
                for (int i = 0; i < cmbModel.getItems().size(); i++) {
                    if (cmbModel.getItems().get(i).getModelId()== vehicleDTO.getModelId()) {
                        cmbModel.getSelectionModel().select(i);
                        break;
                    }
                }
                if (!vehicleDTO.getOwnerNic().equals("---------")) {
                    VehicleOwnerDTO vehicleOwner = vehicleOwnerBO.getVehicleOwner(vehicleDTO.getOwnerNic());
                    txtOwnerNic.setText(vehicleOwner.getOwnerNic());
                    txtOwnerName.setText(vehicleOwner.getName());
                    txtOwnerAddress.setText(vehicleOwner.getAddress());
                    txtOwnerMobile.setText(vehicleOwner.getMobile()+"");
                }
            } catch (Exception ex) {
                Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadOwnershipTypeCmb() {
        cmbOwnershipType.getItems().addAll(Constans.OWNERSHIP_TYPES);
    }

    private void loadCategoryCmb() {
        try {
            ArrayList<CategoryDTO> search = categaryBO.search("");
            cmbCategory.getItems().clear();
            cmbCategory.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMakeCmb() {
        try {
            if (!cmbCategory.getSelectionModel().isEmpty()) {
                ArrayList<MakeDTO> makesByCategory = makeBO.getMakesByCategory(cmbCategory.getSelectionModel().getSelectedItem().getCategoryId());
                cmbMake.getItems().clear();
                cmbMake.getItems().addAll(makesByCategory);
                cmbMake.setDisable(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadModelCmb() {
        try {
            if (!cmbMake.getSelectionModel().isEmpty()) {
                ArrayList<ModelDTO> modelsByCategoryAndMake = modelBO.getModelsByCategoryAndMake(
                        cmbCategory.getSelectionModel().getSelectedItem().getCategoryId(),
                        cmbMake.getSelectionModel().getSelectedItem().getMakeId()
                );
                cmbModel.getItems().clear();
                cmbModel.getItems().addAll(modelsByCategoryAndMake);
                cmbModel.setDisable(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validateForm(){
        if (!FormValidator.isNotEmpty(txtVehicleNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Vehicle No cannot be empty!", "Error!");
            txtVehicleNo.requestFocus();
            return false;
        } else if (cmbOwnershipType.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Ownership Type cannot be empty!", "Error!");
            cmbOwnershipType.requestFocus();
            return false;
        } else if (dcInsuranceExpDate.getValue() == null) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Insurence Expire Date cannot be empty!", "Error!");
            dcInsuranceExpDate.requestFocus();
            return false;
        } else if (dcLicenceExpDate.getValue() == null) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Licence Expire Date cannot be empty!", "Error!");
            dcLicenceExpDate.requestFocus();
            return false;
        } else if (cmbCategory.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Category cannot be empty!", "Error!");
            cmbCategory.requestFocus();
            return false;
        } else if (cmbMake.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Make cannot be empty!", "Error!");
            cmbMake.requestFocus();
            return false;
        } else if (cmbModel.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Model cannot be empty!", "Error!");
            cmbModel.requestFocus();
            return false;
        } else if (!FormValidator.isValidVehicleNo(txtVehicleNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Vehicle No!", "Error!");
            txtVehicleNo.requestFocus();
            return false;
        } else if (cmbOwnershipType.getSelectionModel().getSelectedItem().equals("Recruited") || cmbOwnershipType.getSelectionModel().getSelectedItem().equals("Immediate")) {
            if (!FormValidator.isNotEmpty(txtOwnerNic.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Owner NIC cannot be empty!", "Error!");
                txtOwnerNic.requestFocus();
                return false;
            } else if (!FormValidator.isNotEmpty(txtOwnerName.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Owner Name cannot be empty!", "Error!");
                txtOwnerName.requestFocus();
                return false;
            } else if (!FormValidator.isNotEmpty(txtOwnerAddress.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Owner Address cannot be empty!", "Error!");
                txtOwnerAddress.requestFocus();
                return false;
            } else if (!FormValidator.isNotEmpty(txtOwnerMobile.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Owner Mobile cannot be empty!", "Error!");
                txtOwnerMobile.requestFocus();
                return false;
            } else if (!FormValidator.isValidNic(txtOwnerNic.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Owner NIC!", "Error!");
                txtOwnerNic.requestFocus();
                return false;
            } else if (!FormValidator.isValidMobile(txtOwnerMobile.getText())) {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Owner Mobile!", "Error!");
                txtOwnerMobile.requestFocus();
                return false;
            }
        }
        return true;
    }
    
}

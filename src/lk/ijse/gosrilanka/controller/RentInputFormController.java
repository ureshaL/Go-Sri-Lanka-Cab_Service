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
import lk.ijse.gosrilanka.bo.custom.ConfigBO;
import lk.ijse.gosrilanka.bo.custom.CusOrderBO;
import lk.ijse.gosrilanka.bo.custom.CustomerBO;
import lk.ijse.gosrilanka.bo.custom.DriverBO;
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.bo.custom.ModelBO;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.dto.ConfigDTO;
import lk.ijse.gosrilanka.dto.CusOrderDTO;
import lk.ijse.gosrilanka.dto.CustomerDTO;
import lk.ijse.gosrilanka.dto.DriverDTO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;
import lk.ijse.gosrilanka.dto.VehicleDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.Constans;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class RentInputFormController extends SuperInputForm implements Initializable {

    private CusOrderDTO cusOrderDTO;
    private RentFormController rentFormController;
    private CusOrderBO cusOrderBO;
    private DriverBO driverBO;
    private CategaryBO categaryBO;
    private MakeBO makeBO;
    private ModelBO modelBO;
    private VehicleBO vehicleBO;
    private CustomerBO customerBO;
    private ConfigBO configBO;
    
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtOrderId;
    @FXML
    private ComboBox<DriverDTO> cmbDriver;
    @FXML
    private DatePicker dcJourneyDate;
    @FXML
    private ComboBox<CategoryDTO> cmbCategory;
    @FXML
    private ComboBox<MakeDTO> cmbMake;
    @FXML
    private ComboBox<ModelDTO> cmbModel;
    @FXML
    private ComboBox<VehicleDTO> cmbVehicle;
    @FXML
    private TextField txtCustomerNic;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextArea txtCustomerAddress;
    @FXML
    private TextField txtCustomerMobile;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TextField txtPricePerKm;
    @FXML
    private TextField txtMinAmount;
    @FXML
    private TextField txtDepositAmount;
    @FXML
    private TextField txtDriverPricePerDay;
    @FXML
    private TextField txtNoOfDays;
    @FXML
    private JFXButton btnCustomerCheck;
    @FXML
    private TextField txtCustomerLicenceNo;
    @FXML
    private TextField txtKmPerDay;
    @FXML
    private TextField txtCustomerSearch;

    public RentInputFormController() {
        cusOrderBO = (CusOrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUS_ORDER);
        driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
        categaryBO = (CategaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGARY);
        makeBO = (MakeBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAKE);
        modelBO = (ModelBO) BOFactory.getInstance().getBO(BOFactory.BOType.MODEL);
        vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
        configBO = (ConfigBO) BOFactory.getInstance().getBO(BOFactory.BOType.CONFIG);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDriverCmb();
        loadCategoryCmb();
        loadKmPerDay();
        AppUtil.setDateChooserMinDate(dcJourneyDate, LocalDate.now());
        
        cmbDriver.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            DriverDTO selectedItem = cmbDriver.getSelectionModel().getSelectedItem();
            if (selectedItem.getLicenceNo() != null) {
                try {
                    ConfigDTO configDTO = configBO.getConfigValue(Constans.ConfigIds.DRIVER_PRICE_PER_DAY);
                    txtDriverPricePerDay.setText(configDTO.getValue());
                } catch (Exception ex) {
                    Logger.getLogger(RentInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                txtDriverPricePerDay.setText("");
            }
            calcMinAmount();
        });
        
        cmbCategory.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            loadMakeCmb();
        });
        
        cmbMake.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            loadModelCmb();
        });
        
        cmbModel.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            loadVehicleCmb();
            if (!cmbModel.getSelectionModel().isEmpty()) {
                txtPricePerKm.setText(
                        AppUtil.formatDouble(cmbModel.getSelectionModel()
                                .getSelectedItem().getPricePerKm()));
                txtDepositAmount.setText(
                        AppUtil.formatDouble(cmbModel.getSelectionModel()
                                .getSelectedItem().getDepositAmount()));
                calcMinAmount();
            } else {
                txtPricePerKm.setText("");
                txtDepositAmount.setText("");
            }
        });
        
        btnCustomerCheck.setOnAction((event) -> {
            if (FormValidator.isValidNic(txtCustomerNic.getText())) {
                try {
                    CustomerDTO customer = customerBO.getCustomer(txtCustomerNic.getText());
                    if (customer != null) {
                        if (!customer.getLicenceNo().isEmpty()) {
                            txtCustomerLicenceNo.setText(customer.getLicenceNo());
                        } else {
                            txtCustomerLicenceNo.setDisable(false);
                        }
                        txtCustomerName.setText(customer.getName());
                        txtCustomerAddress.setText(customer.getAddress());
                        txtCustomerMobile.setText(customer.getMobile()+"");
                    } else {
                        AppUtil.toggleStatusOfUiElements(
                                false, 
                                txtCustomerLicenceNo, 
                                txtCustomerName, 
                                txtCustomerAddress, 
                                txtCustomerMobile
                        );
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please enter a valid NIC!", "Error!");
            }
        });
        
        txtNoOfDays.setOnKeyTyped((event) -> {
            char key = event.getCharacter().charAt(0);
            if (!Character.isDigit(key)) {
                event.consume();
            }
        });
        
        txtNoOfDays.setOnKeyReleased((event) -> {
            calcMinAmount();
        });
        
        txtCustomerNic.setOnAction((event) -> {
            btnCustomerCheck.fire();
        });
        
        txtCustomerNic.textProperty().addListener((event) -> {
            AppUtil.toggleStatusOfUiElements(
                    true,
                    txtCustomerLicenceNo,
                    txtCustomerName,
                    txtCustomerAddress,
                    txtCustomerMobile
            );
            AppUtil.clearUiElements(
                    txtCustomerLicenceNo,
                    txtCustomerName,
                    txtCustomerAddress,
                    txtCustomerMobile
            );
        });
        
        btnSave.setOnAction((event) -> {
            if (formType == InputFormType.ADD) {
                try {
                    if (validateForm()) {
                        boolean isCustomerNew = !txtCustomerName.isDisable();
                        boolean placeRentOrder = cusOrderBO.placeRentOrder(
                                new CusOrderDTO(
                                        AppUtil.getCurrentTimestamp(),
                                        dcJourneyDate.getValue().toString(),
                                        Integer.parseInt(txtNoOfDays.getText()),
                                        Double.parseDouble(txtPricePerKm.getText()), 
                                        txtDriverPricePerDay.getText().isEmpty() ? 0 : Double.parseDouble(txtDriverPricePerDay.getText()),
                                        txtCustomerNic.getText(),
                                        cmbVehicle.getSelectionModel().getSelectedItem().getVehicleNo(), 
                                        cmbDriver.getSelectionModel().getSelectedItem().getLicenceNo(),
                                        Integer.parseInt(txtKmPerDay.getText())
                                ), 
                                new CustomerDTO(
                                        txtCustomerNic.getText(),
                                        txtCustomerLicenceNo.getText(),
                                        txtCustomerName.getText(), 
                                        txtCustomerAddress.getText(),
                                        Integer.parseInt(txtCustomerMobile.getText())
                                ),
                                isCustomerNew
                        );
                        if (placeRentOrder) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Rent Order added successfully!", "Done!");
                            rentFormController.loadRentOrderTbl();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        } else {
                            AppUtil.showAlert(Alert.AlertType.ERROR, "Rent Order saving failed!", "Error!");
                        }
                    }
                } catch (Exception ex) {
                    AppUtil.showAlert(Alert.AlertType.ERROR, "Rent Order saving failed!", "Error!");
                    ex.printStackTrace();
                }
            } else if (formType == InputFormType.EDIT){
                try {
                    if (validateForm()) {
//                        boolean updateVehicle = false;
//                        if (cmbOwnershipType.getSelectionModel().getSelectedItem().equals("Own")) {
//                            updateVehicle = vehicleBO.updateOwnVehicle(
//                                    new VehicleDTO(
//                                            txtVehicleNo.getText(),
//                                            cmbOwnershipType.getSelectionModel().getSelectedItem(),
//                                            dcInsuranceExpDate.getValue().toString(),
//                                            dcLicenceExpDate.getValue().toString(),
//                                            cmbModel.getSelectionModel().getSelectedItem().getModelId()
//                                    )
//                            );
//                        } else {
//                            if (txtOwnerName.isDisable()) {
//                                updateVehicle = vehicleBO.updateOtherVehicle(
//                                        new VehicleDTO(
//                                                txtVehicleNo.getText(),
//                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
//                                                dcInsuranceExpDate.getValue().toString(),
//                                                dcLicenceExpDate.getValue().toString(),
//                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
//                                                txtOwnerNic.getText()
//                                        ),
//                                        null
//                                );
//                            } else {
//                                updateVehicle = vehicleBO.updateOtherVehicle(
//                                        new VehicleDTO(
//                                                txtVehicleNo.getText(),
//                                                cmbOwnershipType.getSelectionModel().getSelectedItem(),
//                                                dcInsuranceExpDate.getValue().toString(),
//                                                dcLicenceExpDate.getValue().toString(),
//                                                cmbModel.getSelectionModel().getSelectedItem().getModelId(),
//                                                txtOwnerNic.getText()
//                                        ),
//                                        new VehicleOwnerDTO(
//                                                txtOwnerNic.getText(),
//                                                txtOwnerName.getText(),
//                                                txtOwnerAddress.getText(),
//                                                txtOwnerMobile.getText().isEmpty() ? 0 : Integer.parseInt(txtOwnerMobile.getText())
//                                        )
//                                );
//                            }
//                        }
//                        if (updateVehicle) {
//                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Vehicle updated successfully!", "Done!");
//                            vehicleFormController.loadVehicleTable();
//                            ((Stage) btnSave.getScene().getWindow()).close();
//                        }
                    }
                } catch (Exception ex) {
                    AppUtil.showAlert(Alert.AlertType.ERROR, "Vehicle update failed!", "Error!");
                    ex.printStackTrace();
                }
            }
        });
        
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.cusOrderDTO = (CusOrderDTO) dto;
        lblTitle.setText(formTitle);
        this.rentFormController = (RentFormController) parentController;
        if (formType == InputFormType.ADD) {
            loadNextId();
            btnDelete.setVisible(false);
        } else if (formType == InputFormType.EDIT){
//            txtModelID.setText(modelDTO.getModelId()+"");
//            txtModelName.setText(modelDTO.getModelName());
//            txtNoOfSeats.setText(modelDTO.getNoOfseats()+"");
//            txtDepositAmount.setText(AppUtil.formatDouble(modelDTO.getDepositAmount()));
//            txtPricePerKM.setText(AppUtil.formatDouble(modelDTO.getPricePerKm()));
//            for (int i = 0; i < cmbCategory.getItems().size(); i++) {
//                if (cmbCategory.getItems().get(i).getCategoryId() == modelDTO.getCategoryId()) {
//                    cmbCategory.getSelectionModel().select(i);
//                    break;
//                }
//            }
//            for (int i = 0; i < cmbMake.getItems().size(); i++) {
//                if (cmbMake.getItems().get(i).getMakeId()== modelDTO.getCategoryId()) {
//                    cmbMake.getSelectionModel().select(i);
//                    break;
//                }
//            }
        }
    }
    
    private void calcMinAmount(){
        if (FormValidator.isNotEmpty(txtNoOfDays.getText()) && 
                !cmbModel.getSelectionModel().isEmpty()) {
            try {
                ConfigDTO configDTO = configBO.getConfigValue(Constans.ConfigIds.KM_PER_DAY);
                int kmPerDay = Integer.parseInt(configDTO.getValue());
                double pricePerKm = cmbModel.getSelectionModel()
                        .getSelectedItem().getPricePerKm();
                int noOfDays = Integer.parseInt(txtNoOfDays.getText());
                double minAmount = kmPerDay * noOfDays * pricePerKm;
                if (FormValidator.isNotEmpty(txtDriverPricePerDay.getText())) {
                    minAmount += noOfDays * 
                            Double.parseDouble(txtDriverPricePerDay.getText());
                }
                txtMinAmount.setText(AppUtil.formatDouble(minAmount));
            } catch (Exception ex) {
                Logger.getLogger(RentInputFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txtMinAmount.setText("");
        }
    }

    private void loadNextId() {
        try {
            int nextId = cusOrderBO.getNextId();
            txtOrderId.setText(nextId+"");
        } catch (Exception ex) {
            Logger.getLogger(RentInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDriverCmb() {
        try {
            ArrayList<DriverDTO> search = driverBO.getAvailableDrivers();
            cmbDriver.getItems().clear();
            cmbDriver.getItems().add(new DriverDTO(null, "*** Without Driver ***", null, 0));
            cmbDriver.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(RentInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    private void loadVehicleCmb() {
        try {
            if (!cmbModel.getSelectionModel().isEmpty()) {
                ArrayList<VehicleDTO> vehiclesByModelId = vehicleBO
                        .getVehiclesByModelId(
                                cmbModel.getSelectionModel().getSelectedItem().getModelId()
                        );
                cmbVehicle.getItems().clear();
                cmbVehicle.getItems().addAll(vehiclesByModelId);
                cmbVehicle.setDisable(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(VehicleInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validateForm(){
        if (cmbDriver.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Driver cannot be empty!", "Error!");
            cmbDriver.requestFocus();
            return false;
        } else if (dcJourneyDate.getValue() == null) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Journey Date cannot be empty!", "Error!");
            dcJourneyDate.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtNoOfDays.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Days cannot be empty!", "Error!");
            txtNoOfDays.requestFocus();
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
        } else if (cmbVehicle.getSelectionModel().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Vehicle No cannot be empty!", "Error!");
            cmbVehicle.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerNic.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer NIC cannot be empty!", "Error!");
            txtCustomerNic.requestFocus();
            return false;
        } else if (cmbDriver.getSelectionModel().getSelectedItem().getLicenceNo() == null &&
                !FormValidator.isNotEmpty(txtCustomerLicenceNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Licence No cannot be empty!\n"
                    + "Note : if you rent a vehicle without a driver, Customer Licence No is a required field!", "Error!");
            txtCustomerLicenceNo.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerName.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Name cannot be empty!", "Error!");
            txtCustomerName.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerAddress.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Address cannot be empty!", "Error!");
            txtCustomerAddress.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerMobile.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Mobile cannot be empty!", "Error!");
            txtCustomerMobile.requestFocus();
            return false;
        } else if (!FormValidator.isValidNic(txtCustomerNic.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Customer NIC!", "Error!");
            txtCustomerNic.requestFocus();
            return false;
        } else if (FormValidator.isNotEmpty(txtCustomerLicenceNo.getText()) &&
                !FormValidator.isValidLicenceNo(txtCustomerLicenceNo.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Customer Licence No!", "Error!");
            txtCustomerLicenceNo.requestFocus();
            return false;
        } else if (!FormValidator.isValidMobile(txtCustomerMobile.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Customer Mobile!", "Error!");
            txtCustomerMobile.requestFocus();
            return false;
        }
        return true;
    }

    private void loadKmPerDay() {
        try {
            txtKmPerDay.setText(
                    configBO.getConfigValue(Constans.ConfigIds.KM_PER_DAY)
                            .getValue());
        } catch (Exception ex) {
            Logger.getLogger(RentInputFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

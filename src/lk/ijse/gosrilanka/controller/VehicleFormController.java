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
import lk.ijse.gosrilanka.bo.custom.CategaryBO;
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.bo.custom.ModelBO;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.bo.custom.VehicleOwnerBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;
import lk.ijse.gosrilanka.dto.VehicleDTO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class VehicleFormController implements Initializable {
    
    private ModelBO modelBO;
    private VehicleBO vehicleBO;
    private MakeBO makeBO;
    private CategaryBO categaryBO;
    private VehicleOwnerBO vehicleOwnerBO;

    @FXML
    private TextField txtVehicleSearch;
    @FXML
    private JFXButton btnVehicleAdd;
    @FXML
    private JFXButton btnVehicleEdit;
    @FXML
    private TableView<CustomDTO> tblVehicle;
    @FXML
    private TextField txtMakeSearch;
    @FXML
    private JFXButton btnMakeAdd;
    @FXML
    private JFXButton btnMakeEdit;
    @FXML
    private TableView<MakeDTO> tblMake;
    @FXML
    private TextField txtModelSearch;
    @FXML
    private JFXButton btnModelAdd;
    @FXML
    private JFXButton btnModelEdit;
    @FXML
    private TableView<CustomDTO> tblModel;
    @FXML
    private TextField txtCategarySearch;
    @FXML
    private JFXButton btnCategaryAdd;
    @FXML
    private JFXButton btnCategaryEdit;
    @FXML
    private TableView<CategoryDTO> tblCategary;
    @FXML
    private TextField txtVehicleOwnerSearch;
    @FXML
    private JFXButton btnVehicleOwnerAdd;
    @FXML
    private JFXButton btnVehicleOwnerEdit;
    @FXML
    private TableView<VehicleOwnerDTO> tblVehicleOwner;
    
    public VehicleFormController() {
        makeBO = (MakeBO) BOFactory.getInstance().getBO(BOFactory.BOType.MAKE);
        categaryBO = (CategaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGARY);
        modelBO = (ModelBO) BOFactory.getInstance().getBO(BOFactory.BOType.MODEL);
        vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
        vehicleOwnerBO = (VehicleOwnerBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE_OWNER);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTables();
        loadMakeTable();
        loadCategaryTable();
        loadModelTable();
        loadVehicleTable();
        loadVehicleOwnerTable();
        
        btnMakeAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("MakeInputForm.fxml", "Add New Vehicle Make", this);
        });
        
        btnVehicleAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("VehicleInputForm.fxml", "Add New Vehicle", this);
        });
        
        btnModelAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("ModelInputForm.fxml", "Add New Vehicle Model", this);
        });
        
        btnVehicleOwnerAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("VehicleOwnerInputForm.fxml", "Add New Vehicle Owner", this);
        });
        
        btnMakeEdit.setOnAction((event) -> {
            if (!tblMake.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm("MakeInputForm.fxml", "Edit Vehicle Make", this, tblMake.getSelectionModel().getSelectedItem());
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a make from the table!", "Error!");
            }            
        });
        
        btnVehicleEdit.setOnAction((event) -> {
            if (!tblVehicle.getSelectionModel().isEmpty()) {
                CustomDTO c = tblVehicle.getSelectionModel().getSelectedItem();
                VehicleDTO vdto = new VehicleDTO(
                        c.getVehicleNo(), 
                        c.getOwnershipType(),
                        c.getRegisteredDate(),
                        c.getInsuarenceExpireDate(),
                        c.getLicenceExpireDate(), 
                        c.getModelId(), 
                        c.getOwnerNic()
                );
                AppUtil.loadEditInputForm("VehicleInputForm.fxml", "Edit Vehicle", this,vdto);
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a vehicle from the table!", "Error!");
            }            
        });
        
        btnModelEdit.setOnAction((event) -> {
            if (!tblModel.getSelectionModel().isEmpty()) {
                CustomDTO selectedTblMdl = tblModel.getSelectionModel().getSelectedItem();
                ModelDTO selectedModel = new ModelDTO(
                        selectedTblMdl.getModelId(), 
                        selectedTblMdl.getModelName(), 
                        selectedTblMdl.getNoOfseats(), 
                        selectedTblMdl.getPricePerKm(),
                        selectedTblMdl.getDepositAmount(), 
                        selectedTblMdl.getMakeId(),
                        selectedTblMdl.getCategoryId()
                );
                AppUtil.loadEditInputForm("ModelInputForm.fxml", "Edit Vehicle Model", this, selectedModel);
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a model from the table!", "Error!");
            }            
        });
        
        btnVehicleOwnerEdit.setOnAction((event) -> {
            if (!tblVehicleOwner.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm("VehicleOwnerInputForm.fxml", "Edit Vehicle Owner", this, tblVehicleOwner.getSelectionModel().getSelectedItem());
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a vehicle Owner from the table!", "Error!");
            }
        });
        
        txtMakeSearch.setOnKeyReleased((event) -> {
            loadMakeTable();
        });
        
        txtCategarySearch.setOnKeyReleased((event) -> {
            loadCategaryTable();
        });
        
        txtModelSearch.setOnKeyReleased((event) -> {
            loadModelTable();
        });
        
        txtVehicleSearch.setOnKeyReleased((event) -> {
            loadVehicleTable();
        });
        
        btnCategaryAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("CategaryInputForm.fxml", "Add New Vehicle Categary", this);
        });
        
        txtVehicleOwnerSearch.setOnAction((event) -> {
            loadVehicleOwnerTable();
        });
        
        btnCategaryEdit.setOnAction((event) -> {
            if (!tblCategary.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm("CategaryInputForm.fxml", "Edit Vehicle Categary", this, tblCategary.getSelectionModel().getSelectedItem());
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, "Please select a Categary from the table!", "Error!");
            }  
        });
    }    

    private void initTables() {
        //Make Table
        tblMake.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("makeId"));
        tblMake.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("makeName"));
        
        //Categary Table
        tblCategary.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        tblCategary.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        
        //Model Table
        tblModel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("modelId"));
        tblModel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("modelName"));
        tblModel.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("noOfseats"));
        tblModel.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("pricePerKm"));
        tblModel.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("depositAmount"));
        tblModel.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblModel.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("makeName"));
        
        //Vehicle Owner Table
        tblVehicleOwner.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ownerNic"));
        tblVehicleOwner.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblVehicleOwner.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblVehicleOwner.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        
        //Vehicle Table
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ownershipType"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ownerNic"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblVehicle.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("makeName"));
        tblVehicle.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("modelName"));
        tblVehicle.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
        tblVehicle.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("insuarenceExpireDate"));
        tblVehicle.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("licenceExpireDate"));
    }
    
    public void loadModelTable() {
        try {
            ArrayList<CustomDTO> search = modelBO.search(txtModelSearch.getText());
            tblModel.getItems().clear();
            tblModel.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadMakeTable() {
        try {
            ArrayList<MakeDTO> search = makeBO.search(txtMakeSearch.getText());
            tblMake.getItems().clear();
            tblMake.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadCategaryTable() {
        try {
            ArrayList<CategoryDTO> search = categaryBO.search(txtCategarySearch.getText());
            tblCategary.getItems().clear();
            tblCategary.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadVehicleTable() {
        try {
            ArrayList<CustomDTO> search = vehicleBO.search(txtVehicleSearch.getText());
            tblVehicle.getItems().clear();
            tblVehicle.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadVehicleOwnerTable() {
        try {
            ArrayList<VehicleOwnerDTO> search = vehicleOwnerBO.search(txtVehicleOwnerSearch.getText());
            tblVehicleOwner.getItems().clear();
            tblVehicleOwner.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(VehicleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

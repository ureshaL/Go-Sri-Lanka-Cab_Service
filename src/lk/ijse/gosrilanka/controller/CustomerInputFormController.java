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
import lk.ijse.gosrilanka.bo.custom.CustomerBO;
import lk.ijse.gosrilanka.dto.CustomerDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class CustomerInputFormController extends SuperInputForm implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtCustomerNIC;
    @FXML
    private TextField txtLicenceNo;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerAddress;
    @FXML
    private TextField txtMobile;
    @FXML
    private JFXButton btnSave;
    
    private CustomerDTO customerDTO;
    private CustomerFormController customerFormController;
    private CustomerBO customerBO;
    @FXML
    private JFXButton btnDelete;

    public CustomerInputFormController() {
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    }    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnSave.setOnAction((event) -> {
            if (formType == SuperInputForm.InputFormType.ADD) {
                try {
                    if (validateForm()) {
                        boolean addCustomer = customerBO.addCustomer(new CustomerDTO(
                                txtCustomerNIC.getText(),
                                txtLicenceNo.getText(), 
                                txtCustomerName.getText(),
                                txtCustomerAddress.getText(),
                                Integer.parseInt(txtMobile.getText())
                            )
                        );
                        if (addCustomer) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Customer added successfully!", "Done!");
                            customerFormController.loadCustomerTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (formType == SuperInputForm.InputFormType.EDIT){
                try {
                    if (validateForm()) {
                        boolean updateCustomer = customerBO.updateCustomer(
                                new CustomerDTO(
                                        txtCustomerNIC.getText(),
                                        txtLicenceNo.getText(),
                                        txtCustomerName.getText(),
                                        txtCustomerAddress.getText(),
                                        Integer.parseInt(txtMobile.getText())
                                )
                        );
                        if (updateCustomer) {
                            AppUtil.showAlert(Alert.AlertType.INFORMATION, "Customer updated successfully!", "Done!");
                            customerFormController.loadCustomerTable();
                            ((Stage) btnSave.getScene().getWindow()).close();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CustomerInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
//        btnDelete.setOnAction((event) -> {
//            try {
//                boolean deleteCustomer = customerBO.deleteCustomer(txtCustomerNIC.getText());
//                if (deleteCustomer) {
//                    AppUtil.showAlert(Alert.AlertType.INFORMATION, "Customer deleted successfully!", "Done!");
//                    customerFormController.loadCustomerTable();
//                    ((Stage) btnSave.getScene().getWindow()).close();
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        this.formType = inputFormType;
        this.customerDTO = (CustomerDTO) dto;
        lblTitle.setText(formTitle);
        this.customerFormController = (CustomerFormController) parentController;
//        if (formType == InputFormType.ADD) {
////            btnDelete.setVisible(false);
//        } else 
        if (formType == InputFormType.EDIT) {
            txtCustomerNIC.setText(customerDTO.getCustomerNic());
            txtLicenceNo.setText(customerDTO.getLicenceNo());
            txtCustomerName.setText(customerDTO.getName());
            txtCustomerAddress.setText(customerDTO.getAddress());
            txtMobile.setText(customerDTO.getMobile()+ "");
        }

    }
    
    private boolean validateForm(){
        if (!FormValidator.isNotEmpty(txtCustomerNIC.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer NIC cannot be empty!", "Error!");
            txtCustomerNIC.requestFocus();
            return false;
        } else if (!FormValidator.isValidNic(txtCustomerNIC.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Customer NIC!", "Error!");
            txtCustomerNIC.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerName.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Name cannot be empty!", "Error!");
            txtCustomerName.requestFocus();
            return false;
        } else if (!FormValidator.isNotEmpty(txtCustomerAddress.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Customer Address cannot be empty!", "Error!");
            txtCustomerAddress.requestFocus();
            return false;
        } else if (!FormValidator.isValidMobile(txtMobile.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR, "Invalid value in field Mobile!", "Error!");
            txtMobile.requestFocus();
            return false;
        }
        return true;
    }
}

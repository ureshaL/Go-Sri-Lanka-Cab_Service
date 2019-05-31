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
import lk.ijse.gosrilanka.bo.custom.CustomerBO;
import lk.ijse.gosrilanka.dto.CustomerDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class CustomerFormController implements Initializable {

    private CustomerBO customerBO;

    @FXML
    private TextField txtCustomerSearch;
    @FXML
    private JFXButton btnCustomerAdd;
    @FXML
    private JFXButton btnCustomerEdit;
    @FXML
    private TableView<CustomerDTO> tblCustomer;

    public CustomerFormController() {
        customerBO
                = (CustomerBO) BOFactory.getInstance().getBO(
                        BOFactory.BOType.CUSTOMER
                );
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTables();
        loadCustomerTable();

        btnCustomerAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("CustomerInputForm.fxml", "Customer", this);
        });

        btnCustomerEdit.setOnAction((event) -> {
            if (!tblCustomer.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm(
                        "CustomerInputForm.fxml",
                        "Customer",
                        this,
                        tblCustomer.getSelectionModel().getSelectedItem());
            } else {
                AppUtil.showAlert(
                        Alert.AlertType.ERROR,
                        "Please select a Customer from the table!",
                        "Error!"
                );
            }
        });

        txtCustomerSearch.setOnKeyReleased((event) -> {
            loadCustomerTable();
        });

    }

    private void initTables() {
        //Customer Table
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerNic"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("licenceNo"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("mobile"));

    }

    public void loadCustomerTable() {
        try {
            ArrayList<CustomerDTO> search = customerBO.search(txtCustomerSearch.getText());
            tblCustomer.getItems().clear();
            tblCustomer.getItems().addAll(search);
        } catch (Exception ex) {
            Logger.getLogger(DriverFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

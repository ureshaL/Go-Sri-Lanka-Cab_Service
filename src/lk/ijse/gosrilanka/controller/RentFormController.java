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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.CusOrderBO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class RentFormController implements Initializable {

    private CusOrderBO cusOrderBO;
    
    @FXML
    private TextField txtRentSearch;
    @FXML
    private JFXButton btnRentOrderAdd;
    @FXML
    private TableView<CustomDTO> tblRentOrder;

    public RentFormController() {
        cusOrderBO = (CusOrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUS_ORDER);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadRentOrderTbl();
        
        btnRentOrderAdd.setOnAction((event) -> {
            AppUtil.loadAddInputForm("RentInputFormNew.fxml", "Add New Rent Order", this);
        });
        
        txtRentSearch.setOnKeyReleased((event) -> {
            loadRentOrderTbl();
        });
        
    }    

    private void initTable() {
        tblRentOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblRentOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        tblRentOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblRentOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("journeyDate"));
        tblRentOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tblRentOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("noOfDays"));
        tblRentOrder.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("spentDays"));
        tblRentOrder.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("overDays"));
        tblRentOrder.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("kmSpent"));
        tblRentOrder.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblRentOrder.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("driverName"));
        tblRentOrder.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void loadRentOrderTbl() {
        try {
            ArrayList<CustomDTO> searchRentOrders = cusOrderBO.searchRentOrders(txtRentSearch.getText());
            tblRentOrder.getItems().clear();
            tblRentOrder.getItems().addAll(searchRentOrders);
        } catch (Exception ex) {
            Logger.getLogger(RentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

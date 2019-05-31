/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Nipun Lakshan
 */
public class ReportFormController implements Initializable {

    @FXML
    private JFXButton btnDriver;
    @FXML
    private DatePicker driverEdate;
    @FXML
    private DatePicker driverSdate;
    @FXML
    private JFXButton btnCustomer;
    @FXML
    private DatePicker customerEdate;
    @FXML
    private DatePicker customerSdate;
    @FXML
    private JFXButton btnRent;
    @FXML
    private DatePicker rentEdate;
    @FXML
    private DatePicker rentSdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnDriver.setOnAction((event) -> {
            if (driverSdate.getValue() != null && driverEdate.getValue() != null) {
                HashMap parameters = new HashMap();
                parameters.put("startDate", driverSdate.getValue().toString());
                parameters.put("endDate", driverEdate.getValue().toString());
                AppUtil.loadJasperReport("driver_report.jasper", parameters, this);
            } else {
                AppUtil.showAlert(
                        Alert.AlertType.ERROR,
                        "Please input Start Date and End Date correctly! ", 
                        "Report Load Error!"
                );
            }
        });
        
        btnCustomer.setOnAction((event) -> {
            if (customerSdate.getValue() != null && customerEdate.getValue() != null) {
                HashMap parameters = new HashMap();
                parameters.put("startDate", customerSdate.getValue().toString());
                parameters.put("endDate", customerEdate.getValue().toString());
                AppUtil.loadJasperReport("customer_report.jasper", parameters, this);
            } else {
                AppUtil.showAlert(
                        Alert.AlertType.ERROR,
                        "Please input Start Date and End Date correctly! ", 
                        "Report Load Error!"
                );
            }
        });
        
        btnRent.setOnAction((event) -> {
            if (rentSdate.getValue() != null && rentEdate.getValue() != null) {
                HashMap parameters = new HashMap();
                parameters.put("startDate", rentSdate.getValue().toString());
                parameters.put("endDate", rentEdate.getValue().toString());
                AppUtil.loadJasperReport("rent_order_report.jasper", parameters, this);
            } else {
                AppUtil.showAlert(
                        Alert.AlertType.ERROR,
                        "Please input Start Date and End Date correctly! ", 
                        "Report Load Error!"
                );
            }
        });
    }    
    
}

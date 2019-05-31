/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.CusOrderBO;
import lk.ijse.gosrilanka.dto.CusOrderDTO;
import lk.ijse.gosrilanka.util.AppUtil;
import lk.ijse.gosrilanka.util.FormValidator;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class FinishOrderInputFormController extends SuperInputForm implements Initializable {
    
    private HomeFormController homeFormController;
    private CusOrderBO cusOrderBO;

    @FXML
    private TextField txtOrderId;
    @FXML
    private DatePicker dcReturnDate;
    @FXML
    private TextField txtSpentKm;
    @FXML
    private JFXButton btnFinishOrder;    

    public FinishOrderInputFormController() {
        cusOrderBO = (CusOrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUS_ORDER);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dcReturnDate.setValue(LocalDate.now());
        btnFinishOrder.setOnAction((event) -> {
            if (validateForm()) {
                try {
                    CusOrderDTO rentOrder = cusOrderBO
                            .getRentOrder(Integer.parseInt(txtOrderId.getText()));
                    boolean finishOrder = cusOrderBO.finishOrder(
                            rentOrder.getOrderId(), 
                            dcReturnDate.getValue().toString(),
                            Integer.parseInt(txtSpentKm.getText()), 
                            rentOrder.getVehicleNo(),
                            rentOrder.getLicenceNo()
                    );
                    if (finishOrder) {
                        AppUtil.showAlert(Alert.AlertType.INFORMATION, 
                                "Rent Order finished successfully!", "Done!");
                        homeFormController.loadOnGoingOrdersTbl();
                        homeFormController.loadDashboardTileCounts();
                        ((Stage) btnFinishOrder.getScene().getWindow()).close();
                    } else {
                        AppUtil.showAlert(Alert.AlertType.ERROR, 
                                "Rent Order finishing failed!", "Error!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FinishOrderInputFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    

    @Override
    public void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController) {
        txtOrderId.setText((int) dto + "");
        this.homeFormController = (HomeFormController) parentController;
    }
    
    private boolean validateForm(){
        if (txtSpentKm.getText().trim().isEmpty()) {
            AppUtil.showAlert(Alert.AlertType.ERROR,
                    "Please enter a spent Km!",
                    "Error!");
            txtSpentKm.requestFocus();
            return false;
        }
        if (!FormValidator.isNumber(txtSpentKm.getText())) {
            AppUtil.showAlert(Alert.AlertType.ERROR,
                    "Please enter a valid spent Km!",
                    "Error!");
            txtSpentKm.requestFocus();
            return false;
        }
        return true;
    }
    
}

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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gosrilanka.bo.BOFactory;
import lk.ijse.gosrilanka.bo.custom.CusOrderBO;
import lk.ijse.gosrilanka.bo.custom.CustomerBO;
import lk.ijse.gosrilanka.bo.custom.DriverBO;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.util.AppUtil;

/**
 * FXML Controller class
 *
 * @author Uresha Lakshani
 */
public class HomeFormController implements Initializable {

    private VehicleBO vehicleBO;
    private DriverBO driverBO;
    private CustomerBO customerBO;
    private CusOrderBO cusOrderBO;

    @FXML
    private Label lblCustomerCount;
    @FXML
    private Label lblVehicleCount;
    @FXML
    private Label lblDriverCount;
    @FXML
    private Label lblRentCount;
    @FXML
    private TableView<CustomDTO> tblPendingOrders;
    @FXML
    private JFXButton btnCancelOrder;
    @FXML
    private TableView<CustomDTO> tblOngoingOrders;
    @FXML
    private JFXButton btnFinishOrder;
    @FXML
    private TableView<CustomDTO> tblAlerts;
    @FXML
    private JFXButton btnMarkAsComplete;

    public HomeFormController() {
        vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
        driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
        cusOrderBO = (CusOrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUS_ORDER);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTables();
        loadDashboardTileCounts();
        loadPendingOrderTbl();
        loadOnGoingOrdersTbl();
        loadAlertsTbl();
                
        btnCancelOrder.setOnAction((event) -> {
            if (!tblPendingOrders.getSelectionModel().isEmpty()) {
                AppUtil.showConfirmAlert(
                        "Are you sure do you really want to cancel order ?",
                        "Order Cancellation Confirmation?",
                        (e) -> {
                            try {
                                boolean cancelRentOrder = cusOrderBO
                                        .cancelRentOrder(tblPendingOrders
                                        .getSelectionModel()
                                        .getSelectedItem().getOrderId());
                                if (cancelRentOrder) {
                                    AppUtil.showAlert(Alert.AlertType.INFORMATION,
                                            "Rent Order cancelled successfully!",
                                            "Done!");
                                    loadPendingOrderTbl();
                                } else {
                                    AppUtil.showAlert(Alert.AlertType.ERROR,
                                            "Rent Order cancelling failed!",
                                            "Error!");
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(HomeFormController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        },
                        null
                );
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR, 
                        "Please select an Order from the On Going Rent Orders Table!", 
                        "Error!");
            }
        });
        
        btnFinishOrder.setOnAction((event) -> {
            if (!tblOngoingOrders.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm(
                        "FinishOrderInputForm.fxml",
                        "Finish Rent Order",
                        this,
                        tblOngoingOrders.getSelectionModel().getSelectedItem().getOrderId()
                );
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR,
                        "Please select a row from the On Going Rent Orders table!",
                        "Error!"
                );
            }
        });
        
        btnMarkAsComplete.setOnAction((event) -> {
            if (!tblAlerts.getSelectionModel().isEmpty()) {
                AppUtil.loadEditInputForm(
                        "AlertMarkInputForm.fxml",
                        "Mark Alert As Complete",
                        this,
                        tblAlerts.getSelectionModel().getSelectedItem()
                );
            } else {
                AppUtil.showAlert(Alert.AlertType.ERROR,
                        "Please select an alert from the Alerts Table!",
                        "Error!"
                );
            }
        });
    }

    public void loadDashboardTileCounts() {
        try {
            lblVehicleCount.setText(vehicleBO.getDashboardVehicleCount() + "");
            lblDriverCount.setText(driverBO.getDashboardDriverCount() + "");
            lblCustomerCount.setText(customerBO.getDashboardCustomerCount() + "");
            lblRentCount.setText(cusOrderBO.getDashboardRentCount() + "");
        } catch (Exception ex) {
            Logger.getLogger(HomeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTables() {
        //pending rent orders
        tblPendingOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        tblPendingOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("driverName"));
        tblPendingOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblPendingOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("journeyDate"));

        //On Going Orders Table
        tblOngoingOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        tblOngoingOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("driverName"));
        tblOngoingOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblOngoingOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("journeyDate"));
        
        //Alerts Table
        tblAlerts.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        tblAlerts.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("alertType"));
        tblAlerts.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        tblAlerts.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    public void loadPendingOrderTbl() {
        try {
            ArrayList<CustomDTO> pendingRentOrders = cusOrderBO.getPendingRentOrders();
            tblPendingOrders.getItems().clear();
            tblPendingOrders.getItems().addAll(pendingRentOrders);
        } catch (Exception ex) {
            Logger.getLogger(HomeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadOnGoingOrdersTbl() {
        try {
            ArrayList<CustomDTO> onGoingOrders = cusOrderBO.getOnGoingOrders();
            tblOngoingOrders.getItems().clear();
            tblOngoingOrders.getItems().addAll(onGoingOrders);
        } catch (Exception ex) {
            Logger.getLogger(HomeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadAlertsTbl() {
        try {
            ArrayList<CustomDTO> alerts = cusOrderBO.getAlerts();
            tblAlerts.getItems().clear();
            tblAlerts.getItems().addAll(alerts);
        } catch (Exception ex) {
            Logger.getLogger(HomeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

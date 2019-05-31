/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gosrilanka.controller.SuperInputForm;
import lk.ijse.gosrilanka.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Uresha Lakshani
 */
public class AppUtil {
            
    public static void loadAddInputForm(String formName, String formTitle, Initializable controller){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/lk/ijse/gosrilanka/view/"+formName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(formTitle);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
            
            SuperInputForm inputFormController = loader.<SuperInputForm>getController();
            inputFormController.setValues(SuperInputForm.InputFormType.ADD, null, formTitle, controller);
        } catch (IOException ex) {
            Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadEditInputForm(String formName, String formTitle, Initializable controller, Object dto){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/lk/ijse/gosrilanka/view/"+formName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(formTitle);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
            
            SuperInputForm inputFormController = loader.<SuperInputForm>getController();
            inputFormController.setValues(SuperInputForm.InputFormType.EDIT, dto, formTitle, controller);
        } catch (IOException ex) {
            Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showAlert(Alert.AlertType alertType, String message, String title){
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setHeaderText(title);
        alert.showAndWait();
    }
    
    public static String formatDouble(double value){
        return new DecimalFormat("0.00").format(value);
    }
    
    public static void toggleStatusOfUiElements(boolean isDisabled,Node...elements){
        for (Node element : elements) {
            element.setDisable(isDisabled);
        }
    }
    
    public static void clearUiElements(Node...elements){
        for (Node element : elements) {
            if (element instanceof TextField) {
                ((TextField) element).clear();
            }
            if (element instanceof TextArea) {
                ((TextArea) element).clear();
            }
        }
    }
    
    public static String getCurrentTimestamp(){
        return LocalDate.now().toString()+" "+
                LocalTime.now().toString().split("[.]")[0];
    }
    
    public static void setDateChooserMinDate(DatePicker datePicker, LocalDate date){
        datePicker.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isBefore(date));
               }});
    }
    
    public static void loadJasperReport(
            String reportFileName, HashMap parameters, Initializable parentController){
        try {
            InputStream reportFile = parentController
                    .getClass()
                    .getResourceAsStream("/lk/ijse/gosrilanka/report/"+reportFileName);
            JasperPrint report = JasperFillManager
                    .fillReport(reportFile, 
                            parameters, 
                            DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(report, false);
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(parentController.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showConfirmAlert( String message, String title, 
            EventHandler yesListener, 
            EventHandler noListener){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                message,ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(title);
        Optional<ButtonType> showAndWait = alert.showAndWait();
        if (showAndWait.get() == ButtonType.YES && yesListener != null) {
            yesListener.handle(null);
        } else if (showAndWait.get() == ButtonType.NO && noListener != null) {
            noListener.handle(null);
        }
    }
}

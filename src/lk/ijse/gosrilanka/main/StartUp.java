/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Uresha Lakshani
 */
public class StartUp extends Application{
    
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent parent = FXMLLoader.load(this.getClass()
                .getResource("/lk/ijse/gosrilanka/view/LoginFormNew.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Go Sri Lanka Cab Service - Login");        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

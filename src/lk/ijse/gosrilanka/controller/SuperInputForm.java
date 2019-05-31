/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.controller;

import javafx.fxml.Initializable;

/**
 *
 * @author Uresha Lakshani
 */
public abstract class SuperInputForm {
    
    protected InputFormType formType;
    
    public enum InputFormType{
        ADD,EDIT
    }
    
    public abstract void setValues(InputFormType inputFormType, Object dto, String formTitle, Initializable parentController);
}

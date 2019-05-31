/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.util;

/**
 *
 * @author Uresha Lakshani
 */
public class FormValidator {
    
    public static boolean isNotEmpty(String source){
        return !source.trim().equals("");
    }
    
    public static boolean isNumber(String source){
        return source.matches("[0-9]+");
    }
    
    public static boolean isDecimal(String source){
        return source.matches("[0-9]+[.]00");
    }
    
    public static boolean isValidNic(String source){
        return source.matches("[0-9]{9}V");
    }
    
    public static boolean isValidVehicleNo(String source){
        return source.matches("([a-z]{2}[A-Z]{2,3}(-)[0-9]{4})|([0-9]{2,3}(-)[0-9]{4})");
    }
    
    public static boolean isValidMobile(String source){
        return source.matches("[0-9]{9}");
    }
    
    public static boolean isValidLicenceNo(String source){
        return source.matches("[A-Z]+[0-9]{7}[A-Z]");
    }
}

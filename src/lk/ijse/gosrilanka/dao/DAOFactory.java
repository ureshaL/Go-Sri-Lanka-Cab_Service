/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao;

import lk.ijse.gosrilanka.dao.custom.impl.CategaryDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.ConfigDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.CusOrderDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.DriverDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.MakeDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.ModelDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gosrilanka.dao.custom.impl.VehicleOwnerDAOImpl;

/**
 *
 * @author Uresha Lakshani
 */
public class DAOFactory {    
    private static DAOFactory dAOFactory;
    
    public enum DAOType{
        MAKE , CATEGARY, QUERY, MODEL, VEHICLE, DRIVER, VEHICLE_OWNER, CUSTOMER, CUS_ORDER, CONFIG
    }
    
    private DAOFactory(){
        
    }
    
    public static DAOFactory getInstance(){
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();            
        }
        return dAOFactory;
    }
    
    public SuperDAO getDAO(DAOType dAOType){
        switch(dAOType){
            case MAKE:
                return new MakeDAOImpl();
            case CATEGARY:
                return new CategaryDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case MODEL:
                return new ModelDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case VEHICLE_OWNER:
                return new VehicleOwnerDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUS_ORDER:
                return new CusOrderDAOImpl();
            case CONFIG:
                return new ConfigDAOImpl();
            default:
                return null;
        }
    }
    
}

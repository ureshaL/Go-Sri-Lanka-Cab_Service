/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo;

import lk.ijse.gosrilanka.bo.custom.impl.CategaryBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.ConfigBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.CusOrderBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.MakeBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.ModelBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.VehicleBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.VehicleOwnerBOImpl;
import lk.ijse.gosrilanka.bo.custom.impl.DriverBOImpl;

/**
 *
 * @author Uresha Lakshani
 */
public class BOFactory {
    private static BOFactory bOFactory;
    
    public enum BOType{
        MAKE, CATEGARY, MODEL,VEHICLE, DRIVER, VEHICLE_OWNER, CUS_ORDER, CUSTOMER, CONFIG
    }

    private BOFactory() {
    }
    
    public static BOFactory getInstance(){
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }
    
    public SuperBO getBO(BOType bOType){
        switch(bOType){
            case MAKE:
                return new MakeBOImpl();
            case CATEGARY:
                return new CategaryBOImpl();
            case MODEL:
                return new ModelBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case VEHICLE_OWNER:
                return new VehicleOwnerBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case CUS_ORDER:
                return new CusOrderBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case CONFIG:
                return new ConfigBOImpl();
            default:
                return null;
        }
    }
}

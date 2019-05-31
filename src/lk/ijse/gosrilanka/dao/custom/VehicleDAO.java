/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.Vehicle;

/**
 *
 * @author Uresha Lakshani
 */
public interface VehicleDAO extends CrudDAO<Vehicle, String>{
    public ArrayList<Vehicle> getVehiclesByModelId(int modelId) throws Exception;
    public ArrayList<Vehicle> getVehiclesByStatus(int status) throws Exception;
    public boolean updateStatus(String vehicleNo, int status) throws Exception;
    public int getDashboardVehicleCount() throws Exception;
    public boolean updateLicenceDate(String vehicleNo, String newDate) throws Exception;
    public boolean updateInsuranceDate(String vehicleNo, String newDate) throws Exception;
}

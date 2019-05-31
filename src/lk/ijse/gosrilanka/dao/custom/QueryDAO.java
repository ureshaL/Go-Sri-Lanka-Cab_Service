/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.SuperDAO;
import lk.ijse.gosrilanka.dto.CustomDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface QueryDAO extends SuperDAO{
    public ArrayList<CustomDTO> searchModels(String value) throws Exception;
    public ArrayList<CustomDTO> searchVehicles(String value) throws Exception;
    public ArrayList<CustomDTO> searchCusOrders(String value) throws Exception;
    public ArrayList<CustomDTO> getPendingOrders()throws Exception;
    public ArrayList<CustomDTO> getOnGoingOrders() throws Exception;
    public ArrayList<CustomDTO> getAlerts() throws Exception;
    public ArrayList<CustomDTO> getAvailableNonImmediateVehicle() throws Exception;
    public ArrayList<CustomDTO> getAvailableImmediateVehicle() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.VehicleDTO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface VehicleBO extends SuperBO{
    public boolean addOwnVehicle(VehicleDTO vehicleDTO) throws Exception;
    public boolean addOtherVehicle(VehicleDTO vehicleDTO, VehicleOwnerDTO vehicleOwnerDTO) throws Exception;
    public boolean updateOwnVehicle(VehicleDTO vehicleDTO) throws Exception;
    public boolean updateOtherVehicle(VehicleDTO vehicleDTO, VehicleOwnerDTO vehicleOwnerDTO) throws Exception;
    public boolean deleteVehicle(String id) throws Exception;
    public ArrayList<CustomDTO> search(String value) throws Exception;
    public ArrayList<VehicleDTO> getVehiclesByModelId(int modelId) throws Exception;
    public int getDashboardVehicleCount() throws Exception;
    public boolean updateVehicleAlert(String vehicleNo, String newDate, String alertType) throws Exception;
    public ArrayList<CustomDTO> getAvailableVehicleByType(boolean isImmediate) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface VehicleOwnerBO extends SuperBO{
    public VehicleOwnerDTO getVehicleOwner(String ownerNic) throws Exception;
    public boolean addVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO) throws Exception;
    public boolean updateVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO) throws Exception;
    public boolean deleteVehicleOwner(String id) throws Exception;
    public ArrayList<VehicleOwnerDTO> search(String value) throws Exception;
}

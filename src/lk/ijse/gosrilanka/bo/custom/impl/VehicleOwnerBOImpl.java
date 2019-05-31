/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.VehicleOwnerBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.VehicleOwnerDAO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;
import lk.ijse.gosrilanka.entity.VehicleOwner;

/**
 *
 * @author Uresha Lakshani
 */
public class VehicleOwnerBOImpl implements VehicleOwnerBO {

    private VehicleOwnerDAO vehicleOwnerDAO;

    public VehicleOwnerBOImpl() {
        vehicleOwnerDAO = (VehicleOwnerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE_OWNER);
    }

    @Override
    public VehicleOwnerDTO getVehicleOwner(String ownerNic) throws Exception {
        VehicleOwner get = vehicleOwnerDAO.get(ownerNic);
        if (get != null) {
            return new VehicleOwnerDTO(
                    get.getOwnerNic(),
                    get.getName(),
                    get.getAddress(),
                    get.getMobile()
            );
        }
        return null;
    }

    @Override
    public boolean addVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO) throws Exception {
        return vehicleOwnerDAO.add(
                new VehicleOwner(
                        vehicleOwnerDTO.getOwnerNic(),
                        vehicleOwnerDTO.getName(),
                        vehicleOwnerDTO.getAddress(),
                        vehicleOwnerDTO.getMobile()
                )
        );
    }

    @Override
    public boolean updateVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO) throws Exception {
        return vehicleOwnerDAO.update(
                new VehicleOwner(
                        vehicleOwnerDTO.getOwnerNic(),
                        vehicleOwnerDTO.getName(),
                        vehicleOwnerDTO.getAddress(),
                        vehicleOwnerDTO.getMobile()
                )
        );
    }

    @Override
    public boolean deleteVehicleOwner(String id) throws Exception {
        return vehicleOwnerDAO.delete(id);
    }

    @Override
    public ArrayList<VehicleOwnerDTO> search(String value) throws Exception {
        ArrayList<VehicleOwner> search = vehicleOwnerDAO.search(value);
        ArrayList<VehicleOwnerDTO> list = new ArrayList<>();
        for (VehicleOwner vehicleOwner : search) {
            list.add(
                    new VehicleOwnerDTO(
                            vehicleOwner.getOwnerNic(),
                            vehicleOwner.getName(),
                            vehicleOwner.getAddress(),
                            vehicleOwner.getMobile()
                    )
            );
        }
        return list;
    }

}

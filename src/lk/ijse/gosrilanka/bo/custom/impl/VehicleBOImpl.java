/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.VehicleBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.QueryDAO;
import lk.ijse.gosrilanka.dao.custom.VehicleDAO;
import lk.ijse.gosrilanka.dao.custom.VehicleOwnerDAO;
import lk.ijse.gosrilanka.db.DBConnection;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.VehicleDTO;
import lk.ijse.gosrilanka.dto.VehicleOwnerDTO;
import lk.ijse.gosrilanka.entity.Vehicle;
import lk.ijse.gosrilanka.entity.VehicleOwner;

/**
 *
 * @author Uresha Lakshani
 */
public class VehicleBOImpl implements VehicleBO{
    
    private VehicleDAO vehicleDAO;
    private QueryDAO queryDAO;
    private VehicleOwnerDAO vehicleOwnerDAO;

    public VehicleBOImpl() {
        vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);
        queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
        vehicleOwnerDAO = (VehicleOwnerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE_OWNER);
    }
    
    @Override
    public boolean deleteVehicle(String id) throws Exception {
        return vehicleDAO.delete(id);
    }

    @Override
    public ArrayList<CustomDTO> search(String value) throws Exception {
        return queryDAO.searchVehicles(value);
    }

    @Override
    public boolean addOwnVehicle(VehicleDTO vehicleDTO) throws Exception {
        return vehicleDAO.add(
                new Vehicle(
                        vehicleDTO.getVehicleNo(),
                        vehicleDTO.getOwnershipType(),
                        vehicleDTO.getRegisteredDate(),
                        vehicleDTO.getInsuarenceExpireDate(),
                        vehicleDTO.getLicenceExpireDate(),
                        vehicleDTO.getModelId()
                )
        );
    }

    @Override
    public boolean addOtherVehicle(VehicleDTO vehicleDTO, VehicleOwnerDTO vehicleOwnerDTO) throws Exception {
        if (vehicleOwnerDTO == null) {
            return vehicleDAO.add(
                    new Vehicle(
                            vehicleDTO.getVehicleNo(),
                            vehicleDTO.getOwnershipType(),
                            vehicleDTO.getRegisteredDate(),
                            vehicleDTO.getInsuarenceExpireDate(),
                            vehicleDTO.getLicenceExpireDate(),
                            vehicleDTO.getModelId(),
                            vehicleDTO.getOwnerNic()
                    )
            );
        } else {
            try{
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean addVehicleOwner = vehicleOwnerDAO.add(new VehicleOwner(
                        vehicleOwnerDTO.getOwnerNic(),
                        vehicleOwnerDTO.getName(),
                        vehicleOwnerDTO.getAddress(), 
                        vehicleOwnerDTO.getMobile()
                ));
                if (addVehicleOwner) {
                    boolean addVehicle = vehicleDAO.add(
                            new Vehicle(
                                    vehicleDTO.getVehicleNo(),
                                    vehicleDTO.getOwnershipType(),
                                    vehicleDTO.getRegisteredDate(),
                                    vehicleDTO.getInsuarenceExpireDate(),
                                    vehicleDTO.getLicenceExpireDate(),
                                    vehicleDTO.getModelId(),
                                    vehicleDTO.getOwnerNic()
                            )
                    );
                    if (addVehicle) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                        return false;
                    }
                } else {
                    DBConnection.getInstance().getConnection().rollback();
                    return false;
                }
            } finally {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            }
        }
    }

    @Override
    public boolean updateOwnVehicle(VehicleDTO vehicleDTO) throws Exception {
        return vehicleDAO.update(
                new Vehicle(
                        vehicleDTO.getVehicleNo(),
                        vehicleDTO.getOwnershipType(),
                        vehicleDTO.getInsuarenceExpireDate(),
                        vehicleDTO.getLicenceExpireDate(),
                        vehicleDTO.getModelId()
                )
        );
    }

    @Override
    public boolean updateOtherVehicle(VehicleDTO vehicleDTO, VehicleOwnerDTO vehicleOwnerDTO) throws Exception {
        if (vehicleOwnerDTO == null) {
            return vehicleDAO.update(
                    new Vehicle(
                            vehicleDTO.getVehicleNo(),
                            vehicleDTO.getOwnershipType(),
                            vehicleDTO.getInsuarenceExpireDate(),
                            vehicleDTO.getLicenceExpireDate(),
                            vehicleDTO.getModelId(),
                            vehicleDTO.getOwnerNic()
                    )
            );
        } else {
            try{
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean addVehicleOwner = vehicleOwnerDAO.add(new VehicleOwner(
                        vehicleOwnerDTO.getOwnerNic(),
                        vehicleOwnerDTO.getName(),
                        vehicleOwnerDTO.getAddress(), 
                        vehicleOwnerDTO.getMobile()
                ));
                if (addVehicleOwner) {
                    boolean updateVehicle = vehicleDAO.update(
                            new Vehicle(
                                    vehicleDTO.getVehicleNo(),
                                    vehicleDTO.getOwnershipType(),
                                    vehicleDTO.getInsuarenceExpireDate(),
                                    vehicleDTO.getLicenceExpireDate(),
                                    vehicleDTO.getModelId(),
                                    vehicleDTO.getOwnerNic()
                            )
                    );
                    if (updateVehicle) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                        return false;
                    }
                } else {
                    DBConnection.getInstance().getConnection().rollback();
                    return false;
                }
            } finally {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            }
        }
    }

    @Override
    public ArrayList<VehicleDTO> getVehiclesByModelId(int modelId) throws Exception {
        ArrayList<Vehicle> vehiclesByModelId = vehicleDAO.getVehiclesByModelId(modelId);
        ArrayList<VehicleDTO> list = new ArrayList<>();
        for (Vehicle vehicle : vehiclesByModelId) {
            list.add(new VehicleDTO(
                    vehicle.getVehicleNo(), 
                    vehicle.getOwnershipType(), 
                    vehicle.getRegisteredDate(), 
                    vehicle.getInsuarenceExpireDate(), 
                    vehicle.getLicenceExpireDate(),
                    vehicle.getStatus(),
                    vehicle.getModelId(),
                    vehicle.getOwnerNic()
            ));
        }
        return list;
    }

    @Override
    public int getDashboardVehicleCount() throws Exception {
        return vehicleDAO.getDashboardVehicleCount();
    }

    @Override
    public boolean updateVehicleAlert(String vehicleNo, String newDate, String alertType) throws Exception {
        if (alertType.equals("Insurance")) {
            return vehicleDAO.updateInsuranceDate(vehicleNo, newDate);
        } else if (alertType.equals("Licence")) {
            return vehicleDAO.updateLicenceDate(vehicleNo, newDate);
        }
        return false;
    }

    @Override
    public ArrayList<CustomDTO> getAvailableVehicleByType(boolean isImmediate) throws Exception {
        ArrayList<CustomDTO> vehicles = null;
        if (isImmediate) {
            vehicles = queryDAO.getAvailableImmediateVehicle();
        } else {
            vehicles = queryDAO.getAvailableNonImmediateVehicle();
        }
        return vehicles;
    }
    
}
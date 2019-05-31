/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.CusOrderBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.CusOrderDAO;
import lk.ijse.gosrilanka.dao.custom.CustomerDAO;
import lk.ijse.gosrilanka.dao.custom.DriverDAO;
import lk.ijse.gosrilanka.dao.custom.QueryDAO;
import lk.ijse.gosrilanka.dao.custom.VehicleDAO;
import lk.ijse.gosrilanka.db.DBConnection;
import lk.ijse.gosrilanka.dto.CusOrderDTO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.CustomerDTO;
import lk.ijse.gosrilanka.entity.CusOrder;
import lk.ijse.gosrilanka.entity.Customer;
import lk.ijse.gosrilanka.util.Constans;

/**
 *
 * @author Uresha Lakshani
 */
public class CusOrderBOImpl implements CusOrderBO{

    private CusOrderDAO cusOrderDAO;
    private CustomerDAO customerDAO;
    private VehicleDAO vehicleDAO;
    private DriverDAO driverDAO;
    private QueryDAO queryDAO;

    public CusOrderBOImpl() {
        cusOrderDAO = (CusOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUS_ORDER);
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
        vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);
        driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
        queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
    }
    
    @Override
    public int getNextId() throws Exception {
        return cusOrderDAO.getNextId();
    }

    @Override
    public boolean placeRentOrder(CusOrderDTO cusOrderDTO, CustomerDTO customerDTO, boolean isCustomerNew) throws Exception {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            
            boolean updateCustomer = false;
            boolean addCusOrder = false;
            boolean updateVehicle = false;
            boolean updateDriver = false;
            
            if (isCustomerNew) {
                updateCustomer = customerDAO.add(new Customer(
                        customerDTO.getCustomerNic(),
                        customerDTO.getLicenceNo(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getMobile()
                ));
            } else {
                updateCustomer = customerDAO.update(new Customer(
                        customerDTO.getCustomerNic(),
                        customerDTO.getLicenceNo(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getMobile()
                ));
            }
            if (updateCustomer) {
                addCusOrder = cusOrderDAO.add(new CusOrder(
                        cusOrderDTO.getOrderDate(),
                        cusOrderDTO.getJourneyDate(),
                        cusOrderDTO.getNoOfDays(),
                        cusOrderDTO.getPricePerKm(),
                        cusOrderDTO.getDriverPricePerDay(),
                        cusOrderDTO.getCustomerNic(),
                        cusOrderDTO.getVehicleNo(),
                        cusOrderDTO.getLicenceNo(),
                        cusOrderDTO.getKmPerDay()
                ));
                if (addCusOrder) {
                    updateVehicle = vehicleDAO.updateStatus(
                            cusOrderDTO.getVehicleNo(), 
                            Constans.StatusTypes.NOT_AVAILABLE
                    );
                    if (updateVehicle) {
                        if (cusOrderDTO.getLicenceNo() != null) {
                            updateDriver = driverDAO.updateStatus(
                                    cusOrderDTO.getLicenceNo(),
                                    Constans.StatusTypes.NOT_AVAILABLE
                            );
                        } else {
                            updateDriver = true;
                        }
                    }
                }
            }
            if (updateCustomer && addCusOrder && updateVehicle && updateDriver) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public int getDashboardRentCount() throws Exception {
        return cusOrderDAO.getDashboardRentCount();
    }

    @Override
    public ArrayList<CustomDTO> searchRentOrders(String value) throws Exception {
        return queryDAO.searchCusOrders(value);
    }

    @Override
    public ArrayList<CustomDTO> getPendingRentOrders() throws Exception {
        return queryDAO.getPendingOrders();
    }
        
    @Override
    public ArrayList<CustomDTO> getOnGoingOrders() throws Exception {
        return queryDAO.getOnGoingOrders();
    }

    @Override
    public boolean cancelRentOrder(int orderId) throws Exception {
        return cusOrderDAO.delete(orderId);
    }

    @Override
    public ArrayList<CustomDTO> getAlerts() throws Exception {
        return queryDAO.getAlerts();
    }

    @Override
    public boolean finishOrder(int orderId, String returnDate, int kmSpent, String vehicleNo, String licenceNo) throws Exception {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdateCusOrder = false;
            boolean isUpdateDriverStatus = false; 
            boolean isUpdateVehicleStatus = false; 
            isUpdateCusOrder = cusOrderDAO.finishOrder(orderId, returnDate, kmSpent);
            if (isUpdateCusOrder) {
                if (licenceNo != null) {
                    isUpdateDriverStatus = driverDAO.updateStatus(licenceNo, Constans.StatusTypes.AVAILABLE);
                } else {
                    isUpdateDriverStatus = true;
                }
                if (isUpdateDriverStatus) {
                    isUpdateVehicleStatus = vehicleDAO.updateStatus(vehicleNo, Constans.StatusTypes.AVAILABLE);
                    if (isUpdateVehicleStatus) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public CusOrderDTO getRentOrder(int orderId) throws Exception {
        CusOrder cusOrder = cusOrderDAO.get(orderId);
        return new CusOrderDTO(
                cusOrder.getOrderId(),
                cusOrder.getOrderDate(), 
                cusOrder.getJourneyDate(), 
                cusOrder.getNoOfDays(),
                cusOrder.getReturnDate(), 
                cusOrder.getPricePerKm(),
                cusOrder.getDriverPricePerDay(), 
                cusOrder.getCustomerNic(),
                cusOrder.getVehicleNo(), 
                cusOrder.getLicenceNo(),
                cusOrder.getStatus(),
                cusOrder.getKmPerDay(),
                cusOrder.getKmSpent()
        );
    }

}

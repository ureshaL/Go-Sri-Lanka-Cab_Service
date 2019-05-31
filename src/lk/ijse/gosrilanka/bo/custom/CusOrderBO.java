/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.CusOrderDTO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.CustomerDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface CusOrderBO extends SuperBO{
    public int getNextId() throws Exception;
    public boolean placeRentOrder(CusOrderDTO cusOrderDTO, CustomerDTO customerDTO, boolean isCustomerNew) throws Exception;
    public int getDashboardRentCount() throws Exception;
    public ArrayList<CustomDTO> searchRentOrders(String value) throws Exception;
    public ArrayList<CustomDTO> getPendingRentOrders() throws Exception;
    public ArrayList<CustomDTO> getOnGoingOrders() throws Exception;
    public ArrayList<CustomDTO> getAlerts() throws Exception;
    public boolean cancelRentOrder(int orderId) throws Exception;
    public boolean finishOrder(int orderId, String returnDate, int kmSpent, String vehicleNo, String licenceNo) throws Exception;
    public CusOrderDTO getRentOrder(int orderId) throws Exception;
}

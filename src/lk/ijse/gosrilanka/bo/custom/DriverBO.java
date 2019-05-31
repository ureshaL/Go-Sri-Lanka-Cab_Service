/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.DriverDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface DriverBO extends SuperBO{
    public boolean addDriver(DriverDTO driverDTO) throws Exception;
    public boolean updateDriver(DriverDTO driverDTO) throws Exception;
    public boolean deleteDriver(String id) throws Exception;
    public ArrayList<DriverDTO> search(String value) throws Exception;
    public ArrayList<DriverDTO> getAvailableDrivers() throws Exception;
    public int getDashboardDriverCount() throws Exception;
}

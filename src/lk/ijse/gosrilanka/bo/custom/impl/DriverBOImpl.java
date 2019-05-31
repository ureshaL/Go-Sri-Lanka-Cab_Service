/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.DriverBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.DriverDAO;
import lk.ijse.gosrilanka.dto.DriverDTO;
import lk.ijse.gosrilanka.entity.Driver;
import lk.ijse.gosrilanka.util.Constans;

/**
 *
 * @author Uresha Lakshani
 */
public class DriverBOImpl implements DriverBO {

    private DriverDAO driverDAO;

    public DriverBOImpl() {
        driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    }

    @Override
    public boolean addDriver(DriverDTO driverDTO) throws Exception {
        return driverDAO.add(new Driver(driverDTO.getLicenceNo(), driverDTO.getDriverName(), driverDTO.getDriverAddress(), driverDTO.getDriverMobile()));
    }

    @Override
    public boolean updateDriver(DriverDTO driverDTO) throws Exception {
        return driverDAO.update(new Driver(driverDTO.getLicenceNo(), driverDTO.getDriverName(), driverDTO.getDriverAddress(), driverDTO.getDriverMobile()));
    }

    @Override
    public boolean deleteDriver(String id) throws Exception {
        return driverDAO.delete(id);
    }

    @Override
    public ArrayList<DriverDTO> search(String value) throws Exception {
        ArrayList<Driver> search = driverDAO.search(value);
        ArrayList<DriverDTO> list = new ArrayList<>();
        for (Driver driver : search) {
            list.add(new DriverDTO(driver.getLicenceNo(), driver.getDriverName(), driver.getDriverAddress(), driver.getDriverMobile()));
        }
        return list;
    }

    @Override
    public ArrayList<DriverDTO> getAvailableDrivers() throws Exception {
        ArrayList<Driver> search = driverDAO.getDriversByStatus(Constans.StatusTypes.AVAILABLE);
        ArrayList<DriverDTO> list = new ArrayList<>();
        for (Driver driver : search) {
            list.add(new DriverDTO(driver.getLicenceNo(), driver.getDriverName(), driver.getDriverAddress(), driver.getDriverMobile()));
        }
        return list;
    }

    @Override
    public int getDashboardDriverCount() throws Exception {
        return driverDAO.getDashboardDriverCount();
    }

}

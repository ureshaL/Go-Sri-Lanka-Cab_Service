/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.Driver;

/**
 *
 * @author Uresha Lakshani
 */
public interface DriverDAO extends CrudDAO<Driver, String>{
    public ArrayList<Driver> search(String value) throws Exception;
    public ArrayList<Driver> getDriversByStatus(int status) throws Exception;
    public boolean updateStatus(String licenceNo, int status) throws Exception;
    public int getDashboardDriverCount() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.DriverDAO;
import lk.ijse.gosrilanka.entity.Driver;
import lk.ijse.gosrilanka.util.Constans;

/**
 *
 * @author Uresha Lakshani
 */
public class DriverDAOImpl implements DriverDAO {

    @Override
    public ArrayList<Driver> search(String value) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Driver WHERE licence_no LIKE '%" + value + "%' OR driver_name LIKE '%" + value + "%' OR driver_address LIKE '%" + value + "%' OR driver_mobile LIKE '%" + value + "%'" );
        ArrayList<Driver> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Driver(rst.getString(1), rst.getString(2), rst.getString(3),rst.getInt(4)));
        }
        return list;
    }

    @Override
    public boolean add(Driver entity) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO Driver VALUES(?,?,?,?,?)",
                entity.getLicenceNo(),
                entity.getDriverName(),
                entity.getDriverAddress(),
                entity.getDriverMobile(),
                entity.getStatus()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Driver WHERE licence_no=?", id);
    }

    @Override
    public boolean update(Driver entity) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Driver SET driver_name=?, driver_address=?, driver_mobile=?, status=?"
                + " WHERE licence_no=?",
                entity.getDriverName(),
                entity.getDriverAddress(),
                entity.getDriverMobile(),
                entity.getStatus(),
                entity.getLicenceNo()
        );
    }

    @Override
    public Driver get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Driver> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateStatus(String licenceNo, int status) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Driver SET status=? WHERE licence_no=?", 
                status, licenceNo
        );
    }

    @Override
    public ArrayList<Driver> getDriversByStatus(int status) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Driver WHERE status=?",
                status
        );
        ArrayList<Driver> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Driver(rst.getString(1), rst.getString(2), rst.getString(3),rst.getInt(4)));
        }
        return list;
    }

    @Override
    public int getDashboardDriverCount() throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT COUNT(*) FROM Driver WHERE status=?",
                Constans.StatusTypes.AVAILABLE
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

}

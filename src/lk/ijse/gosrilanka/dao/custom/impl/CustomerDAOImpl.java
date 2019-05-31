/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.CustomerDAO;
import lk.ijse.gosrilanka.entity.Customer;

/**
 *
 * @author Uresha Lakshani
 */
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> search(String value) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Customer WHERE customer_nic LIKE '%" + value + "%' OR licence_no LIKE '%" + value + "%' OR name LIKE '%" + value + "%' OR address LIKE '%" + value + "%' OR mobile LIKE '%" + value + "%'");
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5)));
        }
        return list;
    }

    @Override
    public boolean add(Customer entity) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO Customer VALUES(?,?,?,?,?)",
                entity.getCustomerNic(),
                entity.getLicenceNo(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE customer_nic=?", id);
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Customer SET licence_no=?, name=?, address=?, mobile=?"
                + " WHERE customer_nic=?",
                entity.getLicenceNo(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getCustomerNic()
        );
    }

    @Override
    public Customer get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE customer_nic=?", id);
        if (rst.next()) {
            return new Customer(
                    rst.getString("customer_nic"), 
                    rst.getString("licence_no"), 
                    rst.getString("name"), 
                    rst.getString("address"), 
                    rst.getInt("mobile")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDashboardCustomerCount() throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT COUNT(*) FROM Customer"
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

}

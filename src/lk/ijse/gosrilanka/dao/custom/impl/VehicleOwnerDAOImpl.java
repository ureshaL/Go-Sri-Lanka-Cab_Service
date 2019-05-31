/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.VehicleOwnerDAO;
import lk.ijse.gosrilanka.entity.VehicleOwner;

/**
 *
 * @author Uresha Lakshani
 */
public class VehicleOwnerDAOImpl implements VehicleOwnerDAO {

    @Override
    public boolean add(VehicleOwner entity) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO Vehicle_Owner VALUES (?,?,?,?)",
                entity.getOwnerNic(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Vehicle_Owner WHERE owner_nic=?", id);
    }

    @Override
    public boolean update(VehicleOwner entity) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Vehicle_owner SET name=?, address=?, mobile=? WHERE owner_nic=?",
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getOwnerNic()
        );
    }

    @Override
    public VehicleOwner get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Vehicle_Owner WHERE owner_nic=?", id);
        if (rst.next()) {
            return new VehicleOwner(
                    rst.getString("owner_nic"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getInt("mobile")
            );
        }
        return null;
    }

    @Override
    public ArrayList<VehicleOwner> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<VehicleOwner> search(String value) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Vehicle_Owner WHERE owner_nic LIKE '%" + value + "%' OR name LIKE '%" + value + "%' OR address LIKE '%" + value + "%' OR Mobile LIKE '%" + value + "%'");
        ArrayList<VehicleOwner> list = new ArrayList<>();
        while (rst.next()) {
            list.add(
                    new VehicleOwner(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }

}

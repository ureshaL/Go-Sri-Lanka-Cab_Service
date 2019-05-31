/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.VehicleDAO;
import lk.ijse.gosrilanka.entity.Vehicle;
import lk.ijse.gosrilanka.util.Constans;

/**
 *
 * @author Uresha Lakshani
 */
public class VehicleDAOImpl implements VehicleDAO{

    @Override
    public boolean add(Vehicle entity) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO Vehicle VALUES (?,?,?,?,?,?,?,?)",
                entity.getVehicleNo(),
                entity.getOwnershipType(),
                entity.getRegisteredDate(),
                entity.getInsuarenceExpireDate(),
                entity.getLicenceExpireDate(),
                entity.getStatus(),
                entity.getModelId(),
                entity.getOwnerNic()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM Vehicle WHERE vehicle_no = ?", 
                id
        );
    }

    @Override
    public boolean update(Vehicle entity) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Vehicle SET ownership_type=?, insuarence_expire_date=?, licence_expire_date=?, model_id=?, owner_nic=? WHERE vehicle_no=?",
                entity.getOwnershipType(),
                entity.getInsuarenceExpireDate(),
                entity.getLicenceExpireDate(),
                entity.getModelId(),
                entity.getOwnerNic(),
                entity.getVehicleNo()
        );
    }

    @Override
    public Vehicle get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Vehicle> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Vehicle> getVehiclesByModelId(int modelId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Vehicle WHERE model_id=? AND status=?", 
                modelId, Constans.StatusTypes.AVAILABLE
        );
        ArrayList<Vehicle> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Vehicle(
                    rst.getString("vehicle_no"), 
                    rst.getString("ownership_type"), 
                    rst.getString("registered_date"), 
                    rst.getString("insuarence_expire_date"), 
                    rst.getString("licence_expire_date"),
                    rst.getInt("status"),
                    rst.getInt("model_id"),
                    rst.getString("owner_nic")
            ));
        }
        return list;
    }

    @Override
    public boolean updateStatus(String vehicleNo, int status) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Vehicle SET status=? WHERE vehicle_no=?", 
                status, vehicleNo
        );
    }

    @Override
    public ArrayList<Vehicle> getVehiclesByStatus(int status) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Vehicle WHERE status=?",
                status
        );
        ArrayList<Vehicle> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Vehicle(
                    rst.getString("vehicle_no"), 
                    rst.getString("ownership_type"), 
                    rst.getString("registered_date"), 
                    rst.getString("insuarence_expire_date"), 
                    rst.getString("licence_expire_date"),
                    rst.getInt("status"),
                    rst.getInt("model_id"),
                    rst.getString("owner_nic")
            ));
        }
        return list;
    }

    @Override
    public int getDashboardVehicleCount() throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT COUNT(*) FROM Vehicle WHERE status=?",
                Constans.StatusTypes.AVAILABLE
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean updateLicenceDate(String vehicleNo, String newDate) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Vehicle SET licence_expire_date=? WHERE vehicle_no=?",
                newDate, vehicleNo
        );
    }

    @Override
    public boolean updateInsuranceDate(String vehicleNo, String newDate) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Vehicle SET insuarence_expire_date=? WHERE vehicle_no=?",
                newDate, vehicleNo
        );
    }
    
}

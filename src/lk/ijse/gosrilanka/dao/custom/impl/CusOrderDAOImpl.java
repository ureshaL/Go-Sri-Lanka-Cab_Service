/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.CusOrderDAO;
import lk.ijse.gosrilanka.entity.CusOrder;
import lk.ijse.gosrilanka.util.Constans;

/**
 *
 * @author Uresha Lakshani
 */
public class CusOrderDAOImpl implements CusOrderDAO{

    @Override
    public int getNextId() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'Cus_Order'");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean add(CusOrder entity) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO Cus_Order (order_date,journey_date,no_of_days,price_per_km,driver_price_per_day,customer_nic,vehicle_no,licence_no,status,km_per_day) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)",
                entity.getOrderDate(),
                entity.getJourneyDate(),
                entity.getNoOfDays(),
                entity.getPricePerKm(),
                entity.getDriverPricePerDay(),
                entity.getCustomerNic(),
                entity.getVehicleNo(),
                entity.getLicenceNo(),
                entity.getStatus(),
                entity.getKmPerDay()
        );
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM Cus_Order WHERE order_id=?", 
                id
        );
    }

    @Override
    public boolean update(CusOrder entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CusOrder get(Integer id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Cus_Order WHERE order_id=?",
                id);
        if (rst.next()) {
            return new CusOrder(
                    rst.getInt("order_id"),
                    rst.getString("order_date"), 
                    rst.getString("journey_date"),
                    rst.getInt("no_of_days"), 
                    rst.getString("journey_date"), 
                    rst.getDouble("price_per_km"),
                    rst.getDouble("driver_price_per_day"),
                    rst.getString("customer_nic"), 
                    rst.getString("vehicle_no"), 
                    rst.getString("licence_no"), 
                    rst.getInt("status"), 
                    rst.getInt("km_per_day"), 
                    rst.getInt("km_spent")
            );
        }
        return null;
    }

    @Override
    public ArrayList<CusOrder> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDashboardRentCount() throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT COUNT(*) FROM Cus_Order WHERE YEAR(order_date)=YEAR(CURDATE()) AND MONTH(order_date)=MONTH(CURDATE())"
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean finishOrder(int orderId, String returnDate, int kmSpent) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Cus_Order SET return_date=?, km_spent=?, status=? WHERE order_id=?",
                returnDate,
                kmSpent,
                Constans.StatusTypes.NOT_AVAILABLE,
                orderId
        );
    }
    
}

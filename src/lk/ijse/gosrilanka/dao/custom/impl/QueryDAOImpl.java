/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.QueryDAO;
import lk.ijse.gosrilanka.dto.CustomDTO;

/**
 *
 * @author Uresha Lakshani
 */
public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDTO> searchModels(String value) throws Exception {
        String sql = "SELECT model_id,model_name,no_of_seats,price_per_km,deposit_amount,MK.make_id,make_name,C.category_id,category_name\n"
                + "FROM Model MD, Category C, Make MK\n"
                + "WHERE MD.make_id=MK.make_id AND MD.category_id=C.category_id AND\n"
                + "(model_id='" + value + "' OR model_name LIKE '%" + value + "%' OR category_name LIKE '%" + value + "%' OR make_name LIKE '%" + value + "%')";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setModelId(rst.getInt("model_id"));
            c.setModelName(rst.getString("model_name"));
            c.setNoOfseats(rst.getInt("no_of_seats"));
            c.setPricePerKm(rst.getDouble("price_per_km"));
            c.setDepositAmount(rst.getDouble("deposit_amount"));
            c.setMakeId(rst.getInt("make_id"));
            c.setMakeName(rst.getString("make_name"));
            c.setCategoryId(rst.getInt("category_id"));
            c.setCategoryName(rst.getString("category_name"));
            list.add(c);
        }
        return list;
    }

    @Override
    public ArrayList<CustomDTO> searchVehicles(String value) throws Exception {
        String sql = "SELECT vehicle_no, ownership_type, V.owner_nic, name, C.category_id, C.category_name, MK.make_id, make_name, MD.model_id, model_name, registered_date, insuarence_expire_date, licence_expire_date\n"
                + "FROM Vehicle V\n"
                + "LEFT JOIN Vehicle_Owner VO ON V.owner_nic=VO.owner_nic LEFT JOIN Model MD ON V.model_id=MD.model_id LEFT JOIN Make MK ON MD.make_id=MK.make_id LEFT JOIN Category C ON MD.category_id=C.category_id\n"
                + "WHERE (vehicle_no LIKE '" + value + "%' OR ownership_type = '" + value + "' OR V.owner_nic = '" + value + "' OR category_name LIKE '%" + value + "%' OR make_name LIKE '%" + value + "%' OR model_name LIKE '%" + value + "%')";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setOwnershipType(rst.getString("ownership_type"));
            c.setOwnerNic(rst.getString("owner_nic") == null ? "---------" : rst.getString("owner_nic"));
            c.setOwnerName(rst.getString("name") == null ? "---------" : rst.getString("name"));
            c.setModelId(rst.getInt("model_id"));
            c.setModelName(rst.getString("model_name"));
            c.setMakeId(rst.getInt("make_id"));
            c.setMakeName(rst.getString("make_name"));
            c.setCategoryId(rst.getInt("category_id"));
            c.setCategoryName(rst.getString("category_name"));
            c.setRegisteredDate(rst.getString("registered_date"));
            c.setInsuarenceExpireDate(rst.getString("insuarence_expire_date"));
            c.setLicenceExpireDate(rst.getString("licence_expire_date"));
            list.add(c);
        }
        return list;

    }

    @Override
    public ArrayList<CustomDTO> searchCusOrders(String value) throws Exception {
        String sql = "SELECT \n"
                + "order_id,\n"
                + "vehicle_no, \n"
                + "order_date, \n"
                + "journey_date, \n"
                + "return_date, \n"
                + "no_of_days, \n"
                + "DATEDIFF(return_date,journey_date) as spent_days, \n"
                + "(DATEDIFF(return_date,journey_date) - no_of_days) as over_days, \n"
                + "km_spent,\n"
                + "C.name, \n"
                + "driver_name,\n"
                + "CASE \n"
                + "	WHEN km_spent < (DATEDIFF(return_date,journey_date)*km_per_day) THEN (DATEDIFF(return_date,journey_date)*km_per_day*price_per_km) + (driver_price_per_day*DATEDIFF(return_date,journey_date)) + (driver_price_per_day*DATEDIFF(return_date,journey_date))\n"
                + "    ELSE (km_spent*price_per_km) + (driver_price_per_day*DATEDIFF(return_date,journey_date))\n"
                + "END AS amount\n"
                + "FROM Cus_Order CO\n"
                + "LEFT JOIN Customer C ON CO.customer_nic=C.customer_nic\n"
                + "LEFT JOIN Driver D ON CO.licence_no=D.licence_no\n"
                + "WHERE vehicle_no LIKE '" + value + "%'\n"
                + "ORDER BY order_id DESC";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setOrderId(rst.getInt("order_id"));
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setOrderDate(rst.getString("order_date"));
            c.setJourneyDate(rst.getString("journey_date"));
            c.setReturnDate(rst.getString("return_date"));
            c.setNoOfDays(rst.getInt("no_of_days"));
            c.setSpentDays(rst.getInt("spent_days"));
            c.setOverDays(rst.getInt("over_days"));
            c.setKmSpent(rst.getInt("km_spent"));
            c.setCustomerName(rst.getString("name"));
            c.setDriverName(rst.getString("driver_name"));
            c.setAmount(rst.getDouble("amount"));
            list.add(c);
        }
        return list;
    }

    @Override
    public ArrayList<CustomDTO> getPendingOrders() throws Exception {
        String sql = "SELECT V.vehicle_no,\n" +
            " D.driver_name,\n" +
            " C.name,\n" +
            " Co.order_id,\n" +
            " CO.journey_date\n" +
            " FROM Cus_order CO\n" +
            " LEFT JOIN Customer C ON CO.customer_nic = C.customer_nic\n" +
            " LEFT JOIN Driver D ON CO.licence_no = D.licence_no\n" +
            " LEFT JOIN Vehicle V ON CO.vehicle_no = V.vehicle_no\n" +
            " WHERE journey_date > curdate()\n" +
            " ORDER BY journey_date ASC";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setOrderId(rst.getInt("order_id"));
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setDriverName(rst.getString("driver_name"));
            c.setCustomerName(rst.getString("name"));
            c.setJourneyDate(rst.getString("journey_date"));
            list.add(c);
        }
        return list;
    }
        
    @Override
    public ArrayList<CustomDTO> getOnGoingOrders() throws Exception {
        String sql = "SELECT\n"
                + "order_id,\n"
                + "vehicle_no,\n"
                + "driver_name,\n"
                + "name as customer_name,\n"
                + "journey_date\n"
                + "FROM Cus_Order CO\n"
                + "LEFT JOIN Customer C ON CO.customer_nic=C.customer_nic\n"
                + "LEFT JOIN Driver D on CO.licence_no=D.licence_no\n"
                + "WHERE CO.status='0' AND CURDATE() >= journey_date";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();            
            c.setOrderId(rst.getInt("order_id"));
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setDriverName(rst.getString("driver_name"));
            c.setCustomerName(rst.getString("customer_name"));
            c.setJourneyDate(rst.getString("journey_date"));
            list.add(c);
        }
        return list;
    }

    @Override
    public ArrayList<CustomDTO> getAlerts() throws Exception {
        String sql = "(\n"
                + "	SELECT\n"
                + "	vehicle_no,\n"
                + "	'Licence' as alert_type,\n"
                + "	licence_expire_date as due_date,\n"
                + "    DATEDIFF(licence_expire_date,CURDATE()) days_left\n"
                + "	FROM Vehicle\n"
                + "    HAVING days_left <= 7\n"
                + ")\n"
                + "UNION ALL\n"
                + "(\n"
                + "	SELECT\n"
                + "	vehicle_no,\n"
                + "	'Insurance' as alert_type,\n"
                + "	insuarence_expire_date as due_date,\n"
                + "    DATEDIFF(insuarence_expire_date,CURDATE()) days_left\n"
                + "	FROM Vehicle\n"
                + "    HAVING days_left <= 7\n"
                + ")\n"
                + "ORDER BY vehicle_no";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setAlertType(rst.getString("alert_type"));
            c.setDueDate(rst.getString("due_date"));
            c.setDaysLeft(rst.getInt("days_left"));
            list.add(c);
        }
        return list;
    }

    @Override
    public ArrayList<CustomDTO> getAvailableNonImmediateVehicle() throws Exception {
        String sql = "SELECT vehicle_no, category_name, make_name, model_name\n"
                + "FROM Vehicle V\n"
                + "LEFT JOIN Model MD ON V.model_id=MD.model_id\n"
                + "LEFT JOIN Category C ON MD.category_id=C.category_id\n"
                + "LEFT JOIN MAKE MK ON MD.make_id=MK.make_id\n"
                + "WHERE status='0' AND ownership_type!='Immediate'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setCategoryName(rst.getString("category_name"));
            c.setMakeName(rst.getString("make_name"));
            c.setModelName(rst.getString("model_name"));
            list.add(c);
        }
        return list;
    }

    @Override
    public ArrayList<CustomDTO> getAvailableImmediateVehicle() throws Exception {
        String sql = "SELECT vehicle_no, category_name, make_name, model_name, owner_nic\n"
                + "FROM Vehicle V\n"
                + "LEFT JOIN Model MD ON V.model_id=MD.model_id\n"
                + "LEFT JOIN Category C ON MD.category_id=C.category_id\n"
                + "LEFT JOIN MAKE MK ON MD.make_id=MK.make_id\n"
                + "WHERE status='0' AND ownership_type='Immediate'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomDTO> list = new ArrayList<>();
        while (rst.next()) {
            CustomDTO c = new CustomDTO();
            c.setVehicleNo(rst.getString("vehicle_no"));
            c.setCategoryName(rst.getString("category_name"));
            c.setMakeName(rst.getString("make_name"));
            c.setModelName(rst.getString("model_name"));
            c.setOwnerNic(rst.getString("owner_nic"));
            list.add(c);
        }
        return list;
    }

}

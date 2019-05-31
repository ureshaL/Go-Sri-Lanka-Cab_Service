/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.ModelDAO;
import lk.ijse.gosrilanka.entity.Model;

/**
 *
 * @author Uresha Lakshani
 */
public class ModelDAOImpl implements ModelDAO{

    @Override
    public int getNextId() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'Model'");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean add(Model entity) throws Exception {
        return CrudUtil.executeUpdate(""
                + "INSERT INTO Model "
                + "(model_name,no_of_seats,price_per_km,deposit_amount,make_id,category_id) "
                + "VALUES (?,?,?,?,?,?)", 
                entity.getModelName(),
                entity.getNoOfseats(),
                entity.getPricePerKm(),
                entity.getDepositAmount(),
                entity.getMakeId(),
                entity.getCategoryId()
        );
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Model WHERE model_id=?", id);
    }

    @Override
    public boolean update(Model entity) throws Exception {
        return CrudUtil.executeUpdate(""
                + "UPDATE Model "
                + "SET model_name=?, no_of_seats=?, price_per_km=?, deposit_amount=?, make_id=?, category_id=? WHERE model_id=?", 
                entity.getModelName(),
                entity.getNoOfseats(),
                entity.getPricePerKm(),
                entity.getDepositAmount(),
                entity.getMakeId(),
                entity.getCategoryId(),
                entity.getModelId()
        );
    }

    @Override
    public Model get(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Model> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Model> getModelsByCategoryAndMake(int categoryId, int makeId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Model\n" +
                "WHERE category_id=? AND make_id=?", 
                categoryId, makeId
        );
        ArrayList<Model> list = new ArrayList<>();
        while(rst.next()){
            list.add(new Model(
                    rst.getInt("model_id"), 
                    rst.getString("model_name"), 
                    rst.getInt("no_of_seats"), 
                    rst.getDouble("price_per_km"), 
                    rst.getDouble("deposit_amount"), 
                    rst.getInt("make_id"), 
                    rst.getInt("category_id")
            ));
        }
        return list;
    }
    
}

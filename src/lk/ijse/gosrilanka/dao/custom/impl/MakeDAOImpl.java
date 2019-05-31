/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.MakeDAO;
import lk.ijse.gosrilanka.entity.Make;

/**
 *
 * @author Uresha Lakshani
 */
public class MakeDAOImpl implements MakeDAO{

    @Override
    public boolean add(Make entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Make(make_name) VALUES(?)", entity.getMakeName());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Make WHERE make_id=?", id);
    }

    @Override
    public boolean update(Make entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Make SET make_name=? WHERE make_id=?", entity.getMakeName(), entity.getMakeId());
    }

    @Override
    public Make get(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Make> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'Make'");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public ArrayList<Make> search(String value) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Make WHERE make_id='"+value+"' OR make_name LIKE '%"+value+"%'");
        ArrayList<Make> list = new ArrayList<>();
        while(rst.next()){
            list.add(new Make(rst.getInt(1), rst.getString(2)));
        }
        return list;
    }

    @Override
    public ArrayList<Make> getMakesByCategory(int categoryId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Make\n" +
                "WHERE make_id IN (SELECT make_id FROM Model WHERE category_id=?)",
                categoryId
        );
        ArrayList<Make> list = new ArrayList<>();
        while(rst.next()){
            list.add(new Make(rst.getInt(1), rst.getString(2)));
        }
        return list;
    }

    @Override
    public Make getMakeByModelId(int modelId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Make \n" +
                "WHERE make_id = (SELECT make_id FROM Model WHERE model_id=?)", 
                modelId
        );
        if (rst.next()) {
            return new Make(rst.getInt(1), rst.getString(2));
        }
        return null;
    }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.CategaryDAO;
import lk.ijse.gosrilanka.entity.Category;

/**
 *
 * @author Uresha Lakshani
 */
public class CategaryDAOImpl implements CategaryDAO {

    @Override
    public int getNextId() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'Category'");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public ArrayList<Category> search(String value) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Category WHERE category_id='"+value+"' OR category_name LIKE '%"+value+"%'");
        ArrayList<Category> list = new ArrayList<>();
        while(rst.next()){
            list.add(new Category(rst.getInt(1), rst.getString(2)));
        }
        return list;
    }

    @Override
    public boolean add(Category entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Category(category_name) VALUES(?)", entity.getCategoryName());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Category WHERE category_id=?", id);
    }

    @Override
    public boolean update(Category entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Category SET category_name=? WHERE category_id=?", entity.getCategoryName(), entity.getCategoryId());
    }

    @Override
    public Category get(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Category> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getCategoryByModelId(int modelId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Category \n" +
                        "WHERE category_id = (SELECT category_id FROM Model WHERE model_id=?)",
                modelId
        );
        if (rst.next()) {
            return new Category(rst.getInt(1), rst.getString(2));
        }
        return null;
    }

}

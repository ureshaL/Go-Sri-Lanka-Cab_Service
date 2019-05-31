/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudUtil;
import lk.ijse.gosrilanka.dao.custom.ConfigDAO;
import lk.ijse.gosrilanka.entity.Config;

/**
 *
 * @author Uresha Lakshani
 */
public class ConfigDAOImpl implements ConfigDAO{

    @Override
    public boolean add(Config entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Config entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Config get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Config WHERE config_id=?",
                id
        );
        if (rst.next()) {
            return new Config(rst.getString("config_id"), rst.getString("value"));
        }
        return null;
    }

    @Override
    public ArrayList<Config> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

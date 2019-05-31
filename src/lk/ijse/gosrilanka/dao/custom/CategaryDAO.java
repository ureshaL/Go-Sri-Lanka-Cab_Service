/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.Category;

/**
 *
 * @author Uresha Lakshani
 */
public interface CategaryDAO extends CrudDAO<Category, Integer>{
    public int getNextId() throws Exception;
    public ArrayList<Category> search(String value) throws Exception;
    public Category getCategoryByModelId(int modelId) throws Exception;
}

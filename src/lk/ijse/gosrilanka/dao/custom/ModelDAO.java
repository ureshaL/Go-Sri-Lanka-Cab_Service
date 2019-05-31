/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.Model;

/**
 *
 * @author Uresha Lakshani
 */
public interface ModelDAO extends CrudDAO<Model, Integer>{
    public int getNextId() throws Exception;
    public ArrayList<Model> getModelsByCategoryAndMake(int categoryId, int makeId) throws Exception;
}

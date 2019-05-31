/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.Make;

/**
 *
 * @author Uresha Lakshani
 */
public interface MakeDAO extends CrudDAO<Make, Integer>{
    public int getNextId() throws Exception;
    public ArrayList<Make> search(String value) throws Exception;
    public ArrayList<Make> getMakesByCategory(int categoryId) throws Exception;
    public Make getMakeByModelId(int modelId)throws Exception;
}

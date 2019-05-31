/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface ModelBO extends SuperBO{
    public boolean addModel(ModelDTO modelDTO) throws Exception;
    public boolean updateModel(ModelDTO modelDTO) throws Exception;
    public boolean deleteModel(int id) throws Exception;
    public int getNextId() throws Exception;
    public ArrayList<CustomDTO> search(String value) throws Exception;
    public ArrayList<ModelDTO> getModelsByCategoryAndMake(int categoryId, int makeId) throws Exception;
}

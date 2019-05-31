/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.CategoryDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface CategaryBO extends SuperBO{
    public boolean addCategary(CategoryDTO categaryDTO) throws Exception;
    public boolean updateCategary(CategoryDTO categaryDTO) throws Exception;
    public boolean deleteCategary(int id) throws Exception;
    public int getNextId() throws Exception;
    public ArrayList<CategoryDTO> search(String value) throws Exception;
    public CategoryDTO getCategoryByModelId(int modelId) throws Exception;
}

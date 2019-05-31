/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.CategaryBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.CategaryDAO;
import lk.ijse.gosrilanka.dto.CategoryDTO;
import lk.ijse.gosrilanka.entity.Category;

/**
 *
 * @author Uresha Lakshani
 */
public class CategaryBOImpl implements CategaryBO{
    private CategaryDAO categaryDAO;

    public CategaryBOImpl() {
        categaryDAO = (CategaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGARY);
    }
    
    

    @Override
    public boolean addCategary(CategoryDTO categaryDTO) throws Exception {
        return categaryDAO.add(new Category(categaryDTO.getCategoryName()));
    }

    @Override
    public boolean updateCategary(CategoryDTO categaryDTO) throws Exception {
        return categaryDAO.update(new Category(categaryDTO.getCategoryId(), categaryDTO.getCategoryName()));
    }

    @Override
    public boolean deleteCategary(int id) throws Exception {
        return categaryDAO.delete(id);
    }

    @Override
    public int getNextId() throws Exception {
        return categaryDAO.getNextId();
    }

    @Override
    public ArrayList<CategoryDTO> search(String value) throws Exception {
        ArrayList<Category> search = categaryDAO.search(value);
        ArrayList<CategoryDTO> list = new ArrayList<>();
        for (Category category : search) {
            list.add(new CategoryDTO(category.getCategoryId(), category.getCategoryName()));
        }
        return list;
    }

    @Override
    public CategoryDTO getCategoryByModelId(int modelId) throws Exception {
        Category categoryByModelId = categaryDAO.getCategoryByModelId(modelId);
        return new CategoryDTO(categoryByModelId.getCategoryId(), categoryByModelId.getCategoryName());
    }
    
}

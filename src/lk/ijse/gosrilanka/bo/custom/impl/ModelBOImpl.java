/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.ModelBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.ModelDAO;
import lk.ijse.gosrilanka.dao.custom.QueryDAO;
import lk.ijse.gosrilanka.dto.CustomDTO;
import lk.ijse.gosrilanka.dto.ModelDTO;
import lk.ijse.gosrilanka.entity.Model;

/**
 *
 * @author Uresha Lakshani
 */
public class ModelBOImpl implements ModelBO{
    
    private ModelDAO modelDAO;
    private QueryDAO queryDAO;

    public ModelBOImpl() {
        modelDAO = (ModelDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MODEL);
        queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
    }

    @Override
    public boolean addModel(ModelDTO modelDTO) throws Exception {
        return modelDAO.add(
                new Model(
                        modelDTO.getModelName(), 
                        modelDTO.getNoOfseats(),
                        modelDTO.getPricePerKm(),
                        modelDTO.getDepositAmount(),
                        modelDTO.getMakeId(),
                        modelDTO.getCategoryId()
                )
        );
    }

    @Override
    public boolean updateModel(ModelDTO modelDTO) throws Exception {
        return modelDAO.update(
                new Model(
                        modelDTO.getModelId(),
                        modelDTO.getModelName(), 
                        modelDTO.getNoOfseats(),
                        modelDTO.getPricePerKm(),
                        modelDTO.getDepositAmount(),
                        modelDTO.getMakeId(),
                        modelDTO.getCategoryId()
                )
        );
    }

    @Override
    public boolean deleteModel(int id) throws Exception {
        return modelDAO.delete(id);
    }

    @Override
    public int getNextId() throws Exception {
        return modelDAO.getNextId();
    }

    @Override
    public ArrayList<CustomDTO> search(String value) throws Exception {
        return queryDAO.searchModels(value);
    }

    @Override
    public ArrayList<ModelDTO> getModelsByCategoryAndMake(int categoryId, int makeId) throws Exception {
        ArrayList<Model> modelsByCategoryAndMake = modelDAO.getModelsByCategoryAndMake(categoryId, makeId);
        ArrayList<ModelDTO> list = new ArrayList<>();
        for (Model model : modelsByCategoryAndMake) {
            list.add(new ModelDTO(
                    model.getModelId(),
                    model.getModelName(),
                    model.getNoOfseats(), 
                    model.getPricePerKm(), 
                    model.getDepositAmount(),
                    model.getMakeId(), 
                    model.getCategoryId()
            ));
        }
        return list;
    }
    
}

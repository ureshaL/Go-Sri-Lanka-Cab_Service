/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import lk.ijse.gosrilanka.bo.custom.ConfigBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.ConfigDAO;
import lk.ijse.gosrilanka.dto.ConfigDTO;
import lk.ijse.gosrilanka.entity.Config;

/**
 *
 * @author Uresha Lakshani
 */
public class ConfigBOImpl implements ConfigBO{
    
    private ConfigDAO configDAO;

    public ConfigBOImpl() {
        configDAO = (ConfigDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CONFIG);
    }

    @Override
    public ConfigDTO getConfigValue(String configId) throws Exception {
        Config get = configDAO.get(configId);
        return new ConfigDTO(get.getConfigId(), get.getValue());
    }
    
}

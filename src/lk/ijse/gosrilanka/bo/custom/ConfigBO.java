/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.ConfigDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface ConfigBO extends SuperBO{
    public ConfigDTO getConfigValue(String configId) throws Exception;
}

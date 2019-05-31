/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.MakeDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface MakeBO extends SuperBO{
    public boolean addMake(MakeDTO makeDTO) throws Exception;
    public boolean updateMake(MakeDTO makeDTO) throws Exception;
    public boolean deleteMake(int id) throws Exception;
    public int getNextId() throws Exception;
    public ArrayList<MakeDTO> search(String value) throws Exception;
    public ArrayList<MakeDTO> getMakesByCategory(int categoryId) throws Exception;
    public MakeDTO getMakeByModelId(int modelId)throws Exception;
}

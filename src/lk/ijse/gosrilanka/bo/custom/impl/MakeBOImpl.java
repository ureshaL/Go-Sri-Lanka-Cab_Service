/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.MakeBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.MakeDAO;
import lk.ijse.gosrilanka.dto.MakeDTO;
import lk.ijse.gosrilanka.entity.Make;

/**
 *
 * @author Uresha Lakshani
 */
public class MakeBOImpl implements MakeBO{
    private MakeDAO makeDAO;

    public MakeBOImpl() {
        makeDAO = (MakeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MAKE);
    }
    
    @Override
    public boolean addMake(MakeDTO makeDTO) throws Exception {
        return makeDAO.add(new Make(makeDTO.getMakeName()));
    }

    @Override
    public int getNextId() throws Exception {
        return makeDAO.getNextId();
    }

    @Override
    public ArrayList<MakeDTO> search(String value) throws Exception {
        ArrayList<Make> search = makeDAO.search(value);
        ArrayList<MakeDTO> list = new ArrayList<>();
        for (Make make : search) {
            list.add(new MakeDTO(make.getMakeId(), make.getMakeName()));
        }
        return list;
    }

    @Override
    public boolean updateMake(MakeDTO makeDTO) throws Exception {
        return makeDAO.update(new Make(makeDTO.getMakeId(), makeDTO.getMakeName()));
    }

    @Override
    public boolean deleteMake(int id) throws Exception {
        return makeDAO.delete(id);
    }

    @Override
    public ArrayList<MakeDTO> getMakesByCategory(int categoryId) throws Exception {
        ArrayList<Make> makesByCategory = makeDAO.getMakesByCategory(categoryId);
        ArrayList<MakeDTO> list = new ArrayList<>();
        for (Make make : makesByCategory) {
            list.add(new MakeDTO(make.getMakeId(), make.getMakeName()));
        }
        return list;
    }

    @Override
    public MakeDTO getMakeByModelId(int modelId) throws Exception {
        Make makeByModelId = makeDAO.getMakeByModelId(modelId);
        return new MakeDTO(makeByModelId.getMakeId(), makeByModelId.getMakeName());
    }
    
}

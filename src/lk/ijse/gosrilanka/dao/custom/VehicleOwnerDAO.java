/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.VehicleOwner;

/**
 *
 * @author Uresha Lakshani
 */
public interface VehicleOwnerDAO extends CrudDAO<VehicleOwner, String>{
    public ArrayList<VehicleOwner> search(String value) throws Exception;
}

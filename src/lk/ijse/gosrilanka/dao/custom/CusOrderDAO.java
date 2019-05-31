/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao.custom;

import lk.ijse.gosrilanka.dao.CrudDAO;
import lk.ijse.gosrilanka.entity.CusOrder;

/**
 *
 * @author Uresha Lakshani
 */
public interface CusOrderDAO extends CrudDAO<CusOrder, Integer>{
    public int getNextId() throws Exception;
    public int getDashboardRentCount() throws Exception;
    public boolean finishOrder(int orderId, String returnDate, int kmSpent) throws Exception;
}

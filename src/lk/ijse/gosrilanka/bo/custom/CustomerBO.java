/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.SuperBO;
import lk.ijse.gosrilanka.dto.CustomerDTO;

/**
 *
 * @author Uresha Lakshani
 */
public interface CustomerBO extends SuperBO{
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception;
    public CustomerDTO getCustomer(String customerNic) throws Exception;
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;
    public boolean deleteCustomer(String id) throws Exception;
    public ArrayList<CustomerDTO> search(String value) throws Exception;
    public int getDashboardCustomerCount() throws Exception;
}

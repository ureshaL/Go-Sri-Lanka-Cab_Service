/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.bo.custom.impl;

import java.util.ArrayList;
import lk.ijse.gosrilanka.bo.custom.CustomerBO;
import lk.ijse.gosrilanka.dao.DAOFactory;
import lk.ijse.gosrilanka.dao.custom.CustomerDAO;
import lk.ijse.gosrilanka.dto.CustomerDTO;
import lk.ijse.gosrilanka.entity.Customer;

/**
 *
 * @author Uresha Lakshani
 */
public class CustomerBOImpl implements CustomerBO{
    
    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    }
    
    

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.add(
                new Customer(
                        customerDTO.getCustomerNic(),
                        customerDTO.getLicenceNo(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getMobile()
                )
        );
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(
                new Customer(
                        customerDTO.getCustomerNic(),
                        customerDTO.getLicenceNo(), 
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getMobile()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> search(String value) throws Exception {
        ArrayList<Customer> search = customerDAO.search(value);
        ArrayList<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : search) {
            list.add(
                    new CustomerDTO(
                            customer.getCustomerNic(),
                            customer.getLicenceNo(),
                            customer.getName(), 
                            customer.getAddress(),
                            customer.getMobile()
                    )
            );
        }
        return list;
    }

    @Override
    public CustomerDTO getCustomer(String customerNic) throws Exception {
        Customer customer = customerDAO.get(customerNic);
        if (customer != null) {
            return new CustomerDTO(
                    customer.getCustomerNic(),
                    customer.getLicenceNo(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getMobile()
            );
        }
        return null;
    }

    @Override
    public int getDashboardCustomerCount() throws Exception {
        return customerDAO.getDashboardCustomerCount();
    }
    
}

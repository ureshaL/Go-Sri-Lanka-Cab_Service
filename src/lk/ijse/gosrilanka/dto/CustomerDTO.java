/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dto;

/**
 *
 * @author Uresha Lakshani
 */
public class CustomerDTO {
    private String customerNic;
    private String licenceNo;
    private String name;
    private String address;
    private int mobile;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerNic, String licenceNo, String name, String address, int mobile) {
        this.customerNic = customerNic;
        this.licenceNo = licenceNo;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerNic=" + customerNic + ", licenceNo=" + licenceNo + ", name=" + name + ", address=" + address + ", mobile=" + mobile + '}';
    }
    
}

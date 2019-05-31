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
public class VehicleOwnerDTO {
    private String ownerNic;
    private String name;
    private String address;
    private int mobile;

    public VehicleOwnerDTO() {
    }

    public VehicleOwnerDTO(String ownerNic, String name, String address, int mobile) {
        this.ownerNic = ownerNic;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public String getOwnerNic() {
        return ownerNic;
    }

    public void setOwnerNic(String ownerNic) {
        this.ownerNic = ownerNic;
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
        return "VehicleOwner{" + "ownerNic=" + ownerNic + ", name=" + name + ", address=" + address + ", mobile=" + mobile + '}';
    }
    
}

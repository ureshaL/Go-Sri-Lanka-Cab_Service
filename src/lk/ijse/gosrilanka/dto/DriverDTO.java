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
public class DriverDTO {
    private String licenceNo;
    private String driverName;
    private String driverAddress;
    private int driverMobile;
    private int status;

    public DriverDTO() {
    }

    public DriverDTO(String licenceNo, String driverName, String driverAddress, int driverMobile, int status) {
        this.licenceNo = licenceNo;
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverMobile = driverMobile;
        this.status = status;
    }

    public DriverDTO(String licenceNo, String driverName, String driverAddress, int driverMobile) {
        this.licenceNo = licenceNo;
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverMobile = driverMobile;
    }
    
    

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public int getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(int driverMobile) {
        this.driverMobile = driverMobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return driverName;
    }
    
}

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
public class CusOrderDTO {
    private int orderId;
    private String orderDate;
    private String journeyDate;
    private int noOfDays;
    private String returnDate;
    private double pricePerKm;
    private double driverPricePerDay;
    private String customerNic;
    private String vehicleNo;
    private String licenceNo;
    private int status;
    private int kmPerDay;
    private int kmSpent;

    public CusOrderDTO() {
    }

    //Insert Constructor
    public CusOrderDTO(String orderDate, String journeyDate, int noOfDays, double pricePerKm, double driverPricePerDay, String customerNic, String vehicleNo, String licenceNo, int kmPerDay) {
        this.orderDate = orderDate;
        this.journeyDate = journeyDate;
        this.noOfDays = noOfDays;
        this.pricePerKm = pricePerKm;
        this.driverPricePerDay = driverPricePerDay;
        this.customerNic = customerNic;
        this.vehicleNo = vehicleNo;
        this.licenceNo = licenceNo;
        this.kmPerDay = kmPerDay;
    }
    
    //Fetch Constructor
    public CusOrderDTO(int orderId, String orderDate, String journeyDate, int noOfDays, String returnDate, double pricePerKm, double driverPricePerDay, String customerNic, String vehicleNo, String licenceNo, int status, int kmPerDay, int kmSpent) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.journeyDate = journeyDate;
        this.noOfDays = noOfDays;
        this.returnDate = returnDate;
        this.pricePerKm = pricePerKm;
        this.driverPricePerDay = driverPricePerDay;
        this.customerNic = customerNic;
        this.vehicleNo = vehicleNo;
        this.licenceNo = licenceNo;
        this.status = status;
        this.kmPerDay = kmPerDay;
        this.kmSpent = kmSpent;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getDriverPricePerDay() {
        return driverPricePerDay;
    }

    public void setDriverPricePerDay(double driverPricePerDay) {
        this.driverPricePerDay = driverPricePerDay;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getKmPerDay() {
        return kmPerDay;
    }

    public void setKmPerDay(int kmPerDay) {
        this.kmPerDay = kmPerDay;
    }

    public int getKmSpent() {
        return kmSpent;
    }

    public void setKmSpent(int kmSpent) {
        this.kmSpent = kmSpent;
    }

    @Override
    public String toString() {
        return "CusOrderDTO{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", journeyDate=" + journeyDate + ", noOfDays=" + noOfDays + ", returnDate=" + returnDate + ", pricePerKm=" + pricePerKm + ", driverPricePerDay=" + driverPricePerDay + ", customerNic=" + customerNic + ", vehicleNo=" + vehicleNo + ", licenceNo=" + licenceNo + ", status=" + status + ", kmPerDay=" + kmPerDay + ", kmSpent=" + kmSpent + '}';
    }
    
}

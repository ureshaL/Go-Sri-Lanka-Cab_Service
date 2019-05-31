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
public class VehicleDTO {
    private String vehicleNo;
    private String ownershipType;
    private String registeredDate;
    private String insuarenceExpireDate;
    private String licenceExpireDate;
    private int status;
    private int modelId;
    private String ownerNic;

    public VehicleDTO() {
    }
    
    public VehicleDTO(String vehicleNo, String ownershipType, String insuarenceExpireDate, String licenceExpireDate, int modelId) {
        this.vehicleNo = vehicleNo;
        this.ownershipType = ownershipType;
        this.insuarenceExpireDate = insuarenceExpireDate;
        this.licenceExpireDate = licenceExpireDate;
        this.modelId = modelId;
    }

    public VehicleDTO(String vehicleNo, String ownershipType, String registeredDate, String insuarenceExpireDate, String licenceExpireDate, int modelId) {
        this.vehicleNo = vehicleNo;
        this.ownershipType = ownershipType;
        this.registeredDate = registeredDate;
        this.insuarenceExpireDate = insuarenceExpireDate;
        this.licenceExpireDate = licenceExpireDate;
        this.modelId = modelId;
    }
    
    public VehicleDTO(String vehicleNo, String ownershipType, String insuarenceExpireDate, String licenceExpireDate, int modelId, String ownerNic) {
        this.vehicleNo = vehicleNo;
        this.ownershipType = ownershipType;
        this.insuarenceExpireDate = insuarenceExpireDate;
        this.licenceExpireDate = licenceExpireDate;
        this.modelId = modelId;
        this.ownerNic = ownerNic;
    }
    
    public VehicleDTO(String vehicleNo, String ownershipType, String registeredDate, String insuarenceExpireDate, String licenceExpireDate, int modelId, String ownerNic) {
        this.vehicleNo = vehicleNo;
        this.ownershipType = ownershipType;
        this.registeredDate = registeredDate;
        this.insuarenceExpireDate = insuarenceExpireDate;
        this.licenceExpireDate = licenceExpireDate;
        this.modelId = modelId;
        this.ownerNic = ownerNic;
    }
    
    public VehicleDTO(String vehicleNo, String ownershipType, String registeredDate, String insuarenceExpireDate, String licenceExpireDate, int status, int modelId, String ownerNic) {
        this.vehicleNo = vehicleNo;
        this.ownershipType = ownershipType;
        this.registeredDate = registeredDate;
        this.insuarenceExpireDate = insuarenceExpireDate;
        this.licenceExpireDate = licenceExpireDate;
        this.status = status;
        this.modelId = modelId;
        this.ownerNic = ownerNic;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getInsuarenceExpireDate() {
        return insuarenceExpireDate;
    }

    public void setInsuarenceExpireDate(String insuarenceExpireDate) {
        this.insuarenceExpireDate = insuarenceExpireDate;
    }

    public String getLicenceExpireDate() {
        return licenceExpireDate;
    }

    public void setLicenceExpireDate(String licenceExpireDate) {
        this.licenceExpireDate = licenceExpireDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getOwnerNic() {
        return ownerNic;
    }

    public void setOwnerNic(String ownerNic) {
        this.ownerNic = ownerNic;
    }

    @Override
    public String toString() {
        return vehicleNo;
    }

    
}

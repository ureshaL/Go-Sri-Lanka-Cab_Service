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
public class ModelDTO {
    private int modelId;
    private String modelName;
    private int noOfseats;
    private double pricePerKm;
    private double depositAmount;
    private int makeId;
    private int categoryId;

    public ModelDTO() {
    }

    public ModelDTO(String modelName, int noOfseats, double pricePerKm, double depositAmount, int makeId, int categoryId) {
        this.modelName = modelName;
        this.noOfseats = noOfseats;
        this.pricePerKm = pricePerKm;
        this.depositAmount = depositAmount;
        this.makeId = makeId;
        this.categoryId = categoryId;
    }
    
    public ModelDTO(int modelId, String modelName, int noOfseats, double pricePerKm, double depositAmount, int makeId, int categoryId) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.noOfseats = noOfseats;
        this.pricePerKm = pricePerKm;
        this.depositAmount = depositAmount;
        this.makeId = makeId;
        this.categoryId = categoryId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getNoOfseats() {
        return noOfseats;
    }

    public void setNoOfseats(int noOfseats) {
        this.noOfseats = noOfseats;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return modelName;
    }

    
    
}

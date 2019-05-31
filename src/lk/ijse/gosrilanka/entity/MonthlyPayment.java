/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.entity;

/**
 *
 * @author Uresha Lakshani
 */
public class MonthlyPayment {
    private int payId;
    private double amount;
    private String date;

    public MonthlyPayment() {
    }

    public MonthlyPayment(int payId, double amount, String date) {
        this.payId = payId;
        this.amount = amount;
        this.date = date;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MonthlyPayment{" + "payId=" + payId + ", amount=" + amount + ", date=" + date + '}';
    }
    
    
}

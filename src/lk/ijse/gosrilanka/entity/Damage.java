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
public class Damage {
    private int damageId;
    private String description;
    private int garageDays;

    public Damage() {
    }

    public Damage(int damageId, String description, int garageDays) {
        this.damageId = damageId;
        this.description = description;
        this.garageDays = garageDays;
    }

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGarageDays() {
        return garageDays;
    }

    public void setGarageDays(int garageDays) {
        this.garageDays = garageDays;
    }

    @Override
    public String toString() {
        return "Damage{" + "damageId=" + damageId + ", description=" + description + ", garageDays=" + garageDays + '}';
    }
    
}

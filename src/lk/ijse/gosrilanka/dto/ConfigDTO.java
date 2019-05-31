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
public class ConfigDTO {
    private String configId;
    private String value;

    public ConfigDTO() {
    }

    public ConfigDTO(String configId, String value) {
        this.configId = configId;
        this.value = value;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Config{" + "configId=" + configId + ", value=" + value + '}';
    }
    
}

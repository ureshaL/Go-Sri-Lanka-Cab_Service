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
public class Make {
    private int makeId;
    private String makeName;

    public Make() {
    }

    public Make(int makeId, String makeName) {
        this.makeId = makeId;
        this.makeName = makeName;
    }

    public Make(String makeName) {
        this.makeName = makeName;
    }   

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    @Override
    public String toString() {
        return "Make{" + "makeId=" + makeId + ", makeName=" + makeName + '}';
    }
    
    
}

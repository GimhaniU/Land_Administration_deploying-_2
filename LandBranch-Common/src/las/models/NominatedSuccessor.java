/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.models;

import java.io.Serializable;

/**
 *
 * @author Nuwantha
 */
public class NominatedSuccessor implements Serializable{
    private String N_RegNo;
    private String name;
    private String NIC_S;
    private String address;
    private String owner_reg_no;
    private double portion;

    public NominatedSuccessor(String N_RegNo, String name, String NIC_S, String address, String owner_reg_no, double portion) {
        this.N_RegNo = N_RegNo;
        this.name = name;
        this.NIC_S = NIC_S;
        this.address = address;
        this.owner_reg_no = owner_reg_no;
        this.portion = portion;
    }

    public String getN_RegNo() {
        return N_RegNo;
    }

    public void setN_RegNo(String N_RegNo) {
        this.N_RegNo = N_RegNo;
    }

    public String getOwner_reg_no() {
        return owner_reg_no;
    }

    public void setOwner_reg_no(String owner_reg_no) {
        this.owner_reg_no = owner_reg_no;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }
    
    
    
    public String getNIC_S() {
        return NIC_S;
    }

    public void setNIC_S(String NIC_S) {
        this.NIC_S = NIC_S;
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
    
}

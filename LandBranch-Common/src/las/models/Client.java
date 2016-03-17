/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author H.P. Asela
 */
public class Client implements Serializable{

    private int regNo;
    private String clientName;
    private String NIC; 
    private String landnumber;
    private String lotnumber;
    private String address;
    private double annualIncome;
    private int permitOwnershipPosition;
    private int grantOwnershipPosition;
    private int marriedStatus;
    private String spouseName;
    private int gender;
    private int numberOfMarriedSons;
    private int numberOfUnmarriedSons;
    private ArrayList<NominatedSuccessor> nominatedsuccessorlist;
    
    

    Client() {

    }

    public Client(int regNo, String clientName, String NIC, String landnumber, String lotnumber, String address, double annualIncome, int permitOwnershipPosition, int grantOwnershipPosition, int marriedStatus, String spouseName, int gender, int numberOfMarriedSons, int numberOfUnmarriedSons) {
        this.regNo = regNo;
        this.clientName = clientName;
        this.NIC = NIC;
        this.landnumber = landnumber;
        this.lotnumber = lotnumber;
        this.address = address;
        this.annualIncome = annualIncome;
        this.permitOwnershipPosition = permitOwnershipPosition;
        this.grantOwnershipPosition = grantOwnershipPosition;
        this.marriedStatus = marriedStatus;
        this.spouseName = spouseName;
        this.gender = gender;
        this.numberOfMarriedSons = numberOfMarriedSons;
        this.numberOfUnmarriedSons = numberOfUnmarriedSons;
    }

    public Client(int regNo, String clientName, String NIC, String landnumber, String lotnumber, String address, double annualIncome, int permitOwnershipPosition, int grantOwnershipPosition, int marriedStatus, String spouseName, int gender, int numberOfMarriedSons, int numberOfUnmarriedSons, ArrayList<NominatedSuccessor> nominatedsuccessorlist) {
        this.regNo = regNo;
        this.clientName = clientName;
        this.NIC = NIC;
        this.landnumber = landnumber;
        this.lotnumber = lotnumber;
        this.address = address;
        this.annualIncome = annualIncome;
        this.permitOwnershipPosition = permitOwnershipPosition;
        this.grantOwnershipPosition = grantOwnershipPosition;
        this.marriedStatus = marriedStatus;
        this.spouseName = spouseName;
        this.gender = gender;
        this.numberOfMarriedSons = numberOfMarriedSons;
        this.numberOfUnmarriedSons = numberOfUnmarriedSons;
        this.nominatedsuccessorlist = nominatedsuccessorlist;
    }

    public ArrayList<NominatedSuccessor> getNominatedsuccessorlist() {
        return nominatedsuccessorlist;
    }

    public void setNominatedsuccessorlist(ArrayList<NominatedSuccessor> nominatedsuccessorlist) {
        this.nominatedsuccessorlist = nominatedsuccessorlist;
    }
    
    

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public int isMarried() {
        return marriedStatus;
    }

    public void setMarriedStatus(int marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public int getNumberOfMarriedSons() {
        return numberOfMarriedSons;
    }

    public void setNumberOfMarriedSons(int numberOfMarriedSons) {
        this.numberOfMarriedSons = numberOfMarriedSons;
    }

    public int getNumberOfUnmarriedSons() {
        return numberOfUnmarriedSons;
    }

    public void setNumberOfUnmarriedSons(int numberOfUnmarriedSons) {
        this.numberOfUnmarriedSons = numberOfUnmarriedSons;
    }

    public String getAddress() {
        return address;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPermitOwnershipPosition() {
        return permitOwnershipPosition;
    }

    public void setPermitOwnershipPosition(int permitOwnershipPosition) {
        this.permitOwnershipPosition = permitOwnershipPosition;
    }

    public String getLandnumber() {
        return landnumber;
    }

    public void setLandnumber(String landnumber) {
        this.landnumber = landnumber;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGrantOwnershipPosition() {
        return grantOwnershipPosition;
    }

    public void setGrantOwnershipPosition(int grantOwnershipPosition) {
        this.grantOwnershipPosition = grantOwnershipPosition;
    }
}

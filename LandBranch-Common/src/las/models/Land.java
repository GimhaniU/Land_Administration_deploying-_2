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
 * @author Gimhani
 */
public class Land  implements Serializable{
    private String landnumber;
    private String landName;
    private String planNumber;
    private String divisionNumber;
    
    private int NumberOfLot;
    private ArrayList<Lot> lotList;

    public Land(String landnumber, String landName, String planNumber, String divisionNumber) {
        this.landnumber = landnumber;
        this.landName = landName;
        this.planNumber = planNumber;
        this.divisionNumber = divisionNumber;
    }

    public Land(String landnumber, String landName, String planNumber, String divisionNumber, int NumberOfLot) {
        this.landnumber = landnumber;
        this.landName = landName;
        this.planNumber = planNumber;
        this.divisionNumber = divisionNumber;
        this.NumberOfLot = NumberOfLot;
    }

    public Land(String landnumber, String landName, String planNumber, String divisionNumber, ArrayList<Lot> lotList) {
        this.landnumber = landnumber;
        this.landName = landName;
        this.planNumber = planNumber;
        this.divisionNumber = divisionNumber;
        this.lotList = lotList;
    }

    public String getLandnumber() {
        return landnumber;
    }

    public void setLandnumber(String landnumber) {
        this.landnumber = landnumber;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getDivisionNumber() {
        return divisionNumber;
    }

    public void setDivisionNumber(String divisionNumber) {
        this.divisionNumber = divisionNumber;
    }

    public int getNumberOfLot() {
        return NumberOfLot;
    }

    public void setNumberOfLot(int NumberOfLot) {
        this.NumberOfLot = NumberOfLot;
    }

    public ArrayList<Lot> getLotList() {
        return lotList;
    }

    public void setLotList(ArrayList<Lot> lotList) {
        this.lotList = lotList;
    }
    
    
    
}

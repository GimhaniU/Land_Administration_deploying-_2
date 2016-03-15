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
public class Lot implements Serializable {
    private String landNumber;
    private String lotNumber;
    private int numberOfAcres;
    private int numberofRoods;
    private int numberOfPerches;
    private String permitNumber;
    private String permitIssueDate;
    private int is_p_certified=0;
    private String grantNumber;
    private String grantIssueDate;
    
    
    private Client client;
    private Land land;

    public Lot(String landNumber, String lotNumber, int numberOfAcres, int numberofRoods, int numberOfPerches, String permitNumber, String permitIssueDate, String grantNumber, String grantIssueDate, Client client, Land land) {
        this.landNumber = landNumber;
        this.lotNumber = lotNumber;
        this.numberOfAcres = numberOfAcres;
        this.numberofRoods = numberofRoods;
        this.numberOfPerches = numberOfPerches;
        this.permitNumber = permitNumber;
        this.permitIssueDate = permitIssueDate;
        this.grantNumber = grantNumber;
        this.grantIssueDate = grantIssueDate;
        this.client = client;
        this.land = land;
    }

    
    
    public Lot(String landNumber, String lotNumber, int numberOfAcres, int numberofRoods, int numberOfPerches) {
        this.landNumber = landNumber;
        this.lotNumber = lotNumber;
        this.numberOfAcres = numberOfAcres;
        this.numberofRoods = numberofRoods;
        this.numberOfPerches = numberOfPerches;
    }

    public Lot(String landNumber, String lotNumber, int numberOfAcres, int numberofRoods, int numberOfPerches, String permitNumber, String permitIssueDate, String grantNumber, String grantIssueDate) {
        this.landNumber = landNumber;
        this.lotNumber = lotNumber;
        this.numberOfAcres = numberOfAcres;
        this.numberofRoods = numberofRoods;
        this.numberOfPerches = numberOfPerches;
        this.permitNumber = permitNumber;
        this.permitIssueDate = permitIssueDate;
        this.grantNumber = grantNumber;
        this.grantIssueDate = grantIssueDate;
    }

    public Lot(String landNumber, String lotNumber, int numberOfAcres, int numberofRoods, int numberOfPerches, String permitNumber, String permitIssueDate, String grantNumber, String grantIssueDate, Land land) {
        this.landNumber = landNumber;
        this.lotNumber = lotNumber;
        this.numberOfAcres = numberOfAcres;
        this.numberofRoods = numberofRoods;
        this.numberOfPerches = numberOfPerches;
        this.permitNumber = permitNumber;
        this.permitIssueDate = permitIssueDate;
        this.grantNumber = grantNumber;
        this.grantIssueDate = grantIssueDate;
        this.land = land;
    }
    
    public Lot(String landNumber, String lotNumber, int numberOfAcres, int numberofRoods, int numberOfPerches, String permitNumber, String permitIssueDate, String grantNumber, String grantIssueDate, Client client) {
        this.landNumber = landNumber;
        this.lotNumber = lotNumber;
        this.numberOfAcres = numberOfAcres;
        this.numberofRoods = numberofRoods;
        this.numberOfPerches = numberOfPerches;
        this.permitNumber = permitNumber;
        this.permitIssueDate = permitIssueDate;
        this.grantNumber = grantNumber;
        this.grantIssueDate = grantIssueDate;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getLandNumber() {
        return landNumber;
    }

    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getNumberOfAcres() {
        return numberOfAcres;
    }

    public void setNumberOfAcres(int numberOfAcres) {
        this.numberOfAcres = numberOfAcres;
    }

    public int getNumberofRoods() {
        return numberofRoods;
    }

    public void setNumberofRoods(int numberofRoods) {
        this.numberofRoods = numberofRoods;
    }

    public int getNumberOfPerches() {
        return numberOfPerches;
    }

    public void setNumberOfPerches(int numberOfPerches) {
        this.numberOfPerches = numberOfPerches;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getPermitIssueDate() {
        return permitIssueDate;
    }

    public void setPermitIssueDate(String permitIssueDate) {
        this.permitIssueDate = permitIssueDate;
    }

    public int getIs_p_certified() {
        return is_p_certified;
    }

    public void setIs_p_certified(int is_p_certified) {
        this.is_p_certified = is_p_certified;
    }

    public String getGrantNumber() {
        return grantNumber;
    }

    public void setGrantNumber(String grantNumber) {
        this.grantNumber = grantNumber;
    }

    public String getGrantIssueDate() {
        return grantIssueDate;
    }

    public void setGrantIssueDate(String grantIssueDate) {
        this.grantIssueDate = grantIssueDate;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }
    
    
    
}

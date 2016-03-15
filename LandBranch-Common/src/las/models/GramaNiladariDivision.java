/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package las.models;

import java.io.Serializable;

/**
 *
 * @author H.P. Asela
 */
public class GramaNiladariDivision implements Serializable{
    
    private String divisionNumber;
    private String divisionName;
    private String zoneName;
    private String gramaNilardariName;
    private int noOfLands;
    GramaNiladariDivision() {    
    }

    public GramaNiladariDivision(String divisionNumber, String divisionName, String zoneName, String gramaNilardariName) {
        this.divisionNumber = divisionNumber;
        this.divisionName = divisionName;
        this.zoneName = zoneName;
        this.gramaNilardariName = gramaNilardariName;
    }

    public int getNoOfLands() {
        return noOfLands;
    }

    public void setNoOfLands(int noOfLands) {
        this.noOfLands = noOfLands;
    }
    
    public GramaNiladariDivision(String divisionNumber,String divisionName,int noOfLands) {
        this.divisionNumber=divisionNumber;
        this.divisionName=divisionName;
        this.noOfLands=noOfLands;
    }
    
    public String getDivisionNumber() {
        return divisionNumber;
    }

    public void setDivisionNumber(String divisionNumber) {
        this.divisionNumber = divisionNumber;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getGramaNilardariName() {
        return gramaNilardariName;
    }

    public void setGramaNilardariName(String gramaNilardariName) {
        this.gramaNilardariName = gramaNilardariName;
    }
    
    
    
    
}

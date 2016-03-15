/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import las.models.Grant;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public interface GrantController extends Remote {

    public Grant searchGrant(String grantNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean addNewGrant(Grant grant) throws RemoteException, SQLException, ClassNotFoundException;

    public int getGrantCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Grant> getSimilarGrantNumbers(String grantNumberPart) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean changeGrantOwnership(Grant grant) throws RemoteException, SQLException, ClassNotFoundException;

    public Grant searchGrantByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Grant> getSimilarPermitNumberGrants(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Grant> getSimilarGrantsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Grant> getSimilarGrantsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException;

    public  boolean changeNominatedSuccessorGrant(Grant grant, NominatedSuccessor newSuccessor) throws RemoteException,ClassNotFoundException, SQLException;
    public ArrayList<Grant> getAllGrant() throws RemoteException, ClassNotFoundException, SQLException;
    
    
    
    
    
}

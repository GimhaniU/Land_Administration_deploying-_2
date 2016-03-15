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
import las.models.Client;
import las.models.Lot;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public interface LotController extends Remote {

    public boolean addNewLot(Lot lot) throws RemoteException, SQLException,ClassNotFoundException;

    public Lot searchLot(String lotNumber,String landNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Lot> searchLotOfLand(String landNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Lot> getAvailableLotOfLand(String landNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public boolean updateLot(Lot lot) throws RemoteException, SQLException,ClassNotFoundException;

    public Lot getLastAddedLot() throws RemoteException, SQLException,ClassNotFoundException;

    //Permit
    public boolean addNewPermit(Lot lot) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean cancelPermit(Lot lot) throws RemoteException, SQLException, ClassNotFoundException;

    public Lot searchPermit(String permitNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public int getPermitCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Lot> getSimilarPermitNumbers(String permitNumberPart) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Lot> getAllPermit() throws RemoteException, SQLException, ClassNotFoundException;

    public Lot searchPermitByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getSimilarPermitsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getSimilarPermitsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean addNominatedSuccessor(NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException;
    //get the client of this NIC and find the lot assigned to him
    public Lot searchPermitByNominatedSuccessor(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getGrantHaventPermit(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean changePermitOwnership(Lot lot) throws ClassNotFoundException, SQLException, RemoteException;

    public boolean addGramaNiladaryCertificateToPermit(Lot lot) throws RemoteException, ClassNotFoundException, SQLException;
    
    
    public ArrayList<Lot> getAllPermitsReadytoGrant() throws RemoteException,ClassNotFoundException, SQLException;


    //Grant
    public Lot searchGrant(String grantNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean addNewGrant(Lot lot) throws RemoteException, SQLException, ClassNotFoundException;

    public int getGrantCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Lot> getSimilarGrantNumbers(String grantNumberPart) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean changeGrantOwnership(Lot lot) throws RemoteException, SQLException, ClassNotFoundException;

    public Lot searchGrantByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getSimilarPermitNumberGrants(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getSimilarGrantsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getSimilarGrantsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException;

    public  boolean changeNominatedSuccessorGrant(Client client, NominatedSuccessor newSuccessor) throws RemoteException,ClassNotFoundException, SQLException;
    
    public ArrayList<Lot> getAllGrant() throws RemoteException, ClassNotFoundException, SQLException;
}   


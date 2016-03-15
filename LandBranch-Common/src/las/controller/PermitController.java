/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import las.models.Client;
import las.models.Lot;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author DinsuG
 */
public interface PermitController extends Remote {

    public boolean addNewPermit(Permit permit) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean cancelPermit(Permit permit) throws RemoteException, SQLException, ClassNotFoundException;

    public Permit searchPermit(String permitNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public int getPermitCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Permit> getSimilarPermitNumbers(String permitNumberPart) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<Permit> getAllPermit() throws RemoteException, SQLException, ClassNotFoundException;

    public Permit searchPermitByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Permit> getSimilarPermitsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Permit> getSimilarPermitsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean changeNominatedSuccessorPermit(Permit permit, NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException;

    public Permit searchPermitByNominatedSuccessor(String NIC) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Permit> getGrantHaventPermit(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean changePermitOwnership(Permit permit) throws ClassNotFoundException, SQLException, RemoteException;

    public boolean addGramaNiladaryCertificateToPermit(Permit permit) throws RemoteException, ClassNotFoundException, SQLException;
    
    
    public ArrayList<Permit> getAllPermitsReadytoGrant() throws RemoteException,ClassNotFoundException, SQLException;
}

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
import las.models.GramaNiladariDivision;
import las.models.Lot;


/**
 *
 * @author DinsuG
 */
public interface GramaNiladariDivisionController extends Remote {

    public boolean addNewGND(GramaNiladariDivision GND) throws RemoteException, SQLException, ClassNotFoundException;

    public GramaNiladariDivision searchGND(String DivisionNumber) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<GramaNiladariDivision> getSimmilarGndDivisionNumbers(String divisionNumberPart) throws RemoteException, SQLException, ClassNotFoundException;

    public boolean updateGND(GramaNiladariDivision GND) throws RemoteException, SQLException, ClassNotFoundException;

    public ArrayList<GramaNiladariDivision> getAllGNDDetail() throws ClassNotFoundException, SQLException, RemoteException;

    public GramaNiladariDivision searchGNDByOfficer(String officer) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<GramaNiladariDivision> getLandCount() throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Lot> getPermitsToCertify(String divisionnumber) throws RemoteException,SQLException, ClassNotFoundException;

    public ArrayList<Lot> getAllPermitsToCertify() throws RemoteException,SQLException, ClassNotFoundException;
}

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
import las.models.Land;

/**
 *
 * @author DinsuG
 */
public interface LandController extends Remote {

    public boolean addNewLand(Land land) throws RemoteException, SQLException,ClassNotFoundException;

    public Land getLastAddedLand() throws RemoteException, SQLException,ClassNotFoundException;

    public Land searchLand(String PlanNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public Land getAvailableLotOfLand(String PlanNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Land> getAllLandDetail() throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Land> getSimmilarPlanNumbers(String planNumberPart) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Land> getLandsOfDivision(String DivisionNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public boolean updateLand(Land land) throws RemoteException, SQLException,ClassNotFoundException;
}

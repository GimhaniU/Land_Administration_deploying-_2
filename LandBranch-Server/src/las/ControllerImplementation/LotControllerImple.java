/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import las.controllers.LotController;
import las.models.Client;
import las.models.Lot;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
    public class LotControllerImple extends UnicastRemoteObject implements las.controller.LotController {

    private final LotController lotController;

    public LotControllerImple() throws RemoteException {
        super();
        this.lotController = new LotController();
    }

    @Override
    public boolean addNewLot(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.addNewLot(lot);
    }

    @Override
    public Lot searchLot(String lotNumber,String landNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.searchLot(landNumber,lotNumber);
    }

    @Override
    public ArrayList<Lot> searchLotOfLand(String landNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.searchLotOfLand(landNumber);
    }

    @Override
    public ArrayList<Lot> getAvailableLotOfLand(String landNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.getAvailableLotOfLand(landNumber);
    }

    @Override
    public boolean updateLot(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.updateLot(lot);
    }

    @Override
    public Lot getLastAddedLot(String landnumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.getLastAddedLot(landnumber);
    }

    @Override
    public boolean addNewPermit(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelPermit(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lot searchPermit(String permitNumber) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPermitCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarPermitNumbers(String permitNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getAllPermit() throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lot searchPermitByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarPermitsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarPermitsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addNominatedSuccessor(NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lot searchPermitByNominatedSuccessor(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getGrantHaventPermit(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changePermitOwnership(Lot lot) throws ClassNotFoundException, SQLException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addGramaNiladaryCertificateToPermit(Lot lot) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getAllPermitsReadytoGrant() throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lot searchGrant(String grantNumber) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addNewGrant(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGrantCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarGrantNumbers(String grantNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeGrantOwnership(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lot searchGrantByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarPermitNumberGrants(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarGrantsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getSimilarGrantsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeNominatedSuccessorGrant(Client client, NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Lot> getAllGrant() throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

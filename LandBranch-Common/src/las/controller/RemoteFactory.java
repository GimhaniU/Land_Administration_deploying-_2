/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author DinsuG
 */
public interface RemoteFactory extends Remote {

    public ClientController getClientController() throws RemoteException, SQLException, ClassNotFoundException;

    public GramaNiladariDivisionController getGramaNiladaryDivisionController() throws RemoteException, SQLException, ClassNotFoundException;

    public LandController getLandController() throws RemoteException, SQLException, ClassNotFoundException;

    public LotController getLotController() throws RemoteException, SQLException, ClassNotFoundException;

    public NominatedSuccessorController getNominatedSuccessorController() throws RemoteException, SQLException, ClassNotFoundException;

    public UserController getUeController() throws RemoteException, SQLException, ClassNotFoundException;

    public BackUP getBackUP() throws RemoteException,SQLException,InterruptedException,ClassNotFoundException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public interface NominatedSuccessorController extends Remote {

    public NominatedSuccessor searchNominateSuccessor(int N_RegNo) throws RemoteException, SQLException,ClassNotFoundException;

    public boolean addNewNominateSuccessor(NominatedSuccessor NOS) throws RemoteException, SQLException,ClassNotFoundException;

    public boolean updateNiminateSuccessor(NominatedSuccessor nominateSuccessor) throws RemoteException, SQLException,ClassNotFoundException;
    
   public boolean DeleteNominatedSuccessor(int N_RegNo) throws RemoteException,ClassNotFoundException, SQLException;

    
}

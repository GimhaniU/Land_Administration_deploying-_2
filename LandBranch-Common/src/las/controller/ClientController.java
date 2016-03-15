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

/**
 *
 * @author DinsuG
 */
public interface ClientController extends Remote {

    public boolean addNewClient(Client client) throws RemoteException, SQLException,ClassNotFoundException;

    public Client searchClient(String NIC) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Client> getSimmilarNICs(String nicPart) throws RemoteException, SQLException,ClassNotFoundException;
    
     public  ArrayList<Client> getSimilarNames(String namePart) throws RemoteException,ClassNotFoundException, SQLException;
   
    public ArrayList<Client> getNoPermitOwners(String nicPart) throws RemoteException, SQLException,ClassNotFoundException;

    public int updateClient(Client client) throws RemoteException, SQLException,ClassNotFoundException;

    public ArrayList<Client> getAllClients() throws RemoteException, SQLException,ClassNotFoundException;

    public Client getLastAddedClient() throws RemoteException, SQLException,ClassNotFoundException;

    public int getnextOwnershiPositionPermit(String permitNumber) throws RemoteException, SQLException,ClassNotFoundException;

    public int getnextOwnershiPositionGrant(String grantNumber) throws RemoteException, SQLException,ClassNotFoundException;
    
    public boolean deleteClient(Client client) throws ClassNotFoundException, SQLException,RemoteException ;
  
}

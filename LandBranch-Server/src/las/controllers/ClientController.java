/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import las.db_utilities.DBConnection;
import las.db_utilities.DBHandler;
import las.models.Client;
import las.models.Lot;
import las.models.NominatedSuccessor;

/**
 *
 * @author Gimhani
 */
public class ClientController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewClient(Client client,Lot lot,ArrayList<NominatedSuccessor> nominatedSuccessors) throws ClassNotFoundException, SQLException {
        
        
        
        
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            
            
            
            
            String sql = "Insert into Client Values('"+client.getRegNo()+"','" + client.getClientName()+ "','" + client.getNIC()+ "','" + client.getLandnumber() + "','" + client.getLotnumber() + "','" + client.getAddress() + "','" + client.getAnnualIncome() + "','" + client.getPermitOwnershipPosition()+ "','" + client.getGrantOwnershipPosition()+ "','" + client.isMarried() + "','"+client.getSpouseName()+"','"+client.getGender()+"','" + client.getNumberOfMarriedSons() + "','" + client.getNumberOfUnmarriedSons() + "')";
            int returnValue = DBHandler.setData(conn, sql);

            return returnValue > 0;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Client searchClientByNIC(String NIC) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Client where NIC='" + NIC + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                return client;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
    
    public static Client searchClient(String regNo) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Client where regNo='" + regNo + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                return client;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getSimmilarNICs(String nicPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where NIC like '" + nicPart + "%'  order by NIC limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));         
                clientList.add(client);
            }
            return clientList;

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getNoPermitOwners(String nicPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where permitownershipPosition = '0' and grantownershipposition = '0' and NIC like '" + nicPart + "%'  order by NIC limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;

        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static int updateClient(Client client) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  Client Set  clientName='" + client.getClientName() + "', NIC='" + client.getNIC()+ "',LandNumber='" + client.getLandnumber()+ "', LotNumber='" + client.getLotnumber()+ "',Address='"+client.getAddress()+"', AnnualIncome='" + client.getAnnualIncome() + "',PermitOwnershipPosition='" + client.getPermitOwnershipPosition() + "', GrantOwnershipPosition='" + client.getGrantOwnershipPosition() + "',MarriedStatus='" + client.isMarried() + "',SpouseName='"+client.getSpouseName()+"',Gender='"+client.getGender()+"',NumberOfMarriedSons='" + client.getNumberOfMarriedSons() + "',NumberOfUnmarriedSons='" + client.getNumberOfUnmarriedSons() + "' Where RegNo ='" + client.getRegNo()+ "'";
            int res = DBHandler.setData(conn, sql);
            return res;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static ArrayList<Client> getAllClients() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Client";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static Client getLastAddedClient() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Client order by RegNo Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                return client;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static int getnextOwnershiPositionPermit(String permitNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select PermitOwnershipPosition From Client natural join lot where permitNumber ='" + permitNumber + "'  order by PermitOwnershipPosition Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            int position = 1;
            if (rst.next()) {
                position = rst.getInt("PermitOwnershipPosition") + 1;
            }

            return position;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static int getnextOwnershiPositionGrant(String grantNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select GrantOwnershipPosition From Client natural join lot where grantNumber ='" + grantNumber + "'  order by GrantOwnershipPosition Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            int position = 1;
            if (rst.next()) {
                position = rst.getInt("GrantOwnershipPosition") + 1;
            }

            return position;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getSimilarNames(String namePart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where ClientName like '" + namePart + "%'  order by RegNo ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                
                Client client = new Client(rst.getInt("RegNo"),rst.getString("ClientName"), rst.getString("NIC"), rst.getString("LandNumber"), rst.getString("LotNumber"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("PermitOwnershipPosition"), rst.getInt("GrantOwnershipPosition"), rst.getInt("MarriedStatus"),rst.getString("SpouseName") ,rst.getInt("Gender"),rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}

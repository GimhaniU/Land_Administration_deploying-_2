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
import las.models.Land;
import las.models.Lot;

/**
 *
 * @author Gimhani
 */


public class LotController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewLot(Lot lot) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into Lot Values('" + lot.getLandNumber() + "','" + lot.getLotNumber() + "','"+lot.getNumberOfAcres()+"','" + lot.getNumberofRoods() + "','" + lot.getNumberOfPerches() + "','" + lot.getPermitNumber() + "','"+lot.getPermitIssueDate()+"','"+lot.getIs_p_certified()+"','"+lot.getGrantNumber()+"','"+lot.getGrantIssueDate()+"')";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot searchLot(String landNumber,String lotNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where LotNumber='" + lotNumber + "' and landNumber ='"+landNumber+"'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber,lotNumber, rst.getInt("numberOfAcres"),rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"),rst.getString("permitNumber"),rst.getString("permitIssueDate"),rst.getString("grantNumber"),rst.getString("grantIssueDate"),searchLand );               
                return lot;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Lot> searchLotOfLand(String landNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where landNumber='" +landNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber,rst.getString("lotNumber"), rst.getInt("numberOfAcres"),rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"),rst.getString("permitNumber"),rst.getString("permitIssueDate"),rst.getString("grantNumber"),rst.getString("grantIssueDate"),searchLand );               
                lotList.add(lot);
            }
            return lotList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Lot> getAvailableLotOfLand(String landNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where landNumber='" + landNumber + "' and isAvailabal = 0 ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber,rst.getString("lotNumber"), rst.getInt("numberOfAcres"),rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"),rst.getString("permitNumber"),rst.getString("permitIssueDate"),rst.getString("grantNumber"),rst.getString("grantIssueDate"),searchLand );               
                lotList.add(lot);
            }
            return lotList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean updateLot(Lot lot) throws ClassNotFoundException, SQLException {

        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  Lot Set  NumberOfAcres='" + lot.getNumberOfAcres() + "', NumberOfPerches='" + lot.getNumberOfPerches() + "', NumberofRoods='" + lot.getNumberofRoods() + "', permitNumber='" + lot.getPermitNumber() + "', permitIssueDate='"+lot.getPermitIssueDate()+"',Is_p_certified='"+lot.getIs_p_certified()+"',grantNumber= '"+lot.getGrantNumber()+"',grantIssueDate='"+lot.getGrantIssueDate()+"' Where  LotNumber='" + lot.getLotNumber() + "' and landNumber='"+lot.getLandNumber()+"'";
            int res = DBHandler.setData(conn, sql);
            return res > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot getLastAddedLot() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Lot order by lotNumber Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land searchLand = LandController.searchLand(rst.getString("landNumber"));
                Lot lot = new Lot(rst.getString("landNumber"),rst.getString("lotNumber"), rst.getInt("numberOfAcres"),rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"),rst.getString("permitNumber"),rst.getString("permitIssueDate"),rst.getString("grantNumber"),rst.getString("grantIssueDate"),searchLand );               
                return lot;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

}

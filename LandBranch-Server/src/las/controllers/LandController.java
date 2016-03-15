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
public class LandController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewLand(Land land) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            boolean returnValue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                String sql = "Insert into Land Values('" + land.getLandName() + "','" + land.getLandName() + "','" + land.getPlanNumber() + "','" + land.getDivisionNumber() + "')";
                int returnInt = DBHandler.setData(conn, sql);
                if (returnInt > 0) {
                    System.out.println("land add");
                    ArrayList<Lot> lotList = land.getLotList();
                    for (Lot lot : lotList) {
                        System.out.println("lot added");
                        boolean addNewLot = LotController.addNewLot(lot);
                        if (!addNewLot) {
                            returnValue = false;
                            conn.rollback();
                            break;
                        }
                    }
                } else {
                    returnValue = false;
                    conn.rollback();
                }
                if (returnValue) {
                    conn.commit();
                }
            } catch (SQLException error) {
                error.printStackTrace();
                returnValue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }

            return returnValue;
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    /*  public static boolean updateClient(Land land) throws ClassNotFoundException, SQLException {
     Connection conn = DBConnection.getDBConnection().getConnection();
     String sql = "Update  Client Set  landName='" + land.getLandName() + "', DivisionNumber='" + land.getDivisionNumber() + "',WestBound='" + land.getWestBound() + "', EastBound='" + land.getEastBound() + "', NorthBound='" + land.getNorthBound() + "', SounthBound='" + land.getSouthBound() + "' where PlanNumber ='" + land.getPlanNumber() + "'";
     int res = DBHandler.setData(conn, sql);
     return res > 0;
     }
     */
    //to create next plan number
    public static Land getLastAddedLand() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Land order by landNumber Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"));
                return land;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static Land searchLand(String landNumber) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Land where landNumber = '" + landNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                ArrayList<Lot> searchLotOfLand = LotController.searchLotOfLand(landNumber);
                Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"),searchLotOfLand);
                return land;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static Land getAvailableLotOfLand(String landNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Land where landNumber = '" + landNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                ArrayList<Lot> searchLotOfLand = LotController.getAvailableLotOfLand(landNumber);
                Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"),searchLotOfLand);
                return land;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static ArrayList<Land> getAllLandDetail() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select LandNumber,LandName,DivisionNumber,planNumber,count(LotNumber) as NumberOfLots  From Land natural join lot group by landNumber";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Land> landlist = new ArrayList<>();
            while (rst.next()) {
                Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"),rst.getInt("NumberOfLots"));
                landlist.add(land);
            }
            return landlist;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static ArrayList<Land> getSimmilarPlanNumbers(String landNumberPart) throws ClassNotFoundException, SQLException {

        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From land where landNumber like '" + landNumberPart + "%'  order by landNumber limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Land> landList = new ArrayList<>();
            while (rst.next()) {
               Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"),rst.getInt("NumberOfLots"));
                landList.add(land);
             }
            return landList;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static ArrayList<Land> getLandsOfDivision(String DivisionNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From land where DivisionNumber = '" + DivisionNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Land> landList = new ArrayList<>();
            while (rst.next()) {
               Land land = new Land(rst.getString("LandNumber"), rst.getString("LandName"), rst.getString("planNumber"),rst.getString("DivisionNumber"),rst.getInt("NumberOfLots"));
                landList.add(land);
            }
            return landList;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static boolean updateLand(Land land) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            boolean returnValue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                String sql = "Update  Land Set  LandName='" + land.getLandName() + "', DivisionNumber='" + land.getDivisionNumber() + "' Where  landNumber='" + land.getLandnumber() + "'";
                int returnInt = DBHandler.setData(conn, sql);
                if (returnInt > 0) {
                    System.out.println("land update");
                    ArrayList<Lot> lotList = land.getLotList();
                    for (Lot lot : lotList) {
                        System.out.println("lot update");
                        boolean updateLot = LotController.updateLot(lot);
                        if (!updateLot) {
                            returnValue = false;
                            conn.rollback();
                            break;
                        }
                    }
                } else {
                    returnValue = false;
                    conn.rollback();
                }
                if (returnValue) {
                    conn.commit();
                }
            } catch (SQLException error) {
                returnValue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }

            return returnValue;
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
}

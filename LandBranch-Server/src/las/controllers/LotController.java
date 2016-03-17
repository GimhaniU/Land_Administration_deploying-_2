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
            String sql = "Insert into Lot Values('" + lot.getLandNumber() + "','" + lot.getLotNumber() + "','" + lot.getNumberOfAcres() + "','" + lot.getNumberofRoods() + "','" + lot.getNumberOfPerches() + "','" + lot.getPermitNumber() + "','" + lot.getPermitIssueDate() + "','" + lot.getIs_p_certified() + "','" + lot.getGrantNumber() + "','" + lot.getGrantIssueDate() + "')";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot searchLot(String landNumber, String lotNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where LotNumber='" + lotNumber + "' and landNumber ='" + landNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber, lotNumber, rst.getInt("numberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"), rst.getString("permitNumber"), rst.getString("permitIssueDate"), rst.getInt("is_p_certified"), rst.getString("grantNumber"), rst.getString("grantIssueDate"), searchLand);
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
            String sql = "Select * from Lot where landNumber='" + landNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber, rst.getString("lotNumber"), rst.getInt("numberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"), rst.getString("permitNumber"), rst.getString("permitIssueDate"), rst.getInt("is_p_certified"), rst.getString("grantNumber"), rst.getString("grantIssueDate"), searchLand);
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
            String sql = "Select * from Lot where landNumber='" + landNumber + "' and permitnumber is null and grantnumber is null ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Land searchLand = LandController.searchLand(landNumber);
                Lot lot = new Lot(landNumber, rst.getString("lotNumber"), rst.getInt("numberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"), rst.getString("permitNumber"), rst.getString("permitIssueDate"), rst.getInt("is_p_certified"), rst.getString("grantNumber"), rst.getString("grantIssueDate"), searchLand);
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
            String sql = "Update  Lot Set  NumberOfAcres='" + lot.getNumberOfAcres() + "', NumberOfPerches='" + lot.getNumberOfPerches() + "', NumberofRoods='" + lot.getNumberofRoods() + "', permitNumber='" + lot.getPermitNumber() + "', permitIssueDate='" + lot.getPermitIssueDate() + "',Is_p_certified='" + lot.getIs_p_certified() + "',grantNumber= '" + lot.getGrantNumber() + "',grantIssueDate='" + lot.getGrantIssueDate() + "' Where  LotNumber='" + lot.getLotNumber() + "' and landNumber='" + lot.getLandNumber() + "'";
            int res = DBHandler.setData(conn, sql);
            return res > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot getLastAddedLot(String landnumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Lot order by lotNumber Desc limit 1 where landnumber='" + landnumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land searchLand = LandController.searchLand(landnumber);
                Lot lot = new Lot(landnumber, rst.getString("lotNumber"), rst.getInt("numberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"), rst.getString("permitNumber"), rst.getString("permitIssueDate"), rst.getInt("is_p_certified"), rst.getString("grantNumber"), rst.getString("grantIssueDate"), searchLand);
                return lot;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static boolean addNewPermit(Lot lot) throws ClassNotFoundException, SQLException {

        try {
            readWriteLock.writeLock().lock();
            boolean updateLot = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            System.out.println("permit");

            try {
                updateLot = updateLot(lot);
                if (updateLot) {
                    Client client = lot.getClient();
                    if (client != null) {
                        boolean addNewClient = ClientController.addNewClient(client);
                        if (addNewClient) {
                            System.out.println("permit added");
                        } else {
                            updateLot = false;
                            conn.rollback();
                        }
                    }
                } else {
                    updateLot = false;
                    conn.rollback();
                }

                if (updateLot) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                updateLot = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return updateLot;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean cancelPermit(Lot lot) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            boolean returnStatue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                String sql = "Delete  from permit where permitNumber = '" + permit.getPermitNumber() + "'";
                int returnPermitDelete = DBHandler.setData(conn, sql);
                if (returnPermitDelete > 0) {
                    System.out.println("permit delete");
                    Client client = permit.getClient();
                    // int position = ClientController.getnextOwnershiPositionPermit(permit.getPermitNumber());
                    client.setPermitOwnershipPosition(0);
                    int updateClient = ClientController.updateClient(client);
                    if (updateClient > 0) {
                        System.out.println("client updated");
                        NominatedSuccessor nominatedSuccessor = permit.getNominatedSuccessor();
                        boolean cancelPermit = NominatedSuccessorController.DeleteNominatedSuccessor(nominatedSuccessor.getNIC_S());
                        if (cancelPermit) {
                            System.out.println("succer delete");
                            Lot lot = permit.getLot();
                            lot.setIsAvilable(0);
                            boolean updateLot = LotController.updateLot(lot);
                            if (updateLot) {
                                System.out.println("lotUpdated");
                            } else {
                                returnStatue = false;
                                conn.rollback();
                            }
                        } else {
                            returnStatue = false;
                            conn.rollback();
                        }

                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }

                if (returnStatue) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Permit searchPermit(String permitNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Permit natural join client where PermitNumber='" + permitNumber + "' order by permitOwnershipPosition desc";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, client, searchNominateSuccessor);
                return permit;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static int getPermitCountOfDivision(String divisionNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "select count(distinct permitNumber) as permitCount from permit natural join lot natural join land where divisionNumber ='" + divisionNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            int permitCount = 0;
            if (rst.next()) {
                permitCount = rst.getInt("permitCount");

            }
            return permitCount;

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Permit> getSimilarPermitNumbers(String permitNumberPart) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From permit where permitNumber like '" + permitNumberPart + "%'  order by permitNumber limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Permit> getAllPermit() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From permit";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    //*******************************************//
    public static Permit searchPermitByClient(String NIC) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Permit where NIC='" + NIC + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, client, searchNominateSuccessor);
                return permit;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Permit> getSimilarPermitsByName(String namepart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from client right join permit on client.NIC=permit.NIC where ClientName like '" + namepart + "%'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Permit> getSimilarPermitsByNIC(String nicpart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from client right join permit on client.NIC=permit.NIC where client.NIC like '%" + nicpart + "%'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static Permit searchPermitByNominatedSuccessor(String NIC) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Permit where NIC_Successor='" + NIC + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, client, searchNominateSuccessor);
                return permit;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean changeNominatedSuccessorPermit(Permit permit, NominatedSuccessor newSuccessor) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            boolean returnStatue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                boolean addNewNominateSuccessor = NominatedSuccessorController.addNewNominateSuccessor(newSuccessor);
                if (addNewNominateSuccessor) {
                    System.err.println("new successor added");
                    String sql = "Update permit set NIC_Successor ='" + newSuccessor.getNIC_S() + "' where permitNumber = '" + permit.getPermitNumber() + "'";
                    int returnPermitDelete = DBHandler.setData(conn, sql);
                    if (returnPermitDelete > 0) {
                        System.out.println("permit update");
                        NominatedSuccessor nominatedSuccessor = permit.getNominatedSuccessor();

                        boolean DeleteNominatedSuccessor = NominatedSuccessorController.DeleteNominatedSuccessor(nominatedSuccessor.getNIC_S());
                        if (DeleteNominatedSuccessor) {
                            System.out.println("old successsor deleted");

                        } else {
                            returnStatue = false;
                            conn.rollback();
                        }
                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }

                if (returnStatue) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static ArrayList<Permit> getGrantHaventPermit(String permitNumberPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From permit where haveGrant =0 and permitNumber like '" + permitNumberPart + "%'  order by permitNumber limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean changePermitOwnership(Permit permit) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            boolean returnStatue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                boolean addNewClient = ClientController.addNewClient(permit.getClient());
                if (addNewClient) {
                    System.err.println("new client added");
                    String sql = "Insert into permit Values('" + permit.getPermitNumber() + "','" + permit.getPermitIssueDate() + "','" + permit.getLot().getLotNumber() + "','" + permit.getClient().getNIC() + "','" + permit.getNominatedSuccessor().getNIC_S() + "','" + permit.getHaveGrant() + "','" + permit.getCertified() + "')";
                    int returnPermitInsert = DBHandler.setData(conn, sql);
                    if (returnPermitInsert > 0) {
                        System.out.println("permit aded");

                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }

                if (returnStatue) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean addGramaNiladaryCertificateToPermit(Permit permit) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update permit set certified = '" + permit.getCertified() + "' where permitNumber = '" + permit.getPermitNumber() + "'";
            int returnPermitDelete = DBHandler.setData(conn, sql);
            return returnPermitDelete > 0;

        } finally {

            readWriteLock.writeLock().unlock();

        }
    }

    public static ArrayList<Permit> getPermitSuitableForGrant(String permitNumberPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from permit where certified =1 and havegrant =0 and datediff(curdate(),permitissueDate) >365 and permitNumber like '" + permitNumberPart + "%' ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, client, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static ArrayList<Permit> getAllPermitsReadytoGrant() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From permit where haveGrant =0";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean addGrantToPermit(Permit permit) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update permit set havegrant = '" + permit.getHaveGrant() + "' where permitNumber = '" + permit.getPermitNumber() + "'";
            int returnPermitDelete = DBHandler.setData(conn, sql);
            return returnPermitDelete > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}

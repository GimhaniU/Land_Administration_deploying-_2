/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controllers;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import las.db_utilities.DBConnection;
import las.db_utilities.DBHandler;
import las.models.NominatedSuccessor;

/**
 *
 * @author Gimhani
 */
public class NominatedSuccessorController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    
    public static NominatedSuccessor searchNominateSuccessor(int N_RegNo) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from NominatedSuccessor where N_RegNo='" + N_RegNo+ "'";
           ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                NominatedSuccessor nominateSuccessor = new NominatedSuccessor(N_RegNo, rst.getString("Name"),rst.getString("NIC_S"), rst.getString("Address"),rst.getInt("RegNo"),rst.getDouble("Portion"),rst.getString("relationship"));
                return nominateSuccessor;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static boolean addNewNominateSuccessor(NominatedSuccessor NOS) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into nominatedsuccessor Values('" + NOS.getN_RegNo() + "','" + NOS.getName() + "','"+NOS.getNIC_S()+"','" + NOS.getAddress() + "','"+NOS.getOwner_reg_no()+"','"+NOS.getPortion()+"','"+NOS.getRelationship()+"')";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    public static boolean updateNiminateSuccessor(NominatedSuccessor nominateSuccessor) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  nominatedsuccessor Set  Name='" + nominateSuccessor.getName() + "', Address='" + nominateSuccessor.getAddress() + "' ,RegNo='"+nominateSuccessor.getOwner_reg_no()+"', Portion='"+nominateSuccessor.getPortion()+"',Relationship='"+nominateSuccessor.getRelationship()+"'   Where N_RegNo ='" + nominateSuccessor.getN_RegNo() + "'";
            int res = DBHandler.setData(conn, sql);
            return res > 0;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    public static boolean DeleteNominatedSuccessor(int N_RegNo) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Delete  from nominatedSuccessor where N_RegNo = '" + N_RegNo+ "'";
        int returnDelete = DBHandler.setData(conn, sql);
        return returnDelete > 0;
    }
    
}

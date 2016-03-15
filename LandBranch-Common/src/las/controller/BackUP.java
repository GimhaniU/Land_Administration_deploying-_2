/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controller;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author DinsuG
 */
public interface BackUP extends Remote{
    
    public int writeBackup() throws IOException,RemoteException,SQLException,InterruptedException;

    public int restoreBackup() throws RemoteException,IOException, InterruptedException ;
    
}

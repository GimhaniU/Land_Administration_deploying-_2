/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import las.controllers.NominatedSuccessorController;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public class NominatedSuccessorControllerImple extends UnicastRemoteObject implements las.controller.NominatedSuccessorController {

    private final NominatedSuccessorController nominatedSuccessorController;

    public NominatedSuccessorControllerImple() throws RemoteException {
        super();
        this.nominatedSuccessorController = new NominatedSuccessorController();
    }

    @Override
    public NominatedSuccessor searchNominateSuccessor(int N_RegNo) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.searchNominateSuccessor(N_RegNo);
    }

    @Override
    public boolean addNewNominateSuccessor(NominatedSuccessor NOS) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.addNewNominateSuccessor(NOS);
    }

    @Override
    public boolean updateNiminateSuccessor(NominatedSuccessor nominateSuccessor) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.updateNiminateSuccessor(nominateSuccessor);
    }

    @Override
    public boolean DeleteNominatedSuccessor(int N_RegNo) throws RemoteException, ClassNotFoundException, SQLException {
        return nominatedSuccessorController.DeleteNominatedSuccessor(N_RegNo);//To change body of generated methods, choose Tools | Templates.
    }
}

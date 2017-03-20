package com.wilhelmsen.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Harald Wilhelmsen on 20/3/2017.
 */
public interface IRegister extends Remote {

    boolean regNyttUtstyr(int startNr, String startBetegnelse,
                          String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException;

    int endreLagerbeholdning(int nr, int mengde) throws RemoteException;

    String lagBestillingsliste() throws RemoteException;

    String lagDatabeskrivelse() throws RemoteException;
}

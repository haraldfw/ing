package com.wilhelmsen.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harald Wilhelmsen on 20/3/2017.
 * Et register holder orden på en mengde Utstyrsobjekter. En klient kan legge inn nye
 * Utstyr-objekter i registeret, og også endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */

class RegisterImpl extends UnicastRemoteObject implements IRegister {

    public static final int ok = -1;
    public static final int ugyldigNr = -2;
    public static final int ikkeNokPåLager = -3;

    private List<Utstyr> registeret = new ArrayList<>();

    public RegisterImpl() throws RemoteException {
    }

    public synchronized boolean regNyttUtstyr(int startNr, String startBetegnelse,
                                              String startLeverandør, int startPåLager, int startNedreGrense)
            throws RemoteException {
        if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra før
            Utstyr nytt = new Utstyr(startNr, startBetegnelse, startLeverandør,
                    startPåLager, startNedreGrense);
            registeret.add(nytt);
            return true;
        } else return false;
    }

    public synchronized int endreLagerbeholdning(int nr, int mengde) throws RemoteException {
        int indeks = finnUtstyrindeks(nr);
        if (indeks < 0) {
            System.out.println("Ugyldig nummer");
            return ugyldigNr;
        } else {
            if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
                return ikkeNokPåLager;
            } else return ok;
        }
    }

    private synchronized int finnUtstyrindeks(int nr) throws RemoteException {
        for (int i = 0; i < registeret.size(); i++) {
            int funnetNr = (registeret.get(i)).finnNr();
            if (funnetNr == nr) return i;
        }
        return -1;
    }

    public synchronized String lagBestillingsliste() throws RemoteException {
        String resultat = "\n\nBestillingsliste:\n";
        for (Utstyr u : registeret) {
            resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " +
                    u.finnBestKvantum() + "\n";
        }
        return resultat;
    }

    public synchronized String lagDatabeskrivelse() throws RemoteException {
        String resultat = "Alle data:\n";
        for (Utstyr aRegisteret : registeret) {
            resultat += aRegisteret.toString() + "\n";
        }
        return resultat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        registeret.forEach(utstyr -> {
            sb.append(utstyr.toString());
            sb.append("\n");
        });
        return sb.toString();
    }
}

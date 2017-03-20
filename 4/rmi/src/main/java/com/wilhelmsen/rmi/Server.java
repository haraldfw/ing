package com.wilhelmsen.rmi;

import javax.swing.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Harald Wilhelmsen on 20/3/2017.
 */
public class Server {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        LocateRegistry.createRegistry(1099);

        IRegister reg = new RegisterImpl();
        String objectName = "utstyrsregister";
        Naming.rebind(objectName, reg);

        reg.regNyttUtstyr(
                0, "bet", "lev", 1337, 0);
        reg.regNyttUtstyr(
                1, "bet1", "lev", 1337, 0);
        reg.regNyttUtstyr(
                2, "bet2", "lev1", 1337, 0);
        reg.regNyttUtstyr(
                3, "bet3", "lev2", 1337, 0);
        reg.regNyttUtstyr(
                4, "bet4", "lev4", 1337, 0);

        System.out.println("RMI-objekt er registrert");
        JOptionPane.showMessageDialog(null,
                "Trykk OK for å stoppe tjeneren.");
        Naming.unbind(objectName);
    }
}

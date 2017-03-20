package com.wilhelmsen.rmi;

import javax.swing.*;
import java.rmi.Naming;

/**
 * Created by Harald Wilhelmsen on 20/3/2017.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        IRegister reg = (IRegister) Naming.lookup("rmi://localhost/utstyrsregister");

        String nr;
        String mengde;
        do {
            nr = JOptionPane.showInputDialog("nr");
            mengde = JOptionPane.showInputDialog("mengde");
            int nrInt = Integer.parseInt(nr); //ingen feilkontroll
            int mengdeInt = Integer.parseInt(mengde); //ingen feilkontroll
            reg.endreLagerbeholdning(nrInt, mengdeInt);
            System.out.println(reg.lagBestillingsliste());
            System.out.println(reg.lagDatabeskrivelse());
        } while (!nr.isEmpty() && !mengde.isEmpty());
    }
}

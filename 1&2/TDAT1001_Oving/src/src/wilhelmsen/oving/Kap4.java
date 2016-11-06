package src.wilhelmsen.oving;

import javax.swing.*;

/**
 * Created by Harald Wilhelmsen on 8/22/2014.
 */
public class Kap4 {

    /** Oppgave 1
     * 13-gangen:
     * 13 x 1 = 13
     * ...
     * 13 x 10 = 13
     *
     * Oppgave 5:
     * Skriv metode som finner ut om et tall er et primtall.
     */

    public static void main(String[] args) {

        oppgave5();
    }

    private static void oppgave4() {
        int mulStart = Integer.parseInt(JOptionPane.showInputDialog("Starting-number"));
        int mulEnd = Integer.parseInt(JOptionPane.showInputDialog("Ending-number"));
        for(int i = mulStart; i <= mulEnd; i++) {
            System.out.println(i + "-gangen");
            for(int j = 0; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + i*j);
            }
        }
    }

    private static void oppgave5() {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Enter number"));
        JOptionPane.showMessageDialog(null, num + " is " + (isPrime(num) ? "prime" : "not prime"));
    }

    private static boolean isPrime(double n) {
        if (n%2==0) return false;

        for(int i = 3; i*i <= n; i += 2) {
            if(n%i == 0) return false;
        }

        return true;
    }
}

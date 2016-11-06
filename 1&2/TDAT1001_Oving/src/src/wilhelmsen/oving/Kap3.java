package src.wilhelmsen.oving;

import javax.swing.*;

/**
 * Created by Harald Wilhelmsen on 8/22/2014.
 */
public class Kap3 {

    /**
     * Oppg 3.12.3: Tegn opp algorithmen for om et år er skuddår. Les inn fra bruker, returner true/false.
     * Oppg 3.12.4: Kilopris gitt pris og gram
     */

    public static void main(String[] args) {
        oppgave3();
        oppgave4();
    }

    private static void oppgave4() {
        float price = Float.parseFloat(JOptionPane.showInputDialog("Price?"));
        float weight = Float.parseFloat(JOptionPane.showInputDialog("Weight[grams]?"));
        JOptionPane.showMessageDialog(null, "Price per kilo: " + (price/(weight/1000)) + "\nPrice per gram: " + (price/weight));
    }

    private static void oppgave3() {
        int year = Integer.parseInt(JOptionPane.showInputDialog("What year?"));
        if((!(year%100 == 0) && year%4 == 0) || year%400 == 0)
            JOptionPane.showMessageDialog(null, "Leap year");
        else JOptionPane.showMessageDialog(null, "Not a leap year");
    }
}

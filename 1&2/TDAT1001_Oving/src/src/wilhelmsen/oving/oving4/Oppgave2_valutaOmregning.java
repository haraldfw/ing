package src.wilhelmsen.oving.oving4;

import javax.swing.*;

/**
 * Created by Harald Wilhelmsen on 8/22/2014.
 */
public class Oppgave2_valutaOmregning {

	/**
	 * Oppgave 2:
	 *  Valuta-klasse for å regne til og fra NOK.
	 *  Bruk meny(kanskje i console e. l)
	 */

    public static void main(String[] args) {
        oppgave2();
    }

    private static void oppgave2() {
        Currency[] currencies = {
                new Currency(1, "NOK"),
                new Currency(8.2465958f, "EUR"),
                new Currency(6.38088796f, "USD")
            };

        String selections = "";
        for(int i = 0; i < currencies.length; i++)
            selections += i + ": " + currencies[i].isoCode + "\n";
        selections += "*:" + "Exit";

        int convertFrom = handleConversion(
                JOptionPane.showInputDialog("Select currency to convert from:\n" + selections),
                0, currencies.length);

        int convertTo = handleConversion(
                JOptionPane.showInputDialog("Select currency to convert to:\n" + selections),
                0, currencies.length);

        float value = Float.parseFloat(JOptionPane.showInputDialog("Enter value to convert."));

        Currency f = currencies[convertFrom];
        Currency t = currencies[convertTo];
        JOptionPane.showMessageDialog(null, value + " " + f.isoCode + " = " + (value*f.toNokRate*(1/t.toNokRate)) + " " + t.isoCode);
    }

    private static int handleConversion(String selection, int rangeStart, int rangeEnd) {
        int sel = -1;
        try{
            sel = Integer.parseInt(selection);
        } catch(Exception e) {
            System.exit(0);
        }
        if(sel < rangeStart || sel >= rangeEnd) System.exit(0);
        return sel;
    }

    private static void oppgave2Effektiv() {
        String[] currName = {"NOK", "EUR", "USD"};

        float[][] conversionTable = {
                // NOK, EUR, USD
                {1, 0.121262158f, 0.156718f},   //NOK
                {8.2465958f, 1, 1.29239f},      // EUR
                {6.38088796f, 0.773760243f, 1}  // USD
        };

        String selections = "";
        for(int i = 0; i < currName.length; i++) {
            selections += "\n" + i + ":" +  currName[i];
        }
        selections += "\n" + (currName.length) + ":Exit";

        int from;
        while(true) {
            from = Integer.parseInt(JOptionPane.showInputDialog("Select currency to convert from." + selections));
            if(from >= 0 && from < currName.length) break;
            else if(from == currName.length) System.exit(0);
        }
        float fromAmount = Float.parseFloat(JOptionPane.showInputDialog("How many " + currName[from] + "s?"));

        int to;
        while(true) {
            to = Integer.parseInt(JOptionPane.showInputDialog("Select currency to convert to." + selections));
            if(to >= 0 && to < currName.length) break;
            else if(to == currName.length) System.exit(0);
        }

        JOptionPane.showMessageDialog(null, fromAmount + " " + currName[from] + " is " + conversionTable[from][to]*fromAmount + " " + currName[to]);
    }
}


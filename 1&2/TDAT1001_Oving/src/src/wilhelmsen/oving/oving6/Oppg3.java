package src.wilhelmsen.oving.oving6;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Harald Wilhelmsen on 9/29/2014.
 */
public class Oppg3 {

	public static void main(String[] args) {

		while(true) {
			TextAnalysis ta = new TextAnalysis(JOptionPane.showInputDialog("Please enter a string. Non-alpha characters will be ignored in the analysis.\nEnter empty to exit"));
			if(ta.string.length() == 0) break;
			String message = "";
			message += "String: " + ta.string;
			message += "\nLength: " + ta.length();
			message += "\nDifferent chars: " + ta.amountDifferentCharacters();
			message += "\n%non-Alpha char: " + Math.round(ta.percentageOfNonAlphaCharacters()*100)/100;

			message += "\nOccurences:\n";
			int[] occs = ta.occurrencesOfAllChar();
			for(int i = 0; i < occs.length; i++) {
				message += TextAnalysis.alphas.charAt(i) + ":" + occs[i] + " ";
				if(i == occs.length/2) message += "\n";
			}

			message += "\nMost occuring:\n";
			ArrayList<Character> mostOccurring = ta.mostOccurring();
			for(Character c : mostOccurring) {
				message += c + " ";
			}
			JOptionPane.showMessageDialog(null, message);
		}
	}
}

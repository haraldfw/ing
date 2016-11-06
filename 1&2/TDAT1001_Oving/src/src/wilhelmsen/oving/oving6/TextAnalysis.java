package src.wilhelmsen.oving.oving6;

import java.util.ArrayList;

/**
 * Created by Harald Wilhelmsen on 9/29/2014.
 */
public class TextAnalysis {

	public String string;
	public String low;

	public static String alphas = "abcdefghijklmnopqrstuvwxyzæøå";

	public TextAnalysis(String string) {
		this.string = string;
		low = new String(string).toLowerCase();
	}

	public int amountDifferentCharacters() {
		ArrayList<Character> diff = new ArrayList<Character>();
		for(int i = 0; i < low.length(); i++) {
			char at = low.charAt(i);
			if(!inArray(diff, at) && alphas.contains(String.valueOf(at))) diff.add(at);
		}
		return diff.size();
	}

	private boolean inArray(ArrayList<Character> chars, char c) {
		for(int i = 0; i < chars.size(); i++) {
			if(chars.get(i).equals(c)) return true;
		}
		return false;
	}

	public int length() {
		return low.length();
	}

	public float percentageOfNonAlphaCharacters() {
		int nonAlpha = 0;
		for(int i = 0; i < low.length(); i++)
			if(!alphas.contains(String.valueOf(low.charAt(i)))) nonAlpha++;
		return ((float)nonAlpha/(float)low.length())*100f;
	}

	public int occurrenceOf(String ch) {
		int occ = 0;
		char use = ch.toLowerCase().charAt(0);
		for(int i = 0; i < low.length(); i++) {
			if(use == low.charAt(i)) occ++;
		}
		return occ;
	}

	public int[] occurrencesOfAllChar() {
		int[] occs = new int[alphas.length()];
		for(int i = 0; i < low.length(); i++) {
			int index = alphas.indexOf(low.charAt(i));
			if(index < 0) continue;
			occs[index] ++;
		}
		return occs;
	}

	public ArrayList<Character> mostOccurring() {
		int highest = 0;
		int[] occurences = occurrencesOfAllChar();
		ArrayList<Character> mostOccurring = new ArrayList<Character>();
		for(int i = 0; i < occurences.length; i++) {
			if(occurences[i] > highest) {
				highest = occurences[i];
				mostOccurring.clear();
				mostOccurring.add(alphas.charAt(i));
			} else if(occurences[i] == highest) {
				mostOccurring.add(alphas.charAt(i));
			}
		}
		return mostOccurring;
	}
}

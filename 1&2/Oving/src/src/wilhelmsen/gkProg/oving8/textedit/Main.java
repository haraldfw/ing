package src.wilhelmsen.gkProg.oving8.textedit;

/**
 * Created by Harald on 5.10.15.
 */
public class Main {

  public static void main(String[] args) {
    TextEdit te = new TextEdit(
        "Little brown dwars are cool as hell when they do the thing that is really cool. What is "
        + "the common denominator of your butt and the sun? The answer: things we do not talk "
        + "about. Is this a question if I do not ask it? No, is the answer!");
    System.out.println(te.getString());
    System.out.println("'is' is now 'was': " + te.getReplaced(" is ", " was "));
    System.out.println("num words: " + te.getNumWords());
    System.out.println("avgword count in period: " + te.getAvgWordCountInPeriod());
    System.out.println("avg word-length: " + te.getAvgWordLength());
    System.out.println("upper: " + te.getInUpperCase());
  }

}

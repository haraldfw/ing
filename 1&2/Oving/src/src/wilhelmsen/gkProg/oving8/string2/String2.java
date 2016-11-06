package src.wilhelmsen.gkProg.oving8.string2;

/**
 * Created by Harald on 5.10.15.
 */
public final class String2 {

  private final String string;

  public String2(String string) {
    this.string = string;
  }

  public String firstLetterEveryWord() {
    String[] split = string.split(" ");
    StringBuilder buf = new StringBuilder();
    for (String s : split) {
      buf.append(s.charAt(0));
    }
    return buf.toString();
  }

  public String without(String seq) {
    String ret = string;
    int index;
    while ((index = ret.indexOf(seq)) != -1) {
      ret = ret.substring(0, index) + ret.substring(index + seq.length(), ret.length() - 1);
    }
    return ret;
  }
}

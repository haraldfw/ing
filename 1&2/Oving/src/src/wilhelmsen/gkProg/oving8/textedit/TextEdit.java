package src.wilhelmsen.gkProg.oving8.textedit;

/**
 * Created by Harald on 5.10.15.
 */
public final class TextEdit {

  private final String string;

  public TextEdit(String string) {
    this.string = string;
  }

  public int getNumWords() {
    return string.split(" ").length;
  }

  public double getAvgWordLength() {
    String[] split = string.split(" |.|!|\\?");
    int tot = 0;
    for (String s : split) {
      tot += s.length();
    }
    return (double) tot / split.length;
  }

  public double getAvgWordCountInPeriod() {
    String[] split = string.split("\\.|\\!|\\:|\\?");
    int tot = 0;
    for (String s : split) {
      tot += s.split(" ").length;
    }
    return (double) tot / split.length;
  }

  public String getReplaced(String replace, String replaceWith) {
    return string.replace(replace, replaceWith);
  }

  public String getInUpperCase() {
    return string.toUpperCase();
  }

  public String getString() {
    return string;
  }
}

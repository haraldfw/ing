package src.wilhelmsen.gkProg.oving7.temperature;

/**
 * Created by Harald on 4.10.15.
 */
public class Main {

  public static void main(String[] args) {
    double[][] tempAr = new double[][]{
        {10, 12, 8},
        {5, 5, 6},
        {25, 35, 33},
        {1, 3, 2},
        {10, 5, -1}
    };
    Temperatures temps = new Temperatures(tempAr);
    System.out.println(temps.getAvgForMonth());
    printAr(temps.getAvgPerDay());
    printAr(temps.getAvgPerHour());
    System.out.println(
        "Dager med mid mindre enn -5: " + temps.getNumDaysWithAvgBetween(-Double.MAX_VALUE, 0));
    System.out.println("Dager med mid mellom -5 og 0: " + temps.getNumDaysWithAvgBetween(-5, 0));
    System.out.println("Dager med mid mellom 0 og 5: " + temps.getNumDaysWithAvgBetween(0, 5));
    System.out.println("Dager med mid mellom 5 og 10: " + temps.getNumDaysWithAvgBetween(5, 10));
    System.out.println(
        "Dager med mid over 10: " + temps.getNumDaysWithAvgBetween(10, Double.MAX_VALUE));
  }

  private static void printAr(double[] ar) {
    StringBuffer s = new StringBuffer();
    for (double d : ar) {
      s.append(d);
      s.append(", ");
    }
    if (s.length() > 0) {
      s.delete(s.length() - 2, s.length() - 1);
    } else {
      s.append("no data");
    }
    System.out.println(s);
  }
}

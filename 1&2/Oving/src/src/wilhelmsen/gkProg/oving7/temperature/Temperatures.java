package src.wilhelmsen.gkProg.oving7.temperature;

/**
 * Created by Harald on 4.10.15.
 */
public class Temperatures {

  private double[][] temps;

  public Temperatures(double[][] temps) {
    this.temps = temps;
  }

  public double[] getAvgPerDay() {
    double[] avgs = new double[temps.length];
    for (int i = 0; i < temps.length; i++) {
      avgs[i] = getAvg(temps[i]);
    }
    return avgs;
  }

  public double[] getAvgPerHour() {
    double[] avgs = new double[temps[0].length];
    for (int hour = 0; hour < avgs.length; hour++) {
      double sum = 0;
      for (int day = 0; day < temps.length; day++) {
        sum += temps[day][hour];
      }
      avgs[hour] = sum / avgs.length;
    }
    return avgs;
  }

  public double getAvgForMonth() {
    double sum = 0;
    for (double[] ar : temps) {
      for (double d : ar) {
        sum += d;
      }
    }
    return sum / (temps.length * temps[0].length);
  }

  public int getNumDaysWithAvgBetween(double start, double end) {
    if (end < start) {
      throw new IllegalArgumentException("Argument end must be larger than start! " + start + ", " + end);
    }
    int count = 0;
    for (double[] day : temps) {
      double avg = getAvg(day);
      if (avg < end && avg > start) {
        count++;
      }
    }
    return count;
  }

  private double getAvg(double[] ar) {
    double sum = 0;
    for (double d : ar) {
      sum += d;
    }
    return sum / ar.length;
  }
}

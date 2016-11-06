package src.wilhelmsen.gkProg.oving9;

/**
 * Created by Harald on 5.10.15.
 */
public class Worker {

  private final Person person;

  private int uid;
  private int hiredYear;
  private double monthlyPay;
  private double taxCut;

  public Worker(Person person, int uid, int hiredYear, double monthlyPay, double taxCut) {
    this.person = person;
    this.uid = uid;
    this.hiredYear = hiredYear;
    this.monthlyPay = monthlyPay;
    this.taxCut = taxCut; // per one
  }

  public double getYearlyTaxCut() {
    return monthlyPay * 12 * (taxCut / (12 / 10.5));
  }

  public double getGrossPerYear() {
    return monthlyPay * 12;
  }

  public double getMonthlyTaxCut() {
    return monthlyPay * taxCut;
  }

  public Person getPerson() {
    return person;
  }

  public int getUid() {
    return uid;
  }

  public int getHiredYear() {
    return hiredYear;
  }

  public double getMonthlyPay() {
    return monthlyPay;
  }

  public void setMonthlyPay(double monthlyPay) {
    this.monthlyPay = monthlyPay;
  }

  public double getTaxPercentage() {
    return taxCut * 100;
  }

  public void setTaxCut(double taxCut) {
    this.taxCut = taxCut;
  }

  public String getName() {
    return person.getFullName();
  }

  public int getYearsHired() {
    return person.getyear() - hiredYear;
  }

  public int getAge() {
    return person.getAge();
  }

  public boolean hiredForMoreThan(int years) {
    return getYearsHired() > years;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public void setHiredYear(int hiredYear) {
    this.hiredYear = hiredYear;
  }
}

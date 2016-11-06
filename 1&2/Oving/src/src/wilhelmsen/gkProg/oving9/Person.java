package src.wilhelmsen.gkProg.oving9;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Harald on 5.10.15.
 */
public final class Person {
  private String firstName;
  private String lastName;
  private int birthYear;

  public Person(String firstName, String lastName, int birthYear) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return lastName + ", " + firstName;
  }

  public int getAge() {
    return getyear() - birthYear;
  }

  public int getyear() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.YEAR);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }
}

package src.wilhelmsen.gkProg.oving10;

import java.util.ArrayList;

/**
 * Created by haraldfw on 10/15/15.
 */
public class StudentOverview {

  ArrayList<Student> students;

  public StudentOverview() {
    students = new ArrayList<>();
  }

  public int getStudentCount() {
    return students.size();
  }

  public int getAssignmentCount(String name) {
    Student s = getStudent(name);
    if (s != null) {
      return s.getAssignmentCount();
    }
    return -1;
  }

  public boolean registerStudent(String name) {
    if (getStudent(name) == null) {
      students.add(new Student(name));
      return true;
    }
    return false;
  }

  public boolean registerStudent(String name, int assigments) {
    if(registerStudent(name)) {
      incrementAssignmentCount(name, assigments);
      return true;
    }
    return false;
  }

  public boolean incrementAssignmentCount(String name, int amount) {
    Student s = getStudent(name);
    if (s != null) {
      s.incrementAssignments(amount);
      return true;
    }
    return false;
  }

  private Student getStudent(String name) {
    for (Student s : students) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("List of ")
        .append(getStudentCount())
        .append(" students:\n");
    for (Student s : students) {
      sb.append(s.toString()).append("\n");
    }
    return sb.toString();
  }
}

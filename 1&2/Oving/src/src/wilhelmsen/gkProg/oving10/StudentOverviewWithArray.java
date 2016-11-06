package src.wilhelmsen.gkProg.oving10;

import javax.swing.*;

/**
 * Created by haraldfw on 10/23/15.
 */
public class StudentOverviewWithArray {

  public Student[] students;

  public StudentOverviewWithArray() {
    students = new Student[0];
  }

  public int getStudentCount() {
    return students.length;
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
      addStudent(name);
      return true;
    }
    JOptionPane.showMessageDialog(null, "A student by that name already exists");
    return false;
  }

  public boolean registerStudent(String name, int assigments) {
    if (registerStudent(name)) {
      incrementAssignmentCount(name, assigments);
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

  public boolean incrementAssignmentCount(String name, int amount) {
    Student s = getStudent(name);
    if (s != null) {
      s.incrementAssignments(amount);
      return true;
    }
    return false;
  }

  private void addStudent(String name) {
    Student[] newStudents = new Student[students.length + 1];
    System.arraycopy(students, 0, newStudents, 0, students.length);
    newStudents[newStudents.length - 1] = new Student(name);
    students = newStudents;
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

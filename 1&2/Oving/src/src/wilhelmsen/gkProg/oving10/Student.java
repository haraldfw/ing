package src.wilhelmsen.gkProg.oving10;

/**
 * Created by haraldfw on 10/15/15.
 */
public class Student {

  private String name;
  private int assignmentCount;

  public Student(String name) {
    this.name = name;
    assignmentCount = 0;
  }

  public Student(String name, int assignmentCount) {
    this.name = name;
    this.assignmentCount = assignmentCount;
  }

  public String getName() {
    return name;
  }

  public int getAssignmentCount() {
    return assignmentCount;
  }

  public void incrementAssignments(int amount) {
    assignmentCount += amount;
  }

  @Override
  public String toString() {
    return "Name: " + name + "  Assignments completed: " + assignmentCount;
  }
}

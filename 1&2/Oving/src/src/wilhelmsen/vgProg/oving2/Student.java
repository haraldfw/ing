package src.wilhelmsen.vgProg.oving2;

/**
 * Created by Harald Wilhelmsen on 07. Jan 2015.
 */
public class Student {

	private final String name;
	private int assignmentsCompleted = 0;

	public Student(String name) {
		this.name = name;
	}

	/** May be redundant, when the toString returns the same value
	 * @return Name of the student
	 */
	public String getName() {
		return name;
	}

	public int getAssignmentsCompleted() {
		return assignmentsCompleted;
	}

	public void setAssignmentsCompleted(int assignmentsCompleted) {
		if(assignmentsCompleted < 0) throw new IllegalArgumentException("Argument cannot be smaller than 0");
		this.assignmentsCompleted = assignmentsCompleted;
	}

	public void incrementAssign(int amount) {
		assignmentsCompleted += amount;
	}

	/**
	 * Returns the name of the student. Originally returned a readable representation of the instance with name and asgm.
	 * But the class given(GodkjenningBGS) used this toString method as a way to get the name of a student, so it
	 * had to be this way.
	 * @return	Name of the student.
	 */
	@Override
	public String toString() {
		return name;
	}
}

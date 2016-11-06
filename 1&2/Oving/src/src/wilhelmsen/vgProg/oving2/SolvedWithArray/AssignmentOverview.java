package src.wilhelmsen.vgProg.oving2.SolvedWithArray;

import src.wilhelmsen.vgProg.oving2.Student;

/**
 * Created by Harald Wilhelmsen on 13. Jan 2015.
 */
class AssignmentOverview {

	private Student[] students = new Student[5];
	private int studentAmount = 0;

	public boolean addStudent(String name) {
		if(getStudentByName(name) == null) {
			if(studentAmount == students.length) expandArray();
			students[studentAmount++] = new Student(name);
			return true;
		}
		return false;
	}

	public boolean incrementAssign(String name, int amount) {
		Student s = getStudentByName(name);
		if(s == null) return false;
		s.incrementAssign(amount);
		return true;
	}

	public String[] allNames() {
		String s[] = new String[studentAmount];
		for(int i = 0; i < studentAmount; i++) {
			s[i] = students[i].getName();
		}
		return s;
	}

	public int assignmentsCompleted(String nameOfStudent) {
		System.out.println(nameOfStudent);
		Student s = getStudentByName(nameOfStudent);
		System.out.println(s);
		if(s == null) return -1;

		return s.getAssignmentsCompleted();
	}

	private Student getStudentByName(String name) {
		for(int i = 0; i < studentAmount; i++) {
			Student s = students[i];
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	private void expandArray() {
		Student[] newArray = new Student[students.length + 5];
		for(int i = 0; i < students.length; i++)
			newArray[i] = students[i];
		students = newArray;
	}

	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < studentAmount; i++) s += students[i].getName() + " has solved " + students[i].getAssignmentsCompleted() + " assignments.\n";
		return s;
	}

	public int getStudentAmount() {
		return studentAmount;
	}
}

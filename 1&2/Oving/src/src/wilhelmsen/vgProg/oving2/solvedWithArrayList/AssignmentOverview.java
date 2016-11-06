package src.wilhelmsen.vgProg.oving2.solvedWithArrayList;

import src.wilhelmsen.vgProg.oving2.Student;

import java.util.ArrayList;

/**
 * Created by Harald Wilhelmsen on 13. Jan 2015.
 */
class AssignmentOverview {

	private ArrayList<Student> students = new ArrayList<Student>();

	public boolean addStudent(String name) {
		if(getStudentByName(name) == null) {
			students.add(new Student(name));
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
		String s[] = new String[students.size()];
		int i = 0;
		for(Student st : students)
			s[i++] = st.getName();
		return s;
	}

	public int assignmentsCompleted(String nameOfStudent) {
		Student s = getStudentByName(nameOfStudent);
		if(s != null) return s.getAssignmentsCompleted();
		return -1;
	}

	private Student getStudentByName(String name) {
		for(Student s : students) {
			if(s.toString().equals(name)) return s;
		}
		return null;
	}

	@Override
	public String toString() {
		String s = "";
		for(Student st : students) s += st.getName() + " has solved " + st.getAssignmentsCompleted() + " assignments.\n";
		return s;
	}

	public int getStudentAmount() {
		return students.size();
	}
}

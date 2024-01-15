package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CHUCDY
 */
public class StudentList {

    private List<Student> students;

    public StudentList() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public StudentList(List<Student> students) {
        this.students = students;
    }

    public List<Student> searchByName(String name) {
        List<Student> studentResult = new ArrayList<>();
        for (Student student : students) {
            if (student.getsName().contains(name)) {
                studentResult.add(student);
            }
        }
        Collections.sort(studentResult);
        return studentResult;
    }

    public Student searchById(int id) {
        for (Student student : students) {
            if (student.getsID() == id) {
                return student;
            }
        }
        return null;
    }

    public String studentName(int index) {
        return students.get(index).getsName();
    }

    public void delete(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getsID() == id) {
                students.remove(i);
            }
        }
    }

    public int size() {
        return students.size();
    }

    public void report() {
        // List to store the count of each course for each student
        ArrayList<StudentWCourseCounter> st = new ArrayList<>();

        for (Student student : students) {
            String studentName = student.getsName();
            String courseName = student.getCourse();

            // Check if the student-course combination is already in the list
            boolean found = false;
            for (StudentWCourseCounter scCount : st) {
                if (scCount.getStudentName().equals(studentName) && scCount.getCourseName().equals(courseName)) {
                    scCount.incrementCount();
                    found = true;
                    break;
                }
            }

            // If not found, add a new entry to the list
            if (!found) {
                st.add(new StudentWCourseCounter(studentName, courseName));
            }
        }

        // Print the results
        for (StudentWCourseCounter scCount : st) {
            System.out.println(scCount);
        }
    }

    private static class StudentWCourseCounter {

        private String studentName;
        private String courseName;
        private int count;

        public StudentWCourseCounter(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
            this.count = 1;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void incrementCount() {
            count++;
        }

        @Override
        public String toString() {
            return "| " + this.studentName + " | " + this.courseName + " | " + this.count + " |";
        }
    }
}

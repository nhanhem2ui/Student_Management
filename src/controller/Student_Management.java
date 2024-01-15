package controller;

import model.StudentList;
import View.Menu;
import java.util.List;
import java.util.Scanner;
import model.Student;

public class Student_Management extends Menu<String> {

    private StudentList studentlist;
    Scanner scanner = new Scanner(System.in);

    public Student_Management() {
        super("Students Management", new String[]{"Create", "Find and Sort", "Update/Delete", "Report", "Exit"});
        studentlist = new StudentList();
    }

    @Override
    public void execute(int n) {
        Student linh = new Student(1, "linh", 2, "C/C++");
        Student thor = new Student(2, "thor", 2, "java");
        studentlist.addStudent(linh);
        studentlist.addStudent(thor);
        switch (n) {
            case 1:
                create();
                break;
            case 2:
                findAndSort();
                break;
            case 3:
                updateOrDel();
                break;
            case 4:
                report();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection!");
                break;
        }
    }

    public static void main(String[] args) {

        Student_Management studentManagement = new Student_Management();
        studentManagement.run();

    }

    public void create() {
        int studentCount = 0;

        while (true) {
            Student student = inputInfo();
            Student checkStudent = studentlist.searchById(student.getsID());

            if (checkStudent != null) {
                System.out.println("Id input already exists");
                continue;
            }

            studentCount++;
            studentlist.addStudent(student);

            if (studentCount >= 2) {
                System.out.print("Do you want to continue (Y/N)? ");
                String input = scanner.nextLine();

                if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                    System.out.println("Invalid input. Exiting program.");
                    break;
                }

                if (input.equalsIgnoreCase("n")) {
                    System.out.println("Program ended. Created " + studentCount + " students.");
                    break;
                }
            }
        }
    }

    private Student inputInfo() {
        System.out.print("Enter student ID: ");
        int sID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student name: ");
        String sName = scanner.nextLine();

        System.out.print("Enter student semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();

        String course;
        while (true) {
            System.out.print("Enter course name (Java, C/C++, .Net): ");
            course = scanner.nextLine();

            if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("C/C++") || course.equalsIgnoreCase(".Net")) {
                break;
            } else {
                System.out.println("Invalid course name. Please enter Java, C/C++, or .Net.");
            }
        }
        return new Student(sID, sName, semester, course);
    }

    private Student updateStudent(int id) {
        System.out.print("Enter student name: ");
        String sName = scanner.nextLine();
        System.out.print("Enter student semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter course name: ");
        String course = scanner.nextLine();

        return new Student(id, sName, semester, course);
    }

    private void findAndSort() {
        System.out.println("Input student's name to search: ");
        String studentName = scanner.nextLine();
        List<Student> studentFind = studentlist.searchByName(studentName);
        if (studentFind == null || studentFind.isEmpty()) {
            System.out.println("student not found");
        } else {
            for (Student student : studentFind) {
                System.out.println(student.toString());
            }
        }
    }

    private void updateOrDel() {
        int id = scanner.nextInt();
        Student studentSearched = studentlist.searchById(id);
        if (studentSearched == null) {
            System.out.println("Student not found");
            return;
        }
        scanner.nextLine();
        System.out.println("Do you want to update (U) or delete (D) student?");
        String choose = scanner.nextLine();

        if (choose.equalsIgnoreCase("d")) {
            studentlist.delete(id);
        }

        if (choose.equalsIgnoreCase("u")) {
            Student studentUpdate = updateStudent(id);
            studentlist.delete(id);
            studentlist.addStudent(studentUpdate);
        }

        if (choose.equalsIgnoreCase("d") && choose.equalsIgnoreCase("u")) {
            System.out.println("Invalid input. Exiting program.");
        }
    }

    private void report() {
        studentlist.report();
    }
}


package model;

/**
 *
 * @author CHUCDY
 */
public class Student implements Comparable<Student>{
    private int sID;
    private String sName;
    private int semester;
    private String course;

    public Student(int sID, String sName, int semester, String course) {
        this.sID = sID;
        this.sName = sName;
        this.semester = semester;
        this.course = course;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "| " + this.sName + " | " + this.semester + " | " + this.course + " |";
    }
   
    @Override
    public int compareTo(Student o) {
       return this.sName.compareTo(o.sName);
    } 
    
}

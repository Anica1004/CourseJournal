package model;

import java.util.ArrayList;
import java.util.List;

// This represents the student (user) with student's name, university name,
// list of first, second, third, and fourth year courses, and list of all courses
// that the student has taken in the university

public class Student {
    private String studentName;
    private ArrayList<Course> firstYearCourses;
    private ArrayList<Course> secondYearCourses;
    private ArrayList<Course> thirdYearCourses;
    private ArrayList<Course> fourthYearCourses;
    private List<List<Course>> listOfCourses;

    public Student() {
        studentName = "user";
        firstYearCourses = new ArrayList<>();
        secondYearCourses = new ArrayList<>();
        thirdYearCourses = new ArrayList<>();
        fourthYearCourses = new ArrayList<>();
        listOfCourses = new ArrayList<>();
        listOfCourses.add(firstYearCourses);
        listOfCourses.add(secondYearCourses);
        listOfCourses.add(thirdYearCourses);
        listOfCourses.add(fourthYearCourses);
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void addFirstYearCourses(Course course) {
        firstYearCourses.add(course);
    }

    public void addSecondYearCourses(Course course) {
        secondYearCourses.add(course);
    }

    public void addThirdYearCourses(Course course) {
        thirdYearCourses.add(course);
    }

    public void addFourthYearCourses(Course course) {
        fourthYearCourses.add(course);
    }

    public int calculateAverage(ArrayList<Course> courses) {
        int totalGrade = 0;
        int count = 0;
        for (Course c : courses) {
            totalGrade += c.getFinalMark();
            count++;
        }

        return totalGrade / count;

    }


}

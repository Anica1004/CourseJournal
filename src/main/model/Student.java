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
    private List<List<Course>> listOfCourses = new ArrayList<>();

    public Student() {
      studentName = "user";
      firstYearCourses = new ArrayList<>();
      secondYearCourses = new ArrayList<>();
      thirdYearCourses = new ArrayList<>();
      fourthYearCourses = new ArrayList<>();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setFirstYearCourses(Course course) {
        firstYearCourses.add(course);
    }








}

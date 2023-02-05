package model;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
// This represents the student (user) with student's name, university name,
// list of first, second, third, and fourth year courses, and list of all courses
// that the student has taken in the university

public class Student {
    private List<Course> firstYearCourses;
    private List<Course> secondYearCourses;
    private List<Course> thirdYearCourses;
    private List<Course> fourthYearCourses;
    private List<List<Course>> listOfListOfCourses;


    public Student() {
        firstYearCourses = new ArrayList<>();
        secondYearCourses = new ArrayList<>();
        thirdYearCourses = new ArrayList<>();
        fourthYearCourses = new ArrayList<>();
        listOfListOfCourses = new ArrayList<>();
        listOfListOfCourses.add(firstYearCourses);
        listOfListOfCourses.add(secondYearCourses);
        listOfListOfCourses.add(thirdYearCourses);
        listOfListOfCourses.add(fourthYearCourses);
    }


    // REQUIRES: there must be at least one element in the list of courses
    // EFFECTS:  calculates the average percentage grade of the user courses
    // taken in a particular year

    public double calculateAverage(List<Course> courses) {
        double totalGrade = 0;
        double totalCredit = totalCredit(courses);
        for (Course c : courses) {
            double finalMark = c.getFinalMark();
            int credit = c.getCredit();
            totalGrade += finalMark * credit;
        }
        return totalGrade / totalCredit;
    }

    // REQUIRES: at least one element in the list of courses
    // EFFECTS: calculates the total credits taken in the particular year
    public int totalCredit(List<Course> courses) {
        int credit = 0;
        for (Course course : courses) {
            credit += course.getCredit();
        }
        return credit;
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

    public List<Course> getFirstYearCourses() {
        return firstYearCourses;
    }

    public List<Course> getSecondYearCourses() {
        return secondYearCourses;
    }

    public List<Course> getThirdYearCourses() {
        return thirdYearCourses;
    }

    public List<Course> getFourthYearCourses() {
        return fourthYearCourses;
    }

    public List<List<Course>> getListOfListOfCourses() {
        return listOfListOfCourses;
    }


}

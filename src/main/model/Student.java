package model;

import java.util.ArrayList;
import java.util.List;

// This represents the student (user) with student's name, university name,
// list of first, second, third, and fourth year courses, and list of all courses
// that the student has taken in the university

public class Student {
    private ArrayList<Course> firstYearCourses;
    private ArrayList<Course> secondYearCourses;
    private ArrayList<Course> thirdYearCourses;
    private ArrayList<Course> fourthYearCourses;
    private List<List<Course>> listOfListOfCourses;
    private int firstYearAverage;
    private int secondYearAverage;
    private int thirdYearAverage;
    private int fourthYearAverage;


    public Student() {
        firstYearCourses = new ArrayList<>();
        secondYearCourses = new ArrayList<>();
        thirdYearCourses = new ArrayList<>();
        fourthYearCourses = new ArrayList<>();
        firstYearAverage = 0;
        secondYearAverage = 0;
        thirdYearAverage = 0;
        fourthYearAverage = 0;
        listOfListOfCourses = new ArrayList<>();
        listOfListOfCourses.add(firstYearCourses);
        listOfListOfCourses.add(secondYearCourses);
        listOfListOfCourses.add(thirdYearCourses);
        listOfListOfCourses.add(fourthYearCourses);
    }


// REQUIRES: there must be at least one element in the list of courses
    // EFFECTS:  calculates the average percentage grade of the user courses
    // taken in a particular year

    public int calculateAverage(ArrayList<Course> courses) {
        int totalGrade = 0;
        int count = 0;
        for (Course c : courses) {
            totalGrade += c.getFinalMark();
            count++;
        }
        return totalGrade / count;
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

    public ArrayList<Course> getFirstYearCourses() {
        return firstYearCourses;
    }

    public ArrayList<Course> getSecondYearCourses() {
        return secondYearCourses;
    }

    public ArrayList<Course> getThirdYearCourses() {
        return thirdYearCourses;
    }

    public ArrayList<Course> getFourthYearCourses() {
        return fourthYearCourses;
    }

    public List<List<Course>> getListOfListOfCourses() {
        return listOfListOfCourses;
    }

    public int getFirstYearAverage() {
        return firstYearAverage;
    }

    public int getSecondYearAverage() {
        return secondYearAverage;
    }

    public int getThirdYearAverage() {
        return thirdYearAverage;
    }

    public int getFourthYearAverage() {
        return fourthYearAverage;
    }




}

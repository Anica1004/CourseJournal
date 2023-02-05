package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Course;
import model.Student;

public class FrontDesk {
    private Student user;
    private Scanner keys;

    public void startProgram() {
        user = new Student();
        keys = new Scanner(System.in);
        displayOptions();

    }

    // EFFECTS: displays the program options,
    // including quitting, adding, removing, or viewing courses
    public void displayOptions() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Hello user, \nselect a number from below to fulfill your needs!:");
        System.out.println("\t1. Add Course");
        System.out.println("\t2. Remove Course");
        System.out.println("\t3. View Courses");
        System.out.println("\t4. Quit");
        System.out.println("-------------------------------------------------------------------");
        navigateOptions();
    }

    public void viewCourse() {
        System.out.println("Input the undergraduate year (1, 2, 3, or 4) you want to check!: ");
        int year = keys.nextInt();
        if (year == 1) {
            displayListOfCourse(user.getFirstYearCourses());

        } else if (year == 2) {
            displayListOfCourse(user.getSecondYearCourses());

        } else if (year == 3) {
            displayListOfCourse(user.getThirdYearCourses());

        } else if (year == 4) {
            displayListOfCourse(user.getFourthYearCourses());

        }

    }

    public void displayListOfCourse(ArrayList<Course> courses){
        for (Course course: courses) {

        }


    }


    // EFFECTS: Navigates user to the different program options
    //          depending on user's decision
    public void navigateOptions() {

        int userInput = keys.nextInt();
        if (userInput == 1) {
            System.out.println("You have chosen to add a course!");
            addCourse();
        } else if (userInput == 2) {
            System.out.println("You have chosen to remove a course!");
            removeCourseWelcoming();
        } else if (userInput == 3) {
            System.out.println("You have chosen to view a course!");
        } else if (userInput == 4) {
            System.out.println("You have chosen to quit.");
            System.out.println("Come back again!");
        } else {
            System.out.println("Please input a number between 1 and 4.");
            displayOptions();
        }
    }

    public void removeCourseWelcoming() {
        System.out.println("Be sure to input the exact information without any spelling errors or extra space.");
        String courseName = askCourseName();
        courseName = keys.nextLine();
        int year = askYear();
        findListOfCourse(courseName, year);


    }

    public void removeCourse(Course course, ArrayList<Course> courses) {
        if (course == null) {
            displayOptions();
        } else {
            if (courseConfirmation(course.getCourseName(), course.getProfessorName(),
                    course.getCredit(), course.getYear(),
                    course.getFinalMark(), course.getTerm(), course.getCourseSummary())) {

                courses.remove(course);
                System.out.println("The course " + course.getCourseName() + " was successfully removed!");
                System.out.println("We will escort you back to the front desk.");
                displayOptions();
            } else {
                System.out.println("Sorry about the inconvenience.");
                System.out.println("We will will find and reconfirm the course again.");
                removeCourseWelcoming();
            }

        }
    }


    // MODIFIES: this
    // EFFECTS: finds the course within the list of courses
    public void findListOfCourse(String courseName, int year) {
        if (year == 1) {
            removeCourse(findCourse(courseName, user.getFirstYearCourses()), user.getFirstYearCourses());

        } else if (year == 2) {
            removeCourse(findCourse(courseName, user.getSecondYearCourses()), user.getSecondYearCourses());

        } else if (year == 3) {
            removeCourse(findCourse(courseName, user.getThirdYearCourses()), user.getThirdYearCourses());
        } else {
            removeCourse(findCourse(courseName, user.getFourthYearCourses()), user.getFourthYearCourses());

        }
    }


    // MODIFIES: this
    // EFFECTS: finds the given course in the list of courses
    public Course findCourse(String courseName, ArrayList<Course> courses) {
        for (Course course : courses) {
            if (courseName.equalsIgnoreCase(course.getCourseName())) {
                return course;
            }
        }
        System.out.println("We were unable to find the course");
        System.out.println("Sorry about that. Be sure to check your spelling next time.");
        System.out.println("We will escort you back to the front desk.");
        return null;

    }

    // MODIFIES: this
    // EFFECTS: adds a course to the user's list of courses
    public void addCourse() {
        askCourseName();
        String courseName = keys.nextLine();
        String professorName = askProfessorName();
        int credit = askCredit();
        int year = askYear();
        double finalMark = askFinalMark();
        int term = askTerm();
        askCourseSummary();
        String courseSummary = keys.nextLine();

        if (courseConfirmation(courseName,
                professorName, credit, year,
                finalMark, term, courseSummary)) {
            Course newCourse = new Course(courseName, professorName, credit, year, finalMark, term, courseSummary);
            sortCourse(newCourse, year);
            System.out.println("We have successfully added your course.");
            System.out.println("We will escort you back to the front desk.");
            displayOptions();

        } else {
            System.out.println("Sorry for the inconvenience, we will reconfirm the course information");
            addCourse();
        }

    }

    // MODIFIES: this
    // EFFECTS: sorts the course by adding the course to the
    // list of courses taken in the particular, undergraduate year that the user has specified

    public void sortCourse(Course newCourse, int year) {
        if (year == 1) {
            user.addFirstYearCourses(newCourse);
        } else if (year == 2) {
            user.addSecondYearCourses(newCourse);
        } else if (year == 3) {
            user.addThirdYearCourses(newCourse);
        } else if (year == 4) {
            user.addFourthYearCourses(newCourse);
        }
    }


    // EFFECTS: confirms with user if the course information is correct
    //          returns true if it is; otherwise false
    public boolean courseConfirmation(String courseName, String professorName, int credit, int year,
                                      double finalMark, int term, String courseSummary) {
        System.out.println("Is the following information correct?");
        System.out.println("\tCourse Name: " + courseName);
        System.out.println("\tProfessor Name: " + professorName);
        System.out.println("\tCredit : " + credit + " credits");
        System.out.println("\tYear: Year " + year);
        System.out.println("\tFinal Mark " + finalMark + " %");
        System.out.println("\tTerm: Term " + term);
        System.out.println("\tCourse Description: " + courseSummary);
        System.out.println("Input 'confirm' if it is correct");
        System.out.println("Input 'resubmit' if it is incorrect");

        String userConfirmation;
        userConfirmation = keys.next();
        if (userConfirmation.equalsIgnoreCase("confirm")) {
            return true;
        } else {
            return false;
        }


    }


    // EFFECTS: ask user for course name and returns input
    public String askCourseName() {
        System.out.println("Input the name of the course:");
        String courseName;
        courseName = keys.nextLine();
        return courseName;
    }

    // EFFECTS: asks user for professor name returns input
    public String askProfessorName() {
        System.out.println("Input your professor's name for this course:");
        String professorName;
        professorName = keys.nextLine();
        return professorName;
    }

    // EFFECTS: asks user for number of credits and returns input
    public int askCredit() {
        System.out.println("Input the number of credits of the course:");
        int creditNum;
        creditNum = keys.nextInt();
        return creditNum;
    }

    // REQUIRES: user input year must be 1, 2, 3, or 4
    // EFFECTS: asks user for year taken the course and returns input
    public int askYear() {
        int year = 0;
        boolean exactYear = true;
        while (exactYear) {
            System.out.println("Input the undergraduate year you have taken the course (1, 2, 3, or 4):");
            year = keys.nextInt();
            if (year == 1 || year == 2 || year == 3 || year == 4) {
                exactYear = false;
            } else {
                System.out.println("Please input a year that is either 1, 2, 3, or 4");
            }
        }
        return year;
    }

    // REQUIRES: final mark >= 0
    // EFFECTS: asks user for the final mark achieved in the course and returns input
    public double askFinalMark() {
        System.out.println("Input your final mark for this course in percentage:");
        double userMark;
        userMark = keys.nextDouble();
        return userMark;
    }

    // REQUIRES: must be either 1 or 2
    // EFFECTS: asks user for term number the course was taken and returns input
    public int askTerm() {
        int term = 0;
        boolean state = true;
        while (state) {
            System.out.println("Input the term you have take the course (1 or 2):");
            term = keys.nextInt();
            if (term == 1 || term == 2) {
                state = false;
            } else {
                System.out.println("Please input a value that is either 1 or 2");

            }
        }
        return term;
    }

    // EFFECTS: ask user for a course description and returns input
    public String askCourseSummary() {
        System.out.println("Please describe the course based on your personal experience:");
        String courseSummary;
        courseSummary = keys.nextLine();
        return courseSummary;

    }


}









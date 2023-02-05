package ui;

import java.util.Scanner;

import model.Course;
import model.Student;

public class FrontDesk {
    private Student user = new Student();
    private Scanner keys = new Scanner(System.in);


    // EFFECTS: displays the program options,
    // including quitting, adding, removing, or viewing courses
    public void displayOptions() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Hello user, \nselect a number from below to find your needs!:");
        System.out.println("\t1. Add Course");
        System.out.println("\t2. Remove Course");
        System.out.println("\t3. View Courses");
        System.out.println("\t4. Quit");
        System.out.println("-------------------------------------------------------------------");
        navigateOptions();
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
                finalMark, term, courseSummary).equalsIgnoreCase("confirm")) {
            Course newCourse = new Course(courseName, professorName, credit, year, finalMark, term, courseSummary);
            sortCourse(newCourse, year);
            System.out.println("We have successfully added your course.");

        } else {
            System.out.println("Sorry for the inconvenience, we will reconfirm the course information");
            addCourse();
        }

    }

    public void sortCourse(Course newCourse, int year) {
        if (year == 1) {

        }
    }


    // EFFECTS: confirms with user if the course information is correct
    //          returns true if it is; otherwise false
    public String courseConfirmation(String courseName, String professorName, int credit, int year,
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
        return userConfirmation;

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









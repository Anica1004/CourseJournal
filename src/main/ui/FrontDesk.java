package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Course;
import model.Student;

// The frontDesk where users interact with the program, and navigate to find their needs,
// including adding a course, removing a course, viewing courses taken in a particular year,
//  and the overall grade summary.

public class FrontDesk {
    private Student user;
    private Scanner keys;

    // EFFECTS: starts the program running
    public void startProgram() {
        user = new Student();
        keys = new Scanner(System.in);
        displayOptions();

    }

    // EFFECTS: displays the program options,
    // including adding a course, removing a course, viewing courses,
    // viewing grade summary, and quitting
    public void displayOptions() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Hello user, \nselect a number from below to fulfill your needs!:");
        System.out.println("\t1. Add Course");
        System.out.println("\t2. Remove Course");
        System.out.println("\t3. View Courses");
        System.out.println("\t4. Grade Summary");
        System.out.println("\t5. Quit");
        System.out.println("-------------------------------------------------------------------");
        navigateOptions();
    }

    // EFFECTS: Navigates user to the different program options
    //          depending on user's decision, which may add, remove, view courses, or
    //          view grade summary
    // MODIFIES: this
    public void navigateOptions() {

        int userInput = keys.nextInt();
        if (userInput == 1) {
            System.out.println("You have chosen to add a course!");
            addCourse();
        } else if (userInput == 2) {
            System.out.println("You have chosen to remove a course!");
            removeCourseWelcoming();
        } else if (userInput == 3) {
            System.out.println("You have chosen to view courses!");
            viewCourse();
        } else if (userInput == 4) {
            System.out.println("You have chosen to view your grade summary!");
            gradeSummary();


        } else if (userInput == 5) {
            System.out.println("You have chosen to quit.");
            System.out.println("Come back again!");
        } else {
            System.out.println("Please input a number between 1 and 4.");
            displayOptions();
        }
    }

    // EFFECTS: displays all the courses that the user has taken in a particular year
    public void viewCourse() {
        optionsViewCourse();
        int year = keys.nextInt();
        if (year == 1) {
            viewCourses(year, user.getFirstYearCourses());

        } else if (year == 2) {
            viewCourses(year, user.getSecondYearCourses());

        } else if (year == 3) {
            viewCourses(year, user.getThirdYearCourses());

        } else if (year == 4) {
            viewCourses(year, user.getFourthYearCourses());

        } else {
            System.out.println("Please select a number 1, 2, 3, or 4");
            viewCourse();
        }

    }

    // EFFECTS: displays the options of course information based on undergraduate year
    public void optionsViewCourse() {
        System.out.println("Choose one of the options below: ");
        System.out.println("\t1. Course Information of Year 1: ");
        System.out.println("\t2. Course Information of Year 2: ");
        System.out.println("\t3. Course Information of Year 3: ");
        System.out.println("\t4. Course Information of Year 4: ");
    }


    // EFFECTS: displays the grade summary of each year (1, 2, 3, and 4)
    public void gradeSummary() {

        int year = 1;
        List<Course> emptyList = new ArrayList<>();
        for (List<Course> courses : user.getListOfListOfCourses()) {
            if (emptyList.equals(courses)) {
                System.out.println("\n======================================================================");
                System.out.println("A summary for Year " + year + " is not yet available.");
                System.out.println("Not enough courses were added.");
                System.out.println("Hopefully you add more courses in the near future for Year " + year + "!");
                System.out.println("======================================================================");

            } else {
                String average = user.calculateAverage(courses);
                String letterGrade = user.letterGrade(Double.parseDouble(average));
                System.out.println("\n======================================================================");
                System.out.println("Grade Summary of Year " + year + ":");
                System.out.println("\tYear " + year + " Average Percentage: " + average + " %");
                System.out.println("\tYear " + year + " Average Letter Grade: " + letterGrade);
                System.out.println("\tYear " + year + " Total Credit: " + user.totalCredit(courses));
                System.out.println("======================================================================");
            }
            year++;

        }
        backToLobby();

    }

    // EFFECTS: displays the courses taken in the specified year
    public void viewCourses(int year, List<Course> courses) {
        ArrayList<Course> emptyList = new ArrayList<>();
        if (emptyList.equals(courses)) {
            System.out.println("\nYou have not yet added any courses in Year " + year + ".");
            System.out.println("Hopefully you add courses in the near future!");
            backToLobby();

        } else {
            System.out.println("\nThe below are courses you have taken in Year " + year + ":");
            displayListOfCourse(courses);
            System.out.println("That must have been a fantastic year!");
            backToLobby();
        }
    }

    // EFFECTS: asks user to go back to the lobby; otherwise quit program
    public void backToLobby() {
        System.out.println("Would you like to go back to the front desk?");
        System.out.println("Input 'Y' for yes and 'N' for no");
        String userInput = keys.next();
        if (userInput.equalsIgnoreCase("Y")) {
            System.out.println("I will escort you to the front desk!");
            displayOptions();
        } else {
            System.out.println("See you again!");
        }
    }

    // EFFECTS: displays all the list of courses taken in the particular year,
    // ordered from term 1 to term 2
    public void displayListOfCourse(List<Course> courses) {
        for (Course course : courses) {
            if (course.getTerm() == 1) {
                System.out.println("\n---------------------------------------------------------------------");
                courseInformation(course.getCourseName(), course.getProfessorName(), course.getCredit(),
                        course.getYear(), course.getFinalMark(), course.getTerm(), course.getRating(),
                        course.getCourseSummary());
                System.out.println("---------------------------------------------------------------------");
            }
        }
        for (Course course : courses) {
            if (course.getTerm() == 2) {
                System.out.println("\n---------------------------------------------------------------------");
                courseInformation(course.getCourseName(), course.getProfessorName(), course.getCredit(),
                        course.getYear(), course.getFinalMark(), course.getTerm(),
                        course.getRating(), course.getCourseSummary());
                System.out.println("---------------------------------------------------------------------");
            }
        }


    }

    // EFFECTS: welcomes the user to remove a course, and asks for input values, specifying which
    // course to remove
    public void removeCourseWelcoming() {
        System.out.println("Be sure to input the exact information without any spelling errors or extra space.");
        askCourseName();
        String courseName = keys.nextLine();
        int year = askYear();
        findListOfCourse(courseName, year);


    }

    // MODIFIES: this
    // EFFECTS: removes the specified course if found and confirmed; otherwise navigate user
    public void removeCourse(Course course, List<Course> courses) {
        if (course == null) {
            System.out.println("We were unable to find the course");
            System.out.println("Sorry about that. Be sure to check your spelling and correct year next time.");
            backToLobby();
        } else {
            if (courseConfirmation(course.getCourseName(), course.getProfessorName(),
                    course.getCredit(), course.getYear(),
                    course.getFinalMark(), course.getTerm(), course.getRating(),
                    course.getCourseSummary())) {

                courses.remove(course);
                System.out.println("The course " + course.getCourseName() + " was successfully removed!");
                backToLobby();
            } else {
                System.out.println("Sorry about the inconvenience.");
                System.out.println("We will will find and reconfirm the course again.");
                removeCourseWelcoming();
            }

        }
    }

    // REQUIRES: year must be 1, 2, 3, or 4
    // MODIFIES: this
    // EFFECTS: finds the course within the list of courses, navigating to remove it
    public void findListOfCourse(String courseName, int year) {
        if (year == 1) {
            removeCourse(user.findCourse(courseName, user.getFirstYearCourses()), user.getFirstYearCourses());

        } else if (year == 2) {
            removeCourse(user.findCourse(courseName, user.getSecondYearCourses()), user.getSecondYearCourses());

        } else if (year == 3) {
            removeCourse(user.findCourse(courseName, user.getThirdYearCourses()), user.getThirdYearCourses());
        } else {
            removeCourse(user.findCourse(courseName, user.getFourthYearCourses()), user.getFourthYearCourses());

        }
    }


    // MODIFIES: this
    // EFFECTS: adds a course to the user's list of courses
    public void addCourse() {
        askCourseName();
        String courseName = keys.nextLine();
        String professorName = askProfessorName();
        int year = askYear();
        int term = askTerm();
        double finalMark = askFinalMark();
        int credit = askCredit();
        double courseRating = askCourseRating();
        askCourseSummary();
        String courseSummary = keys.nextLine();

        if (courseConfirmation(courseName,
                professorName, credit, year,
                finalMark, term, courseRating, courseSummary)) {
            Course newCourse = new Course(courseName, professorName, credit, year,
                    finalMark, term, courseRating, courseSummary);
            user.sortCourse(newCourse, year);
            System.out.println("We have successfully added your course.");
            backToLobby();

        } else {
            System.out.println("Sorry for the inconvenience, we will reconfirm the course information");
            addCourse();
        }

    }

    // REQUIRES: year must be 1, 2, 3, or 4,  credit > 0, final mark > 0, term must be 1 or 2,
    //            snd 0<= course rating <= 10
    // EFFECTS: displays a course's information including course name, the professor that taught
    //// the course, course credit, the undergraduate year (1, 2, 3, or 4) and term (1 or 2)
    //// that the user has taken the course
    public void courseInformation(String courseName, String professorName, int credit, int year,
                                  double finalMark, int term, double courseRating, String courseSummary) {

        System.out.println("\tCourse Name: " + courseName);
        System.out.println("\tProfessor Name: " + professorName);
        System.out.println("\tYear: Year " + year);
        System.out.println("\tTerm: Term " + term);
        System.out.println("\tFinal Mark: " + finalMark + " %");
        System.out.println("\tCredit : " + credit + " credits");
        System.out.println("\tCourse Rating: " + courseRating + " out of 10");
        System.out.println("\tCourse Description: " + courseSummary);

    }
    // REQUIRES: year must be 1, 2, 3, or 4,  credit > 0, final mark > 0, term must be 1 or 2,
    //           and  0<= course rating <= 10
    // EFFECTS: confirms with user if the course information is correct
    //          returns true if it is; otherwise false
    public boolean courseConfirmation(String courseName, String professorName, int credit, int year,
                                      double finalMark, int term, double courseRating, String courseSummary) {
        System.out.println("Is the following information correct?");
        courseInformation(courseName, professorName, credit, year,
                finalMark, term, courseRating, courseSummary);
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

    // EFFECTS: ask user for course rating out of 10 and returns input
    public double askCourseRating() {
        double courseRating = -10;
        boolean state = true;
        while (state) {
            System.out.println("Rate the overall quality of the course out of 10:");
            courseRating = keys.nextDouble();
            if (courseRating >= 0 && courseRating <= 10) {
                state = false;
            } else {
                System.out.println("Please input a rating that is in between 1 to 10");
            }
        }
        return courseRating;
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


    // EFFECTS: asks user for the final mark achieved in the course and returns input
    public double askFinalMark() {
        System.out.println("Input your final mark for this course in percentage:");
        double userMark;
        userMark = keys.nextDouble();
        return userMark;
    }


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









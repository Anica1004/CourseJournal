package ui.gui;

import model.Student;
import persistence.JsonLoader;
import persistence.JsonSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


// The FrontDeskGui where users interact with the program, and navigate to find their needs,
// including adding a course, removing a course, viewing courses taken in a particular year,
//  and the overall grade summary, in a graphic user interface
public class FrontDeskGui implements ActionListener {
    private static final String FILENAME = "./data/student.json";
    private Student user;
    private Scanner keys;
    private JsonSaver jsonSaver;
    private JsonLoader jsonLoader;


    // below are fields related to gui:
    private JFrame userFrame;

    private Panel addCoursePanel;
    private Panel removeCoursePanel;
    private Panel viewCoursePanel;
    private Panel gradeSummaryPanel;
    private Panel otherPanel;
    private Panel mainPanel;

    private JButton addCourseButton;
    private JButton removeCourseButton;
    private JButton viewCourseButton;
    private JButton gradeSummaryButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;


    private JTextField inputCourseName;
    private JTextField inputProfessorName;
    private JTextField inputCredit;
    private JTextField inputYear;
    private JTextField inputFinalMark;
    private JTextField inputTerm;
    private JTextField inputRating;
    private JTextArea inputCourseSummary;

    private Panel coursePanel;


    public void initializeGui() {
        user = new Student();
        jsonSaver = new JsonSaver(FILENAME);
        jsonLoader = new JsonLoader(FILENAME);

        userFrame = new FrontFrame();
        addCoursePanel = new Panel();
        removeCoursePanel = new Panel();
        viewCoursePanel = new Panel();
        gradeSummaryPanel = new Panel();
        otherPanel = new Panel();
    }

    // EFFECTS: starts the program running
    // MODIFIES: this
    public void startProgram() {
        initializeGui();
        addAddCoursePanel();
        addRemoveCoursePanel();
        addViewCoursePanel();
        addGradeSummaryPanel();
        addOtherPanel();

        mainPanel = new Panel();



        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(addCoursePanel);
        mainPanel.add(removeCoursePanel);
        mainPanel.add(viewCoursePanel);
        mainPanel.add(gradeSummaryPanel);
        mainPanel.add(otherPanel);
        userFrame.add(mainPanel);
        userFrame.setVisible(true);


       /* user = new Student();
        keys = new Scanner(System.in);
        jsonSaver = new JsonSaver(FILENAME);
        jsonLoader = new JsonLoader(FILENAME);
        displayOptions();
*/

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object userClick = e.getSource();
        if (userClick == addCourseButton) {
            addCourseNavigation();
            //userFrame.getContentPane().removeAll();

        } else if (userClick == removeCourseButton) {

        } else if (userClick == viewCourseButton) {

        } else if (userClick == gradeSummaryButton) {

        } else if (userClick == saveButton) {

        } else if (userClick == loadButton) {

        } else if (userClick == quitButton) {

        }

    }

    public void addCourseNavigation() {
        userFrame.dispose();
        userFrame = new AddCourseFrame();
        addCourseFrame();
    }

    public void setInputs() {
        inputCourseName = new JTextField();
        inputCourseName.setPreferredSize(new Dimension(180, 40));

        inputProfessorName = new JTextField();
        inputProfessorName.setPreferredSize(new Dimension(180, 40));

        inputCredit = new JTextField();
        inputCredit.setPreferredSize(new Dimension(180, 40));

        inputYear = new JTextField();
        inputYear.setPreferredSize(new Dimension(180, 40));

        inputFinalMark = new JTextField();
        inputFinalMark.setPreferredSize(new Dimension(180, 40));

        inputTerm = new JTextField();
        inputTerm.setPreferredSize(new Dimension(180, 40));

        inputRating = new JTextField();
        inputRating.setPreferredSize(new Dimension(180, 40));

        inputCourseSummary = new JTextArea();
        inputCourseSummary.setPreferredSize(new Dimension(230, 100));
    }




    public JPanel courseLabelPanel() {
        JLabel courseNameLabel = makeInputLabel("Course Name");
        JPanel courseLabelPanel = new JPanel();
        courseLabelPanel.setLayout(new FlowLayout());
        courseLabelPanel.setSize(960, 40);
        courseLabelPanel.add(courseNameLabel);
        courseLabelPanel.add(inputCourseName);
        courseLabelPanel.setVisible(true);
        return courseLabelPanel;
    }

    public JPanel profNameLabelPanel() {
        JLabel profNameLabel = makeInputLabel("Professor Name");
        JPanel profNameLabelPanel = new JPanel();
        profNameLabelPanel.setLayout(new FlowLayout());
        profNameLabelPanel.setSize(960, 40);
        profNameLabelPanel.add(profNameLabel);
        profNameLabelPanel.add(inputProfessorName);
        profNameLabelPanel.setVisible(true);
        return profNameLabelPanel;

    }

    public JPanel creditLabelPanel() {
        JLabel creditLabel = makeInputLabel("Number of Credits");
        JPanel creditLabelPanel = new JPanel();
        creditLabelPanel.setLayout(new FlowLayout());
        creditLabelPanel.setSize(960, 40);
        creditLabelPanel.add(creditLabel);
        creditLabelPanel.add(inputCredit);

        creditLabelPanel.setVisible(true);

        return creditLabelPanel;


    }

    public JPanel yearLabelPanel() {
        JLabel yearLabel = makeInputLabel("Undergraduate Year (1, 2, 3, or 4)");
        JPanel yearLabelPanel = new JPanel();
        yearLabelPanel.setLayout(new FlowLayout());
        yearLabelPanel.setSize(960, 40);
        yearLabelPanel.add(yearLabel);
        yearLabelPanel.add(inputYear);
        yearLabelPanel.setVisible(true);

        return yearLabelPanel;
    }

    public JPanel markLabelPanel() {
        JLabel markLabel = makeInputLabel("Final Mark");
        JPanel markLabelPanel = new JPanel();
        markLabelPanel.setLayout(new FlowLayout());
        markLabelPanel.setSize(960, 40);
        markLabelPanel.add(markLabel);
        markLabelPanel.add(inputFinalMark);
        markLabelPanel.setVisible(true);

        return markLabelPanel;

    }

    public JPanel termLabelPanel() {
        JLabel termLabel = makeInputLabel("Term (1 or 2)");
        JPanel termLabelPanel = new JPanel();
        termLabelPanel.setLayout(new FlowLayout());
        termLabelPanel.setSize(960, 40);
        termLabelPanel.add(termLabel);
        termLabelPanel.add(inputTerm);

        termLabelPanel.setVisible(true);
        return termLabelPanel;
    }

    public JPanel ratingLabelPanel() {
        JLabel ratingLabel = makeInputLabel("Rating out of 10");
        JPanel ratingLabelPanel = new JPanel();
        ratingLabelPanel.setLayout(new FlowLayout());
        ratingLabelPanel.setSize(960, 40);
        ratingLabelPanel.add(ratingLabel);
        ratingLabelPanel.add(inputRating);

        ratingLabelPanel.setVisible(true);

        return ratingLabelPanel;
    }

    public JPanel courseSummaryLabelPanel() {
        JLabel courseSummaryLabel = makeInputLabel("Description of the Course");
        JPanel courseSummaryLabelPanel = new JPanel();
        courseSummaryLabelPanel.setLayout(new FlowLayout());
        courseSummaryLabelPanel.setSize(960, 40);
        courseSummaryLabelPanel.add(courseSummaryLabel);
        courseSummaryLabelPanel.add(inputCourseSummary);
        courseSummaryLabelPanel.setVisible(true);

        return courseSummaryLabelPanel;
    }





    public void addCourseFrame() {
        setInputs();
        coursePanel = new Panel();
        coursePanel.setSize(960, 1500);
        coursePanel.setBackground(Color.white);
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(courseLabelPanel());
        coursePanel.add(profNameLabelPanel());
        coursePanel.add(creditLabelPanel());
        coursePanel.add(yearLabelPanel());
        coursePanel.add(markLabelPanel());
        coursePanel.add(termLabelPanel());
        coursePanel.add(ratingLabelPanel());
        coursePanel.add(courseSummaryLabelPanel());
        coursePanel.setVisible(true);
        userFrame.add(coursePanel);
        userFrame.setVisible(true);


    }




    public JLabel makeInputLabel(String name) {
        JLabel label = new JLabel();
        label.setText(name + ": ");
        label.setFont(new Font("Serif", Font.ITALIC, 16));
        return label;

    }


    private void addAddCoursePanel() {
        addCoursePanel.setBackground(Color.white);
        addCoursePanel.setSize(1000, 100);
        addCourseButton = new JButton();
        addCourseButton.setPreferredSize(new Dimension(300, 90));
        addCourseButton.setText("Add a Course");
        addCourseButton.addActionListener(this);
        addCoursePanel.add(addCourseButton);
        addCourseButton.setVerticalAlignment(JButton.CENTER);
        addCourseButton.setHorizontalAlignment(JButton.CENTER);
    }

    private void addRemoveCoursePanel() {
        removeCoursePanel.setBackground(Color.white);
        removeCoursePanel.setSize(1000, 100);
        removeCourseButton = new JButton();
        removeCourseButton.setPreferredSize(new Dimension(300, 90));
        removeCourseButton.setText("Remove a Course");
        removeCourseButton.addActionListener(this);
        removeCoursePanel.add(removeCourseButton);
        removeCourseButton.setVerticalAlignment(JButton.CENTER);
        removeCourseButton.setHorizontalAlignment(JButton.CENTER);

    }

    private void addViewCoursePanel() {
        viewCoursePanel.setBackground(Color.white);
        viewCoursePanel.setSize(1000, 100);
        viewCourseButton = new JButton();
        viewCourseButton.setPreferredSize(new Dimension(300, 90));
        viewCourseButton.setText("View Courses");
        viewCourseButton.addActionListener(this);
        viewCoursePanel.add(viewCourseButton);
        viewCourseButton.setVerticalAlignment(JButton.CENTER);
        viewCourseButton.setHorizontalAlignment(JButton.CENTER);
    }

    private void addGradeSummaryPanel() {

        gradeSummaryPanel.setBackground(Color.white);
        gradeSummaryPanel.setSize(1000, 100);

        gradeSummaryButton = new JButton();
        gradeSummaryButton.setPreferredSize(new Dimension(300, 90));
        gradeSummaryButton.setText("View a Grade Summary");
        gradeSummaryButton.addActionListener(this);
        gradeSummaryPanel.add(gradeSummaryButton);
        gradeSummaryButton.setVerticalAlignment(JButton.CENTER);
        gradeSummaryButton.setHorizontalAlignment(JButton.CENTER);

    }


    private void addOtherPanel() {
        otherPanel.setSize(1000, 100);
        otherPanel.setBackground(Color.white);
        saveButton = new JButton();
        loadButton = new JButton();
        quitButton = new JButton();

        saveButton.setText("Save File");
        loadButton.setText("Load File");
        quitButton.setText("Quit");

        saveButton.setPreferredSize(new Dimension(200, 90));
        loadButton.setPreferredSize(new Dimension(200, 90));
        quitButton.setPreferredSize(new Dimension(200, 90));

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        quitButton.addActionListener(this);
        otherPanel.setLayout(new FlowLayout());
        otherPanel.add(saveButton);
        otherPanel.add(loadButton);
        otherPanel.add(quitButton);
    }



    /*// EFFECTS: displays the program options,
    // including adding a course, removing a course, viewing courses,
    // viewing grade summary, and quitting
    public void displayOptions() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Hello user, \nselect a number from below to fulfill your needs!:");
        System.out.println("\t1. Add Course");
        System.out.println("\t2. Remove Course");
        System.out.println("\t3. View Courses");
        System.out.println("\t4. Grade Summary");
        System.out.println("\t5. Save Course Information");
        System.out.println("\t6. Load Course Information");
        System.out.println("\t7. Quit");
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
            saveWork();
        } else if (userInput == 6) {
            loadWork();
        } else if (userInput == 7) {
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

    // The methods saveWork and loadWork was created based on the source below:
    // This class was created based on the source below:
    // Carter, Paul (2021) JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


    // EFFECTS: saves the student to file
    private void saveWork() {
        try {
            jsonSaver.open();
            jsonSaver.write(user);
            jsonSaver.close();
            System.out.println("We have successfully saved your course data to " + FILENAME);
            backToLobby();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + FILENAME);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student from file
    private void loadWork() {
        try {
            user = jsonLoader.read();
            System.out.println("We have successfully loaded your course information from " + FILENAME);
            backToLobby();
        } catch (IOException e) {
            System.out.println("We are unable to read from the following file: " + FILENAME);
        }
    }

*/
}


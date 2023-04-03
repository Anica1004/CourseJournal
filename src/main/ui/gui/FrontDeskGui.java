package ui.gui;

import model.Course;
import model.Student;
import persistence.JsonLoader;
import persistence.JsonSaver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// The FrontDeskGui where users interact with the program, and navigate to find their needs,
// including adding a course, removing a course, viewing courses taken in a particular year,
//  and the overall grade summary, in a graphic user interface
public class FrontDeskGui implements ActionListener, MouseListener {
    private static final String FILENAME = "./data/student.json";
    private Student user;
    private JsonSaver jsonSaver;
    private JsonLoader jsonLoader;

    // below are fields specifically related to gui:
    private JFrame userFrame;

    private Panel addCoursePanel;
    private Panel removeCoursePanel;
    private Panel viewCoursePanel;
    private Panel gradeSummaryPanel;
    private Panel otherPanel;


    private JButton addCourseButton;
    private JButton removeCourseButton;
    private JButton viewCourseButton;
    private JButton gradeSummaryButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    private JButton submitNewCourseButton;
    private JButton submitRemoveCourseButton;
    private JButton homeButton;


    private JButton firstYear;
    private JButton secondYear;
    private JButton thirdYear;
    private JButton fourthYear;

    private JTextField inputCourseName;
    private JTextField inputProfessorName;
    private JTextField inputCredit;
    private JTextField inputYear;
    private JTextField inputFinalMark;
    private JTextField inputTerm;
    private JTextField inputRating;
    private JTextArea inputCourseSummary;

    private Panel coursePanel;
    private JTable courseTable;


    // MODIFIES: this
    // EFFECTS: initializes the user, jsonSaver, jsonLoader, panels, buttons, and labels
    public void initializeGui() {
        user = new Student();
        jsonSaver = new JsonSaver(FILENAME);
        jsonLoader = new JsonLoader(FILENAME);
        addCoursePanel = new Panel();
        removeCoursePanel = new Panel();
        viewCoursePanel = new Panel();
        gradeSummaryPanel = new Panel();
        otherPanel = new Panel();
        addAddCoursePanel();
        addRemoveCoursePanel();
        addViewCoursePanel();
        addGradeSummaryPanel();
        addOtherPanel();
        setHomeButton();
        setYearButtons();
        setSubmitNewCourseButton();
        setSubmitRemoveCourseButton();
        setInputs();
    }


    // EFFECTS: starts the program running by displaying the homepage
    public void startProgram() {
        initializeGui();
        setHomePanels();
    }

    // MODIFIES: this
    // EFFECTS: disposes previous frame, and navigates user to a new frame that corresponds to
    // a grade summary frame
    public void gradeSummaryNavigation() {
        userFrame.dispose();
        userFrame = new GradeSummaryFrame();
        gradeSummaryFrame();

    }

    // MODIFIES: this
    // EFFECTS: disposes previous frame, and navigates user to a new frame that corresponds to
    // a view courses frame
    public void viewCourseNavigation() {
        userFrame.dispose();
        userFrame = new ViewCourseFrame();
        viewCourseFrame();
    }

    // MODIFIES: this
    // EFFECTS: disposes previous frame, and navigates user to a new frame that corresponds to
    // an add course frame
    public void addCourseNavigation() {
        userFrame.dispose();
        userFrame = new AddCourseFrame();
        addCourseFrame();
    }

    // MODIFIES: this
    // EFFECTS: disposes previous frame, and navigates user to a new frame that corresponds to
    // a remove course frame
    public void removeCourseNavigation() {
        userFrame.dispose();
        userFrame = new RemoveCourseFrame();
        removeCourseFrame();
    }


    // MODIFIES: this
    // EFFECTS: creates a course panel for the grade summary frame that displays the grade summary
    // for first, second, third, and fourth year
    public void gradeSummaryFrame() {
        coursePanel = new Panel();
        coursePanel.setSize(960, 1000);
        coursePanel.setBackground(Color.white);
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));

        coursePanel.add(createSummaryPanel(user.getFirstYearCourses(), 1));
        coursePanel.add(createSummaryPanel(user.getSecondYearCourses(), 2));
        coursePanel.add(createSummaryPanel(user.getThirdYearCourses(), 3));
        coursePanel.add(createSummaryPanel(user.getFourthYearCourses(), 4));

        coursePanel.setVisible(true);
        userFrame.add(homeButton);
        userFrame.add(coursePanel);
        userFrame.setVisible(true);
    }


    // EFFECTS: creates a summary panel that either displays that the user does not have enough
    // courses for a summary or a grade summary of the specified year and returns it
    public JPanel createSummaryPanel(List<Course> courses, int year) {
        List<Course> emptyList = new ArrayList<>();
        if (emptyList.equals(courses)) {
            return noCourseAvailPanel(year);
        }
        return courseAvailPanel(courses, year);
    }

    // REQUIRES: the year must be 1, 2, 3, or 4
    // EFFECTS: Creates and returns a panel that notifies that the user does not have enough courses for
    // a grade summary
    public JPanel noCourseAvailPanel(int year) {
        JPanel panel = new JPanel();
        panel.setSize(950, 200);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
        JLabel noCourseAvailable = new JLabel();
        noCourseAvailable.setText("A summary for Year " + year + " is not yet available."
                + "\nNot enough courses were added.");
        Border border = BorderFactory.createDashedBorder(Color.pink);
        noCourseAvailable.setBorder(border);
        noCourseAvailable.setPreferredSize(new Dimension(950, 100));
        noCourseAvailable.setVerticalAlignment(JLabel.CENTER);
        noCourseAvailable.setHorizontalAlignment(JLabel.CENTER);
        panel.add(noCourseAvailable);

        return panel;
    }

    // REQUIRES: the year must be 1, 2, 3, or 4 and the courses should not be empty
    // EFFECTS: Creates and returns a panel that displays a grade summary of the particular, specified year
    // where the grade summary includes an average percentage, letter grade, and total credits
    public JPanel courseAvailPanel(List<Course> courses, int year) {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(960, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String average = user.calculateAverage(courses);
        String letterGrade = user.letterGrade(Double.parseDouble(average));
        int totalCredit = user.totalCredit(courses);


        JLabel averagePercentLabel = new JLabel();
        averagePercentLabel.setText("\t\tYear " + year + " Average Percentage: " + average + " %");
        averagePercentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel letterGradeLabel = new JLabel();
        letterGradeLabel.setText("\t\tYear " + year + " Average Letter Grade: " + letterGrade);
        letterGradeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel totalCreditLabel = new JLabel();
        totalCreditLabel.setText("\t\tYear " + year + " Total Credits: " + totalCredit);
        totalCreditLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(getTitleLabel(year));
        panel.add(averagePercentLabel);
        panel.add(letterGradeLabel);
        panel.add(totalCreditLabel);
        return panel;
    }


    // EFFECTS: creates and returns a label that
    // specifies the year of the grade summary
    public JLabel getTitleLabel(int year) {
        JLabel title = new JLabel();
        title.setText("Grade Summary of Year " + year + ":");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 16));

        return title;
    }


    // MODIFIES: this
    // EFFECTS: If the list of courses is not empty, display all the courses by adding a
    // table with course information in the panel;
    // otherwise notify user that
    // there is not enough courses to provide a view courses panel
    public void displayCourses(List<Course> courses, int year) {
        ArrayList<Course> emptyList = new ArrayList<>();
        if (emptyList.equals(courses)) {
            JOptionPane.showMessageDialog(null, "You have not yet added "
                            + "any courses in Year " + year,
                    "Unavailable to View Courses", JOptionPane.WARNING_MESSAGE);
        } else {
            setTable(courses);
            JScrollPane sp = new JScrollPane(courseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp.setPreferredSize(new Dimension(960, 400));
            userFrame.add(sp);
            userFrame.setVisible(true);

        }
    }


       // MODIFIES: this
       // EFFECTS: creates a course panel for the view Course Frame
    public void viewCourseFrame() {
        coursePanel = new Panel();
        coursePanel.setSize(960, 1000);
        coursePanel.setBackground(Color.white);
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        userFrame.add(homeButton);
        coursePanel.add(buttonPanel());
        coursePanel.setVisible(true);
        userFrame.add(coursePanel);
        userFrame.setVisible(true);

    }

    // EFFECTS: creates and returns a button panel that has
    // the four buttons, "view first year courses" button,
    // "view second year courses" button, "view third year courses" button,
    // and "view fourth year courses" button
    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(960, 200);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(firstYear);
        buttonPanel.add(secondYear);
        buttonPanel.add(thirdYear);
        buttonPanel.add(fourthYear);

        return buttonPanel;


    }

    // MODIFIES: this
    // EFFECTS: creates a course based on the input, and adds it to the users' list of courses

    public void createCourse() {
        String courseName = inputCourseName.getText();
        String professorName = inputProfessorName.getText();
        int credit = Integer.parseInt(inputCredit.getText());
        int year = Integer.parseInt(inputYear.getText());
        Double finalMark = Double.parseDouble(inputFinalMark.getText());
        int term = Integer.parseInt(inputTerm.getText());
        Double courseRating = Double.parseDouble(inputRating.getText());
        String courseSummary = inputCourseSummary.getText();
        Course newCourse = new Course(courseName, professorName, credit, year,
                finalMark, term, courseRating, courseSummary);
        user.sortCourse(newCourse, year);
        JOptionPane.showMessageDialog(null, "We have successfully added " + courseName + "!",
                "Successful Submission", JOptionPane.INFORMATION_MESSAGE);
        setTextFieldEmpty();

    }

    // MODIFIES: this
    // EFFECTS: sets the text fields empty
    public void setTextFieldEmpty() {
        inputCourseName.setText("");
        inputProfessorName.setText("");
        inputCredit.setText("");
        inputYear.setText("");
        inputFinalMark.setText("");
        inputTerm.setText("");
        inputRating.setText("");
        inputCourseSummary.setText("");
    }




    // REQUIRES: year must be 1, 2, 3, or 4
    // MODIFIES: this
    // EFFECTS: finds the course within the list of courses of the particular year,
    // navigating to remove it
    public void findListOfCourse(String courseName, int year) {
        if (year == 1) {
            removeCourse(user.findCourse(courseName, user.getFirstYearCourses()), user.getFirstYearCourses(),
                    courseName);

        } else if (year == 2) {
            removeCourse(user.findCourse(courseName, user.getSecondYearCourses()), user.getSecondYearCourses(),
                    courseName);

        } else if (year == 3) {
            removeCourse(user.findCourse(courseName, user.getThirdYearCourses()), user.getThirdYearCourses(),
                    courseName);
        } else {
            removeCourse(user.findCourse(courseName, user.getFourthYearCourses()), user.getFourthYearCourses(),
                    courseName);

        }
    }


    // REQUIRES: year must be 1, 2, 3, or 4
    // MODIFIES: this
    // EFFECTS:  removes the specified course from the users' list of courses
    // of the particular year
    public void removeCourse() {
        String courseName = inputCourseName.getText();
        int year = Integer.parseInt(inputYear.getText());
        findListOfCourse(courseName, year);
        inputCourseName.setText("");
        inputYear.setText("");
    }

    // MODIFIES: this
    // EFFECTS: removes the specified course if found and confirmed; otherwise display dialog that
    // the course was unable to be found or not removed
    public void removeCourse(Course course, List<Course> courses, String courseName) {
        if (course == null) {
            JOptionPane.showMessageDialog(null, "We were unable to find " + courseName
                            + ". Be sure to check your spelling and correct year.",
                    "Failure in Removing Course", JOptionPane.ERROR_MESSAGE);

        } else {
            ImageIcon image = new ImageIcon("src/main/ui/gui/ConfirmationIcon.png");
            int result = JOptionPane.showConfirmDialog(coursePanel, "Is the following information correct?: "
                            + "\n\t Course Name: " + courseName + "\n\t Professor: " + course.getProfessorName()
                            + "\n\t Year: " + course.getYear() + "\n\t Term: " + course.getTerm() + "\n\t Credit: "
                            + course.getCredit() + "\n\t Course Rating: " + course.getRating()
                            + "\n\t Course Description: " + course.getCourseSummary(),
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, image);

            if (result == JOptionPane.YES_OPTION) {
                courses.remove(course);
                JOptionPane.showMessageDialog(null, "We have successfully removed "
                                + courseName + "!",
                        "Successfully removed", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Sorry for the inconvenience."
                                + " We have not removed " + courseName + ".",
                        "No Change", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }



    // MODIFIES: this
    // EFFECTS: creates a course panel for the remove course frame, adding buttons and
    // panels to it
    public void removeCourseFrame() {
        coursePanel = new Panel();
        coursePanel.setSize(960, 1000);
        coursePanel.setBackground(Color.white);
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(courseLabelPanel(60));
        coursePanel.add(yearLabelPanel(60));
        coursePanel.add(submitRemoveCourseButton);
        coursePanel.setVisible(true);
        userFrame.add(homeButton);
        userFrame.add(coursePanel);
        userFrame.setVisible(true);


    }



    // MODIFIES: this
    // EFFECTS: creates course panel for the add course frame, adding labels, submit button, and home button
    // to it
    public void addCourseFrame() {
        coursePanel = new Panel();
        coursePanel.setSize(960, 1000);
        coursePanel.setBackground(Color.white);
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(courseLabelPanel(40));
        coursePanel.add(profNameLabelPanel());
        coursePanel.add(creditLabelPanel());
        coursePanel.add(yearLabelPanel(40));
        coursePanel.add(markLabelPanel());
        coursePanel.add(termLabelPanel());
        coursePanel.add(ratingLabelPanel());
        coursePanel.add(courseSummaryLabelPanel());
        coursePanel.add(submitNewCourseButton);
        coursePanel.setVisible(true);
        userFrame.add(homeButton);
        userFrame.add(coursePanel);
        userFrame.setVisible(true);
    }

    // EFFECTS: creates a panel that stores the input course name text field
    // and label
    public JPanel courseLabelPanel(int height) {
        JLabel courseNameLabel = makeInputLabel("Course Name");
        JPanel courseLabelPanel = new JPanel();
        courseLabelPanel.setLayout(new FlowLayout());
        courseLabelPanel.setPreferredSize(new Dimension(960, height));
        courseLabelPanel.add(courseNameLabel);
        courseLabelPanel.add(inputCourseName);
        courseLabelPanel.setVisible(true);
        return courseLabelPanel;
    }


    // EFFECTS: creates a panel that stores the input professor name text field
    // and label
    public JPanel profNameLabelPanel() {
        JLabel profNameLabel = makeInputLabel("Professor Name");
        JPanel profNameLabelPanel = new JPanel();
        profNameLabelPanel.setLayout(new FlowLayout());
        profNameLabelPanel.setPreferredSize(new Dimension(960, 40));
        profNameLabelPanel.add(profNameLabel);
        profNameLabelPanel.add(inputProfessorName);
        profNameLabelPanel.setVisible(true);
        return profNameLabelPanel;

    }

    // EFFECTS: creates a panel that stores the input credits text field
    // and label
    public JPanel creditLabelPanel() {
        JLabel creditLabel = makeInputLabel("Number of Credits");
        JPanel creditLabelPanel = new JPanel();
        creditLabelPanel.setLayout(new FlowLayout());
        creditLabelPanel.setPreferredSize(new Dimension(960, 40));
        creditLabelPanel.add(creditLabel);
        creditLabelPanel.add(inputCredit);

        creditLabelPanel.setVisible(true);

        return creditLabelPanel;


    }

    // EFFECTS: creates a panel that stores the input year text field
    // and label
    public JPanel yearLabelPanel(int height) {
        JLabel yearLabel = makeInputLabel("Undergraduate Year (1, 2, 3, or 4)");
        JPanel yearLabelPanel = new JPanel();
        yearLabelPanel.setLayout(new FlowLayout());
        yearLabelPanel.setPreferredSize(new Dimension(960, height));
        yearLabelPanel.add(yearLabel);
        yearLabelPanel.add(inputYear);
        yearLabelPanel.setVisible(true);

        return yearLabelPanel;
    }

    // EFFECTS: creates a panel that stores the input final mark text field
    // and label
    public JPanel markLabelPanel() {
        JLabel markLabel = makeInputLabel("Final Mark");
        JPanel markLabelPanel = new JPanel();
        markLabelPanel.setLayout(new FlowLayout());
        markLabelPanel.setPreferredSize(new Dimension(960, 40));
        markLabelPanel.add(markLabel);
        markLabelPanel.add(inputFinalMark);
        markLabelPanel.setVisible(true);

        return markLabelPanel;

    }

    // EFFECTS: creates a panel that stores the input term text field
    // and label
    public JPanel termLabelPanel() {
        JLabel termLabel = makeInputLabel("Term (1 or 2)");
        JPanel termLabelPanel = new JPanel();
        termLabelPanel.setLayout(new FlowLayout());
        termLabelPanel.setPreferredSize(new Dimension(960, 40));
        termLabelPanel.add(termLabel);
        termLabelPanel.add(inputTerm);

        termLabelPanel.setVisible(true);
        return termLabelPanel;
    }

    // EFFECTS: creates a panel that stores the input rating text field
    // and label
    public JPanel ratingLabelPanel() {
        JLabel ratingLabel = makeInputLabel("Rating out of 10");
        JPanel ratingLabelPanel = new JPanel();
        ratingLabelPanel.setLayout(new FlowLayout());
        ratingLabelPanel.setPreferredSize(new Dimension(960, 40));
        ratingLabelPanel.add(ratingLabel);
        ratingLabelPanel.add(inputRating);

        ratingLabelPanel.setVisible(true);

        return ratingLabelPanel;
    }

    // EFFECTS: creates a panel that stores the input course summary text field
    // and label
    public JPanel courseSummaryLabelPanel() {
        JLabel courseSummaryLabel = makeInputLabel("Course Description");
        JPanel courseSummaryLabelPanel = new JPanel();
        courseSummaryLabelPanel.setLayout(new FlowLayout());
        courseSummaryLabelPanel.setPreferredSize(new Dimension(960, 200));
        courseSummaryLabelPanel.add(courseSummaryLabel);
        courseSummaryLabelPanel.add(inputCourseSummary);
        courseSummaryLabelPanel.setVisible(true);

        return courseSummaryLabelPanel;
    }

    // EFFECTS: creates a label with the specified name, and returns it
    public JLabel makeInputLabel(String name) {
        JLabel label = new JLabel();
        label.setText(name + ": ");
        label.setFont(new Font("Serif", Font.ITALIC, 16));
        return label;

    }


    // MODIFIES: this
    // EFFECTS: initializes the add course button, and stores it within the add course panel
    private void addAddCoursePanel() {
        addCoursePanel.setBackground(Color.white);
        addCoursePanel.setBounds(0, 130, 1000, 100);

        addCourseButton = new JButton();
        addCourseButton.setPreferredSize(new Dimension(300, 90));
        addCourseButton.setBounds(0, 140, 1000, 90);
        addCourseButton.setText("Add a Course");
        addCourseButton.addActionListener(this);
        addCoursePanel.add(addCourseButton);
        addCourseButton.setVerticalAlignment(JButton.CENTER);
        addCourseButton.setHorizontalAlignment(JButton.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes the remove course button, and stores it within the remove course panel
    private void addRemoveCoursePanel() {
        removeCoursePanel.setBounds(0, 240, 1000, 100);
        removeCoursePanel.setBackground(Color.white);

        removeCourseButton = new JButton();
        removeCourseButton.setPreferredSize(new Dimension(300, 90));
        removeCourseButton.setBounds(0, 140, 1000, 90);
        removeCourseButton.setText("Remove a Course");
        removeCourseButton.addActionListener(this);
        removeCoursePanel.add(removeCourseButton);
        removeCourseButton.setVerticalAlignment(JButton.CENTER);
        removeCourseButton.setHorizontalAlignment(JButton.CENTER);

    }

    // MODIFIES: this
    // EFFECTS: initializes the view course button, and stores it within the view course panel
    private void addViewCoursePanel() {
        viewCoursePanel.setBounds(0, 350, 1000, 100);
        viewCoursePanel.setBackground(Color.white);

        viewCourseButton = new JButton();
        viewCourseButton.setPreferredSize(new Dimension(300, 90));
        viewCourseButton.setBounds(0, 140, 1000, 90);
        viewCourseButton.setText("View Courses");
        viewCourseButton.addActionListener(this);
        viewCoursePanel.add(viewCourseButton);
        viewCourseButton.setVerticalAlignment(JButton.CENTER);
        viewCourseButton.setHorizontalAlignment(JButton.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: initializes the grade summary button, and stores it within the grade summary panel
    private void addGradeSummaryPanel() {
        gradeSummaryPanel.setBounds(0, 460, 1000, 100);
        gradeSummaryPanel.setBackground(Color.white);
        gradeSummaryButton = new JButton();
        gradeSummaryButton.setPreferredSize(new Dimension(300, 90));
        gradeSummaryButton.setBounds(0, 140, 1000, 90);
        gradeSummaryButton.setText("View a Grade Summary");
        gradeSummaryButton.addActionListener(this);
        gradeSummaryPanel.add(gradeSummaryButton);
        gradeSummaryButton.setVerticalAlignment(JButton.CENTER);
        gradeSummaryButton.setHorizontalAlignment(JButton.CENTER);

    }

    // MODIFIES: this
    // EFFECTS: initializes the quit, save, and load button, and stores them within the other panel
    private void addOtherPanel() {
        otherPanel.setBounds(0, 570, 1000, 100);
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

        saveButton.setBounds(0, 140, 200, 90);
        saveButton.addActionListener(this);
        loadButton.setBounds(300, 140, 200, 90);
        loadButton.addActionListener(this);
        quitButton.setBounds(600, 140, 200, 90);
        quitButton.addActionListener(this);
        otherPanel.add(saveButton);
        otherPanel.add(loadButton);
        otherPanel.add(quitButton);
    }


    // MODIFIES: this
    // EFFECTS: adds the add course, remove course, view course, grade summary, and other panel
    // to the user frame to set the home userframe
    public void setHomePanels() {
        userFrame = new FrontFrame();
        userFrame.add(addCoursePanel);
        userFrame.add(removeCoursePanel);
        userFrame.add(viewCoursePanel);
        userFrame.add(gradeSummaryPanel);
        userFrame.add(otherPanel);
        userFrame.setVisible(true);

    }

     // MODIFIES: this
     // EFFECTS: initializes the home button
    public void setHomeButton() {
        ImageIcon image = new ImageIcon("src/main/ui/gui/HomeImage.png");
        homeButton = new JButton(image);
        homeButton.setPreferredSize(new Dimension(50, 50));
        homeButton.setHorizontalAlignment(JButton.LEFT);
        homeButton.setVerticalAlignment(JButton.BOTTOM);
        homeButton.addActionListener(this);

    }

    // MODIFIES: this
    // initializes the buttons for "view first year courses" button, "view second year courses" button,
    // "view third year courses" button, and "view fourth year courses" button
    public void setYearButtons() {
        firstYear = new JButton();
        firstYear.setText("View First Year Courses");
        secondYear = new JButton();
        secondYear.setText("View Second Year Courses");
        thirdYear = new JButton();
        thirdYear.setText("View Third Year Courses");
        fourthYear = new JButton();
        fourthYear.setText("View Fourth Year Courses");
        firstYear.setPreferredSize(new Dimension(200, 80));
        secondYear.setPreferredSize(new Dimension(200, 80));
        thirdYear.setPreferredSize(new Dimension(200, 80));
        fourthYear.setPreferredSize(new Dimension(200, 80));
        firstYear.addActionListener(this);
        secondYear.addActionListener(this);
        thirdYear.addActionListener(this);
        fourthYear.addActionListener(this);
    }

    // MODIFIES: this
    // initializes the table that displays the list of courses with information such as the course name,
    // professor name, number of credits, year, final mark, term, rating, and the course summary
    public void setTable(List<Course> courses) {
        String[] columnNames = {"Course Name", "Professor Name", "Credits", "Year",
                "Final Mark", "Term", "Rating", "Course Description"};
        int numOfCourse = courses.size();
        Object[][] data = new Object[numOfCourse][8];

        int count = 0;
        for (Course course : courses) {
            data[count][0] = course.getCourseName();
            data[count][1] = course.getProfessorName();
            data[count][2] = course.getCredit();
            data[count][3] = course.getYear();
            data[count][4] = course.getFinalMark();
            data[count][5] = course.getTerm();
            data[count][6] = course.getRating();
            data[count][7] = course.getCourseSummary();
            count++;
        }
        courseTable = new JTable(data, columnNames);
        courseTable.getColumnModel().getColumn(7).setPreferredWidth(200);
        courseTable.setPreferredSize(new Dimension(960, 400));
        courseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        courseTable.addMouseListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the input fields including the inputCourseName, inputProfessorName, inputCredit,
    // inputYear, inputFinalMark, inputTerm, inputRating, inputCourseSummary
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
        inputCourseSummary.setLineWrap(true);
        inputCourseSummary.setWrapStyleWord(false);
        inputCourseSummary.setPreferredSize(new Dimension(300, 170));
    }

    // MODIFIES: this
    // EFFECTS: initializes the submit button
    public void setSubmitNewCourseButton() {
        submitNewCourseButton = new JButton();
        submitNewCourseButton.setPreferredSize(new Dimension(200, 50));
        submitNewCourseButton.setText("Submit");
        submitNewCourseButton.addActionListener(this);

    }

    // MODIFIES: this
    // EFFECTS: initializes the submit remove course button
    public void setSubmitRemoveCourseButton() {
        submitRemoveCourseButton = new JButton();
        submitRemoveCourseButton.setPreferredSize(new Dimension(200, 50));
        submitRemoveCourseButton.setText("Submit");
        submitRemoveCourseButton.addActionListener(this);

    }


    // EFFECTS: when the mouse is pressed on a specific part of the data,
    // enlarge and display the row of the data in the view course
    @Override
    public void mousePressed(MouseEvent e) {
        int row = courseTable.rowAtPoint(e.getPoint());
        int column = courseTable.columnAtPoint(e.getPoint());
        JOptionPane.showMessageDialog(null, "\n\t Course Name: "
                        + courseTable.getValueAt(row, 0) + "\n\t Professor: "
                        + courseTable.getValueAt(row, 1)
                        + "\n\t Year: " + courseTable.getValueAt(row, 3)
                        + "\t Term: "
                        + courseTable.getValueAt(row, 5)
                        + "\t Credit: "
                        + courseTable.getValueAt(row, 2)
                        + "\n\t Final Mark: "
                        + courseTable.getValueAt(row, 4) + "%"
                        + "\n\t Course Rating: " + courseTable.getValueAt(row, 6)
                        + "/10.0"
                        + "\n\t Course Summary: " + courseTable.getValueAt(row, 7),
                "Larger View", JOptionPane.PLAIN_MESSAGE);
    }


    // EFFECTS: handles cases when the buttons are pressed, navigating accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        Object userClick = e.getSource();
        if (userClick == addCourseButton) {
            addCourseNavigation();
        } else if (userClick == removeCourseButton) {
            removeCourseNavigation();
        } else if (userClick == viewCourseButton) {
            viewCourseNavigation();
        } else if (userClick == gradeSummaryButton) {
            gradeSummaryNavigation();

        }
        actionPerformed2(userClick);
        actionPerformed3(userClick);
    }

    // MODIFIES: this
    // EFFECTS: handles cases when the buttons are pressed, related to view courses
    private void actionPerformed2(Object userClick) {
        if (userClick == firstYear) {
            userFrame.dispose();
            userFrame = new ViewCourseFrame();
            viewCourseFrame();
            displayCourses(user.getFirstYearCourses(), 1);
        } else if (userClick == secondYear) {
            userFrame.dispose();
            userFrame = new ViewCourseFrame();
            viewCourseFrame();
            displayCourses(user.getSecondYearCourses(), 2);
        } else if (userClick == thirdYear) {
            userFrame.dispose();
            userFrame = new ViewCourseFrame();
            viewCourseFrame();
            displayCourses(user.getThirdYearCourses(), 3);
        } else if (userClick == fourthYear) {
            userFrame.dispose();
            userFrame = new ViewCourseFrame();
            viewCourseFrame();
            displayCourses(user.getFourthYearCourses(), 4);
        }

    }

    // MODIFIES: this
    // EFFECTS: handles cases when the buttons are pressed, disposing the userframe
    // when necessary
    public void actionPerformed3(Object userClick) {
        if (userClick == saveButton) {
            saveWork();
            JOptionPane.showMessageDialog(null, "We have successfully saved your file!",
                    "Successfully Saved File", JOptionPane.INFORMATION_MESSAGE);
        } else if (userClick == loadButton) {
            loadWork();
            JOptionPane.showMessageDialog(null, "We have successfully loaded your file!",
                    "Successfully Loaded File", JOptionPane.INFORMATION_MESSAGE);
        } else if (userClick == quitButton) {
            userFrame.dispose();
        } else if (userClick == submitNewCourseButton) {
            createCourse();
        } else if (userClick == homeButton) {
            userFrame.dispose();
            setHomePanels();
        } else if (userClick == submitRemoveCourseButton) {
            removeCourse();
        }

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
        } catch (IOException e) {
            System.out.println("We are unable to read from the following file: " + FILENAME);
        }
    }


    // EFFECTS: not used, but overridden for implementation
    @Override
    public void mouseClicked(MouseEvent e) {
        // not used
    }


    // EFFECTS: not used, but overridden for implementation
    @Override
    public void mouseReleased(MouseEvent e) {
        // not used
    }

    // EFFECTS: not used, but overridden for implementation
    @Override
    public void mouseEntered(MouseEvent e) {
        // not used
    }

    // EFFECTS: not used, but overridden for implementation
    @Override
    public void mouseExited(MouseEvent e) {
        // not used
    }



}


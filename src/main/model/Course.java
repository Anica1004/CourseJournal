package model;


// The class represents a "course" having a course name, the professor that taught
// the course, course credit, the year and term (1 or 2) that the user has taken the course
// the final mark that the user has gotten, and a short description of the course.

public class Course {
    private String courseName;
    private String professorName;
    private int credit;
    private int year;
    private int finalMark;
    private int term;
    private String courseSummary;


    // EFFECTS: constructs a course with a course name, professor name, number of credit,
    //          the year taken, final mark, term, and course description

    public Course(String courseName, String professorName, int credit, int year, int finalMark,
                  int term, String courseSummary) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.credit = credit;
        this.year = year;
        this.finalMark = finalMark;
        this.term = term;
        this.courseSummary = courseSummary;
    }



/*
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFinalMark(int finalMark) {
        this.finalMark = finalMark;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setCourseSummary(String newSummary) {
        this.courseSummary = newSummary;
    }
*/

    public int getFinalMark() {
        return finalMark;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public int getCredit() {
        return credit;
    }

    public int getYear() {
        return year;
    }

    public int getTerm() {
        return term;
    }

    public String getCourseSummary() {
        return courseSummary;
    }



}

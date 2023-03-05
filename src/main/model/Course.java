package model;


// The class represents a "course" having a course name, the professor that taught
// the course, course credit, the undergraduate year (1, 2, 3, or 4) and term (1 or 2)
// that the user has taken the course,
// the final mark that the user has gotten, a short description of the course
// and the course's overall rating.

import org.json.JSONObject;
import persistence.Changer;

public class Course implements Changer {
    private String courseName;
    private String professorName;
    private int credit;
    private int year;
    private double finalMark;
    private int term;
    private String courseSummary;
    private double rating;



    // EFFECTS: constructs a course with a course name, professor name, number of credit,
    //          the year taken, final mark, term, and course description

    public Course(String courseName, String professorName, int credit, int year, double finalMark,
                  int term, double rating, String courseSummary) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.credit = credit;
        this.year = year;
        this.finalMark = finalMark;
        this.term = term;
        this.courseSummary = courseSummary;
        this.rating = rating;

    }

    // EFFECTS: returns course and its information as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", courseName);
        json.put("professorName", professorName);
        json.put("credit", credit);
        json.put("year", year);
        json.put("finalMark", finalMark);
        json.put("term", term);
        json.put("courseSummary", courseSummary);
        json.put("rating", rating);
        return json;
    }


    public double getFinalMark() {
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


    public double getRating() {
        return rating;
    }

}

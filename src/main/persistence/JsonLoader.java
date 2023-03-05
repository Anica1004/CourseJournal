package persistence;

import model.Course;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class represents a reader that loads the JSON data of students from the specified
// file

// This class was created based on the source below:
// Carter, Paul (2021) JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonLoader {

    private String fileName;

    // EFFECTS: constructs the JsonLoader by assigning the filename to load
    public JsonLoader(String fileName) {
        this.fileName = fileName;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Student read() throws IOException {
        String jsonData = readFile(fileName);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject) {
        Student st = new Student();
        addFirstYearCourses(st, jsonObject);
        addSecondYearCourses(st, jsonObject);
        addThirdYearCourses(st, jsonObject);
        addFourthYearCourses(st, jsonObject);

        return st;
    }


    // MODIFIES: st
    // EFFECTS: parses first year courses from JSON object and adds them to student
    private void addFirstYearCourses(Student st, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("firstYearCourses");
        for (Object json : jsonArray) {
            JSONObject course = (JSONObject) json;
            addCourse(st, course, 1);
        }
    }

    // MODIFIES: st
    // EFFECTS: parses first year courses from JSON object and adds them to student
    private void addSecondYearCourses(Student st, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("secondYearCourses");
        for (Object json : jsonArray) {
            JSONObject course = (JSONObject) json;
            addCourse(st, course, 2);
        }
    }

    // MODIFIES: st
    // EFFECTS: parses first year courses from JSON object and adds them to student
    private void addThirdYearCourses(Student st, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thirdYearCourses");
        for (Object json : jsonArray) {
            JSONObject course = (JSONObject) json;
            addCourse(st, course, 3);
        }
    }

    // MODIFIES: st
    // EFFECTS: parses first year courses from JSON object and adds them to student
    private void addFourthYearCourses(Student st, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fourthYearCourses");
        for (Object json : jsonArray) {
            JSONObject course = (JSONObject) json;
            addCourse(st, course, 4);
        }
    }

    // MODIFIES: st
    // EFFECTS: parses a first year course from JSON object and adds it to student
    private void addCourse(Student st, JSONObject jsonObject, int num) {
        String courseName = jsonObject.getString("courseName");
        String professorName = jsonObject.getString("professorName");
        int credit = jsonObject.getInt("credit");
        int year = jsonObject.getInt("year");
        double finalMark = jsonObject.getDouble("finalMark");
        int term = jsonObject.getInt("term");
        String courseSummary = jsonObject.getString("courseSummary");
        double rating = jsonObject.getDouble("rating");
        Course course = new Course(courseName, professorName, credit, year, finalMark, term, rating, courseSummary);
        if (num == 1) {
            st.addFirstYearCourses(course);
        } else if (num == 2) {
            st.addSecondYearCourses(course);
        } else if (num == 3) {
            st.addThirdYearCourses(course);
        } else if (num == 4) {
            st.addFourthYearCourses(course);
        }
    }


}

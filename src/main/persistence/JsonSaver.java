package persistence;



import model.Student;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// This class is saves the data of Student, in which its information is
// represented as Json Objects


// This class was created based on the source below:
// Carter, Paul (2021) JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonSaver {
    private static final int indent = 4;
    private String fileName;
    private PrintWriter writer;


    // EFFECTS: constructs the Json writer by setting the file name
    public JsonSaver(String fileName) {
        this.fileName = fileName;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if the specified file
    // is unable to be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileName));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Student s) {
        JSONObject json = s.toJson();
        saveToFile(json.toString(indent));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }



}

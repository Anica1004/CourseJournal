package persistence;

import org.json.JSONObject;

// This interface specifies the method that is responsible to represent this as a JSON object
// and return it
public interface Changer {
    // This interface was created based on the source below:
    // Carter, Paul (2021) JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}

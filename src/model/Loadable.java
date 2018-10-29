package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//MODIFIES: this
// EFFECTS: returns list of tasks from the output file
public interface Loadable {
    ArrayList<Task> load(String output, List<EstCompletionTime> times) throws IOException, ClassNotFoundException;
}

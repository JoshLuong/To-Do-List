package model;

import java.io.IOException;
import java.util.ArrayList;

//MODIFIES: this
// EFFECTS: returns list of tasks from the output file
public interface Loadable {
    ArrayList<Task> load(String output) throws IOException, ClassNotFoundException;
}

package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//MODIFIES: this
// EFFECTS: returns list of tasks from the output file
public interface Loadable {
    Map<String, Task> load(String output, List<EstCompletionTime> timeList) throws IOException, ClassNotFoundException;
}

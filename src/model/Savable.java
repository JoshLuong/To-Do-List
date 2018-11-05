package model;

import exceptions.NullOutputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


//REQUIRES: output is not null
// MODIFIES: this
// EFFECTS: saves tasks in todoList into the output file
public interface Savable{
    void save(Map<String, Task> mapToDoList, String output) throws IOException, NullOutputException;
}

package model;

import exceptions.NullOutputException;

import java.io.IOException;
import java.util.ArrayList;


//REQUIRES: output is not null
// MODIFIES: this
// EFFECTS: saves tasks in todoList into the output file
public interface Savable{
    void save(ArrayList<Task> toDoList, String output) throws IOException, NullOutputException;
}

package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoList implements Loadable, Savable {
    Task task;

    @Override
    //REQUIRES: output is not null
    // MODIFIES: this
    // EFFECTS: saves tasks in todoList into the output file
    public void save(ArrayList<Task> toDoList, String output) throws IOException {

        PrintWriter writer = new PrintWriter(output, "UTF-8");
        for (Task t : toDoList) {
            List<String> lines = Files.readAllLines(Paths.get(output));
            lines.add(t.getName() + " " + t.getImportanceLvl());

            for (String line : lines) {
               // ArrayList<String> partsOfLine = splitOnSpace(line);
                writer.println(line);
            }
        }
        writer.close();
    }

    @Override
    //MODIFIES: this
    // EFFECTS: returns list of tasks from the output file
    public ArrayList<Task> load(String output) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(output));
        ArrayList<Task> loadedList = new ArrayList<>();
        for (String s: lines){
            ArrayList<String> partsOfLine = splitOnSpace(s);
            Task t = new Task(partsOfLine.get(0), partsOfLine.get(1));
            loadedList.add(t);

        }
        return loadedList;

    }

    // MODIFIES: this
    // EFFECTS: makes a new Task with an importance level and name
    public Task newToDo(Scanner scanner){
        System.out.println("Please enter the task to do");
        String newTask = scanner.nextLine();
        String actualLevel = "";
        if (!newTask.equals("")) {
            System.out.println("Please enter the level of importance: [1] urgent, [2] medium, or [3] low");
            int level = scanner.nextInt();

            if (level == 1) {
                actualLevel = "urgent";
            }
            {
                ;
            }
            if (level == 2) {
                actualLevel = "medium";
            }
            {
                ;
            }
            if (level == 3) {
                actualLevel = "low";
            }
            {
                ;
            }
            scanner.nextLine();
        }
        {
            ;
        }
        task = new Task(newTask, actualLevel);
        return task;
        // tasks.addTask(newTask,actualLevel);
    }



    // MODIFIES: this
    // EFFECTS: takes out completed task from toDoList, unless not there
    public void crossOff(Scanner scanner, ArrayList<Task> toDoList){
        System.out.println("Please enter the task completed");
        String search = scanner.nextLine();
        ArrayList<Task> cloned = (ArrayList) toDoList.clone();
        for (Task t : cloned){
            if (t.getName().equals(search)){
                toDoList.remove(t);
            }

        }

    }
    // MODIFIES: this
    // EFFECTS: prints out toDoList
    public void printStatement(ArrayList<Task> toDoList){
        if (toDoList.size() == 0){
            System.out.println("Good job, you've finished all the tasks!");
        }
        else System.out.println("Tasks to complete based on level of importance:");
        ArrayList<Task> sortedList = sortedList(toDoList);
        for (Task t : sortedList){
            System.out.println(t.getName()+" : "+t.getImportanceLvl());
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the list based on level of importance
    public ArrayList<Task> sortedList(ArrayList<Task> oldList){
        ArrayList<Task> newList = new ArrayList<>();
        for (Task t : oldList){
            if (t.getImportanceLvl().equals("urgent")){
                newList.add(t);
            }
        }
        for (Task t : oldList){
            if (t.getImportanceLvl().equals("medium")){
                newList.add(t);
            }
        }
        for (Task t : oldList){
            if (t.getImportanceLvl().equals("low")){
                newList.add(t);
            }
        }
        return newList;

    }



    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}

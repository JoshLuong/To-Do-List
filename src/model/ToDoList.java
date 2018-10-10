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

    @Override
    //REQUIRES: output is not null
    // MODIFIES: this
    // EFFECTS: saves tasks in todoList into the output file
    public void save(ArrayList<Task> toDoList, String output) throws IOException {

        PrintWriter writer = new PrintWriter(output, "UTF-8");
        for (Task t : toDoList) {
            List<String> lines = Files.readAllLines(Paths.get(output));
            lines.add(t.getName() + " " + t.getImportanceLvl()+" " +t.getType());

            for (String line : lines) {
                writer.println(line);
            }
        }
        writer.close();
    }

    @Override
    //MODIFIES: this
    // EFFECTS: returns list of tasks from the output file
    public ArrayList<Task> load(String output) throws IOException {
        Task t = new RegularTask("","","");
        List<String> lines = Files.readAllLines(Paths.get(output));
        ArrayList<Task> loadedList = new ArrayList<>();
        for (String s: lines){
            ArrayList<String> partsOfLine = splitOnSpace(s);
            if (partsOfLine.get(2).equals("Regular")){
                t = new RegularTask(partsOfLine.get(0), partsOfLine.get(1), partsOfLine.get(2));
            }
            {}
            if (partsOfLine.get(2).equals("School")) {
                t = new SchoolTask(partsOfLine.get(0), partsOfLine.get(1), partsOfLine.get(2));
            }
            loadedList.add(t);

        }
        return loadedList;

    }
    // REQUIRES: Task is not already in the to-doList
    // MODIFIES: this
    // EFFECTS: makes a new Task with an importance level and name
    public Task newToDo(Scanner scanner){
        Task task = new RegularTask("","","");
        System.out.println("Please enter the task to do");
        String newTask = scanner.nextLine();
        String type= "";
        if (!newTask.equals("")) {
            System.out.println("Please enter which type of Task you want to enter: [1] Regular Task, [2] School Task");
            int level = scanner.nextInt();

            if (level == 1) {
                type = "Regular";
            }
            if (level == 2) {
                type = "School";
            }
            {}

            scanner.nextLine();
        }
        {}
        if (type.equals("Regular")){
            task = new RegularTask(newTask, getNewLevel(newTask, scanner), type);
        }
        if (type.equals("School")){
        task = new SchoolTask(newTask, getNewLevel(newTask, scanner), type);}
        {}
        return task;
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

    public void printList(Scanner scanner, ArrayList<Task> sortedList){
        boolean done = false;
        Task rt = new RegularTask("","","");
        Task st = new SchoolTask("","","");
        System.out.println("Which list of tasks do you want to see: [1] All Tasks, [2] Regular Tasks, [3] School Tasks");
         int typeList = scanner.nextInt();
         scanner.nextLine();
        if (typeList == 1){
            System.out.println("All tasks left to complete:");
            for (Task t : sortedList) {
                System.out.println(t.getName()+" : "+t.getImportanceLvl());
            }
        }
         if (typeList == 2){
             System.out.println("Regular tasks left to complete:");
             for (Task t : sortedList){
                 if (t.getType().equals("Regular")){
                      System.out.println(t.getName()+" : "+t.getImportanceLvl());
                      done = true;
                     }
                 }
                 if (!done){
                     System.out.println(rt.done());
             }
             }
        if (typeList == 3){
            System.out.println("School tasks left to complete:");
            for (Task t : sortedList){
                if (t.getType().equals("School")){
                    System.out.println(t.getName()+" : "+t.getImportanceLvl());
                    done = true;
                }
            }
            if (!done){
                System.out.println(st.done());
            }

        }
        {}

    }



    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private String getNewLevel(String newTask, Scanner scanner){
        String actualLevel= "";
        if (!newTask.equals("")) {
            System.out.println("Please enter the level of importance: [1] urgent, [2] medium, or [3] low");
            int level = scanner.nextInt();

            if (level == 1) {
                actualLevel = "urgent";
            }
            if (level == 2) {
                actualLevel = "medium";
            }
            if (level == 3) {
                actualLevel = "low";
            }
            {}

            scanner.nextLine();
        }
        {}
        return actualLevel;

    }




}

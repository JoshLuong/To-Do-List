package ui;

import exceptions.AlreadyInList;
import exceptions.EmptyTaskException;
import exceptions.NoTaskFoundException;
import exceptions.NullOutputException;
import model.*;
import observer.Subject;

import java.io.*;
import java.util.*;


public class ToDoListManager extends Subject {
    private SaveAndLoad saveAndLoad = new SaveAndLoad();
    protected List<EstCompletionTime> times = new ArrayList<>();
    private ImportanceLevelMapManager importanceLevelMapManager;
    {
        try {
            importanceLevelMapManager = new ImportanceLevelMapManager();
        } catch (IOException e) {
        }
    }


    protected void save(Map<String, Task> mapToDoList, String output) throws IOException, NullOutputException {
    saveAndLoad.save(mapToDoList, output);
    }

    protected void saveMap() throws IOException {
        importanceLevelMapManager.saveMap();
    }

    //MODIFIES: this
    // EFFECTS: returns list of tasks from the output file
    protected Map<String, Task> load(String output, List<EstCompletionTime> timeList) throws IOException {
        return saveAndLoad.load(output,timeList);
    }




    // REQUIRES: Task is not already in the to-doList
    // MODIFIES: this
    // EFFECTS: makes a new Task with an importance level and name
    public  Task addTask(Scanner scanner, Map<String, Task> toDoList,String newTask, String type, String t, String lvl) throws AlreadyInList, EmptyTaskException {
        TaskFactory tf = new TaskFactory();
        Task task;
        EstCompletionTime time = new EstCompletionTime(t);
        if(newTask.equals("")| newTask.equals(" ")){
            throw new EmptyTaskException();
        }
        //task = setTask(times, newTask, type, t, lvl);
        task = tf.makeTask(newTask, type, lvl);
        String day = time.getDay();
        addTimeAndTask(times, task, day);

        if (isSameInList(task, toDoList)){
            throw new AlreadyInList();
        }
        toDoList.put(task.getName(), task);
        addObserver(task);
        return task;
    }


    protected void crossOff(Scanner scanner, Map<String, Task> toDoList, String search) throws NoTaskFoundException {
        if (toDoList.containsKey(search)){
            notifyObservers(toDoList.get(search));
            toDoList.get(search).getTime().getTasks().remove(toDoList.get(search));
            removeTaskFromLevelMap(toDoList, search);
            toDoList.remove(search);
        }
        else throw new NoTaskFoundException();
    }

    public void addToMap(Task t){
        importanceLevelMapManager.addToMap(t);
    }



    // TODO COUPLING
    // MODIFIES: this
    // EFFECTS: sorts the list based on level of importance
    public ArrayList<Task> sortedList(Map<String, Task> oldList){
        Collection<Task> List = oldList.values();
        ArrayList<Task> newList = new ArrayList<>();
        sortTasks(List, newList, "urgent");
        sortTasks(List, newList, "medium");
        sortTasks(List, newList, "low");
        return newList;

    }



    public Task setTask(List<EstCompletionTime> timeList, String newTask, String type, String t, String lvl) {
        Task task = null;
        EstCompletionTime time = new EstCompletionTime(t);
        TaskFactory tf = new TaskFactory();
        task = tf.makeTask(newTask, type, lvl);

        String day = time.getDay();
        addTimeAndTask(timeList, task, day);
        return task;
    }


    private void removeTaskFromLevelMap(Map<String, Task> toDoList, String search) {
        importanceLevelMapManager.removeTaskFromLevelMap(toDoList,search);
    }


    private  boolean  isSameInList(Task task, Map<String, Task> toDoList){
        return toDoList.containsKey(task.getName());

    }


    private static void addTimeAndTask(List<EstCompletionTime> timeList, Task task, String day) {
        boolean b = true;

        while (true && b) {
            for (EstCompletionTime t : timeList) {
                if (t.getDay().equals(day)) {
                    t.addTask(task);
                    task.addTime(t);
                    b = false;
                }
            }
            break;
        }
        while (true && b) {
            EstCompletionTime time = new EstCompletionTime(day);
            time.addTask(task);
            task.addTime(time);
            timeList.add(time);
            b = false;
        }
    }

    public void sortTasks(Collection<Task> list, ArrayList<Task> newList, String urgent) {
        for (Task t : list) {
            if (t.getImportanceLvl().equals(urgent)) {
                newList.add(t);
            }
        }
    }

}

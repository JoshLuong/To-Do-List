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
    protected Weather weather = new Weather();
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


//    public void saveTimes(List<EstCompletionTime> times, String output) throws IOException, NullOutputException {
//
//        PrintWriter writer = new PrintWriter("times.txt", "UTF-8");
//        for (EstCompletionTime t : times) {
//            List<String> lines = Files.readAllLines(Paths.get("times.txt"));
//            lines.add(t.getDay() + " " + getTasksFromList(t.getTasks()));
//
//            for (String line : lines) {
//                writer.println(line);
//            }
//        }
//        writer.close();
//    }

//    private StringBuilder getTasksFromList(ArrayList<Task> tasks){
//        StringBuilder sb = new StringBuilder();
//        for (Task t: tasks){
//            sb.append(t.getName() + " " + t.getImportanceLvl()+" " +t.getType()+ " "+t.getTime().getDay());
//
//        }
//        return sb;
//    }

    // REQUIRES: Task is not already in the to-doList
    // MODIFIES: this
    // EFFECTS: makes a new Task with an importance level and name
    public  Task addTask(Scanner scanner, Map<String, Task> toDoList, String newTask, String type, String t, String lvl) throws AlreadyInList, EmptyTaskException {
        Task task;
        //System.out.println("Please enter the task to do");
        //String newTask = scanner.nextLine();
//        if(newTask.equals("")| newTask.equals(" ")){
//            throw new EmptyTaskException();
//        }
        //String type = selectType(scanner);
        //String t = dayScanner(scanner);
        //scanner.nextLine();
        //String lvl = getNewLevel(newTask, scanner);
// TODO COUPLING
        task = setTask(times, newTask, type, t, lvl);

        if (isSameInList(task, toDoList)){
            throw new AlreadyInList();
        }
        toDoList.put(task.getName(), task);
        addObserver(task);
        return task;
    }


    protected Collection<Task> getTasksFromTime(Scanner scanner, Map<String, Task> tasks){
        HashSet<Task> tasks1= new HashSet<>();
        Collection<Task> List = tasks.values();
        String time = dayScanner(scanner);
        scanner.nextLine();
        for (Task t : List){
            if (t.getTime().getDay().equals(time)){
                tasks1.addAll(t.getTime().getTasks());
            }
        }

        return tasks1;

    }

    // MODIFIES: this
    // EFFECTS: takes out completed task from toDoList, unless not there
    protected void crossOff(Scanner scanner, Map<String, Task> toDoList, String search) throws NoTaskFoundException {
        if (toDoList.containsKey(search)){
            notifyObservers(toDoList.get(search));
            toDoList.get(search).getTime().getTasks().remove(toDoList.get(search));
            removeTaskFromLevelMap(toDoList, search);
            toDoList.remove(search);
        }
        else throw new NoTaskFoundException();
    }

    public void printLevelMap(String s) {
        importanceLevelMapManager.printLevelMap(s);

    }
    public void addToMap(Task t){
        importanceLevelMapManager.addToMap(t);
    }

    // MODIFIES: this
    // EFFECTS: prints out toDoList
    public void printStatement(Map<String, Task> toDoList) throws IOException {
        if (toDoList.size() == 0){
            System.out.println("Good job, you've finished all the tasks!");
        }
        else System.out.println("Tasks to complete based on level of importance:");
        ArrayList<Task> sortedList = sortedList(toDoList);
        for (Task t : sortedList){
            printTask(t);
        }
        System.out.println("The weather is currently "+weather.getWeather()+". . . perfect weather to be productive!");
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


    // TODO COUPLING
    public void printList(Scanner scanner, ArrayList<Task> sortedList, Map<String, Task> toDoList) throws IOException {
        boolean done = false;
        Task rt = new RegularTask("","");
        Task st = new SchoolTask("","");
        System.out.println("Which list of tasks do you want to see: [1] All Tasks, [2] Regular Tasks, [3] School Tasks");
         int typeList = scanner.nextInt();
         scanner.nextLine();
        if (typeList == 1){
            System.out.println("All tasks left to complete:");
            printStatement(toDoList);
        }

         if (typeList == 2){
             System.out.println("Regular tasks left to complete:");
             for (Task t : sortedList){
                 if (t.getType().equals("Regular")){
                     printTask(t);
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
                    printTask(t);
                    done = true;
                }
            }
            if (!done){
                System.out.println(st.done());
            }
        }
        {}

    }
    public String printUrgency(Scanner scanner){
        System.out.println("What is the urgency you would like to look up? [1] urgent, [2] medium, [3] low");
        String s = "";
        int n = scanner.nextInt();
        if (n == 1){
            s = "urgent";
        }
        if (n == 2){
            s = "medium";
        }
        if (n == 3){
            s = "low";
        }
        scanner.nextLine();
        return s;

    }

    public Task setTask(List<EstCompletionTime> timeList, String newTask, String type, String t, String lvl) {
        Task task = null;
        EstCompletionTime time = null;
        if (type.equals("Regular")){
            time = new EstCompletionTime(t);
            task = new RegularTask(newTask, lvl);
        }
        if (type.equals("School")){
            time = new EstCompletionTime(t);
            task = new SchoolTask(newTask, lvl);}

        String day = time.getDay();
        addTimeAndTask(timeList, task, day);
        return task;
    }


    private void removeTaskFromLevelMap(Map<String, Task> toDoList, String search) {
        importanceLevelMapManager.removeTaskFromLevelMap(toDoList,search);
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

    private  boolean  isSameInList(Task task, Map<String, Task> toDoList){
        return toDoList.containsKey(task.getName());

    }

    private String selectType(Scanner scanner) {
        String type= "";
        System.out.println("Please enter the Task's type: [1] Regular Task, [2] School Task");
        int level = scanner.nextInt();
        if (level == 1) {
            type = "Regular";
        }
        if (level == 2) {
            type = "School";
        }
        scanner.nextLine();
        return type;
    }


    private String dayScanner(Scanner scanner){
        System.out.println("What is the task's estimated completion dayScanner?");
        System.out.println("[1]: SUN, [2]: MON, [3]: TUES, [4]: WED, [5]: THURS, [6]: FRI, [7]: SAT");
        String day = "";
        int scan = scanner.nextInt();
        if (scan == 1){
            day = "SUNDAY";
        }
        if (scan == 2){
            day = "MONDAY";
        }
        if (scan == 3){
            day = "TUESDAY";
        }
        if (scan == 4){
            day = "WEDNESDAY";
        }
        if (scan == 5){
            day = "THURSDAY";
        }
        if (scan == 6){
            day = "FRIDAY";
        }
        if (scan == 7){
            day = "SATURDAY";
        }

        return day;
    }

    private static void addTimeAndTask(List<EstCompletionTime> timeList, Task task, String day) {
        boolean b = true;
//        if (b) {
//            for (EstCompletionTime t : timeList) {
//                if (t.getDay().equals(day)) {
//                    t.addTask(task);
//                    task.addTime(t);
//                    b = false;
//                }
//            }        }
//         if (b) {
//            EstCompletionTime time = new EstCompletionTime(day);
//            time.addTask(task);
//            task.addTime(time);
//            timeList.add(time);
//
//        }
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

    private void sortTasks(Collection<Task> list, ArrayList<Task> newList, String urgent) {
        for (Task t : list) {
            if (t.getImportanceLvl().equals(urgent)) {
                newList.add(t);
            }
        }
    }
    private void printTask(Task t) {
        System.out.println(t.getImportanceLvl()+". . ." +t.getName());
    }

}

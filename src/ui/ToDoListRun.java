// referenced from LittleCalculatorLecLab

package ui;

import exceptions.AlreadyInList;
import exceptions.EmptyTaskException;
import exceptions.NoTaskFoundException;
import exceptions.NullOutputException;
import model.*;

import java.io.IOException;
import java.util.*;

public class ToDoListRun extends ToDoListManager{
    private final String FILE = "outputfile.txt";
//    private ToDoListManager newToDoListManager = new ToDoListManager();
    private Scanner scanner = new Scanner(System.in);
//    private List<EstCompletionTime> times = new ArrayList<>();
    private Map<String, Task> toDoList = load("outputfile.txt", times);
   // private Map<String, List<String>> searchImptLvl = loadMap();


    private ToDoListRun() throws IOException{
        String operation;

        while (true){

//            ToDoListManager newToDoListManager = new ToDoListManager();
            System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                         ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do, [2] cross-off, [3] print sorted to-do list, [4] check times, [5] find a task based on urgency");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            operation = scanner.nextLine();



            if (operation.equals("1")) {
                try {
                     Task t = addTask(scanner, toDoList);
                     addToMap(t);
                    System.out.println("you added "+t.getName()+" to the to-do list!");

                } catch (AlreadyInList alreadyInList) {
                    System.out.println("This task is already in the list!");

                } catch (EmptyTaskException emptyTaskException){
                    System.out.println("You cannot add an empty task!");
                }

            }

            if (operation.equals("2")) {
                try {
                    crossOff(scanner, toDoList);
                } catch (NoTaskFoundException e) {
                    System.out.println("There was no task found in the list!");
                }

            }
            if (operation.equals("3")) {
                ArrayList<Task> sortedList = sortedList(toDoList);
                printList(scanner, sortedList, toDoList);
            }

            if (operation.equals("4")){
                Collection<Task> tasks= getTasksFromTime(scanner, toDoList);
                for (Task t : tasks){
                    System.out.println(t.getName());
                }
            }
            if (operation.equals("5")){
                String s = printUrgency(scanner);
                try {
                    printLevelMap(s);
                }
                catch (NullPointerException n){
                    System.out.println("There are no existing tasks with the chosen urgency!");}
                }


            else if (operation.equals("quit")) {
                break;
            }

        }
        try {
            save(toDoList, FILE);
            saveMap();
        }catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }
        finally {
            System.out.println("\n      -----Have a productive day!-----");
            printStatement(toDoList);
        }

    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}

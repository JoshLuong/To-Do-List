// referenced from LittleCalculatorLecLab

package ui;

import exceptions.AlreadyInList;
import exceptions.EmptyTaskException;
import exceptions.NoTaskFoundException;
import exceptions.NullOutputException;
import model.EstCompletionTime;
import model.Task;

import java.io.IOException;
import java.util.*;

public class ToDoListRun{
    private final String FILE = "outputfile.txt";

    private ToDoListManager newToDoListManager = new ToDoListManager();
    private Scanner scanner = new Scanner(System.in);
    private List<EstCompletionTime> times = new ArrayList<>();
    private Map<String, Task> toDoList = newToDoListManager.load1("outputfile.txt", times);
    private Map<String, List<String>> searchImptLvl = newToDoListManager.loadMap();



    private ToDoListRun() throws IOException{
        String operation;

        while (true){

            ToDoListManager newToDoListManager = new ToDoListManager();

            System.out.println("\n\n                                     ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do, [2] cross-off, [3] print sorted to-do list, [4] check times, [5] find a task based on urgency");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            operation = scanner.nextLine();



            if (operation.equals("1")) {
                try {
                     Task t = newToDoListManager.addTask(scanner, toDoList, times);
                     newToDoListManager.addToMap(searchImptLvl, t);
                    System.out.println("you added "+t.getName()+" to the to-do list!");

                } catch (AlreadyInList alreadyInList) {
                    System.out.println("This task is already in the list!");

                } catch (EmptyTaskException emptyTaskException){
                    System.out.println("You cannot add an empty task!");
                }

            }

            if (operation.equals("2")) {
                try {
                    newToDoListManager.crossOff(scanner, toDoList, searchImptLvl);
                    System.out.println("The task was crossed off the to-do list!");
                } catch (NoTaskFoundException e) {
                    System.out.println("There was no task found in the list!");
                }

            }
            if (operation.equals("3")) {
                ArrayList<Task> sortedList = newToDoListManager.sortedList(toDoList);
                newToDoListManager.printList(scanner, sortedList);
            }

            if (operation.equals("4")){
                Collection<Task> tasks= newToDoListManager.getTasksFromTime(scanner, toDoList);
                for (Task t : tasks){
                    System.out.println(t.getName());
                }
            }
            if (operation.equals("5")){
                String s = newToDoListManager.printUrgency(scanner);
                try {
                    for (int i = 0; i < searchImptLvl.get(s).size(); i++) {
                        System.out.println(searchImptLvl.get(s).get(i));
                    }
                }
                catch (NullPointerException n){
                    System.out.println("There are no existing tasks with the chosen urgency!");}
                }


            else if (operation.equals("quit")) {
                break;
            }


        }
        try {
            newToDoListManager.save1(toDoList, FILE);
            newToDoListManager.saveMap(searchImptLvl, FILE);
        }catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }
        finally {
            System.out.println("\n      -----Have a productive day!-----");
            newToDoListManager.printStatement(toDoList);
        }

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
    new ToDoListRun();

}

}

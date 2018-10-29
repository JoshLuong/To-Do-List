// referenced from LittleCalculatorLecLab

package ui;

import exceptions.AlreadyInList;
import exceptions.EmptyTaskException;
import exceptions.NoTaskFoundException;
import exceptions.NullOutputException;
import model.EstCompletionTime;
import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class ToDoListRun{
    private final String FILE = "outputfile.txt";

    private model.ToDoList newToDoList = new model.ToDoList();
    private Scanner scanner = new Scanner(System.in);
    private List<EstCompletionTime> times = new ArrayList<>();
    private ArrayList<Task> toDoList = newToDoList.load("outputfile.txt", times);
    public Map<String, List<String>> searchImptLvl = newToDoList.loadMap();



    private ToDoListRun() throws IOException, ClassNotFoundException {
        String operation;

        while (true){

            ToDoList newToDoList = new ToDoList();

            System.out.println("\n                                    ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do, [2] cross-off, [3] print sorted to-do list, [4] check times, [5] find a task based on urgency\n");
            operation = scanner.nextLine();



            if (operation.equals("1")) {
                try {
                     newToDoList.addTask(scanner, toDoList, times);
                     Task t = toDoList.get(toDoList.size()-1);
                     if (!searchImptLvl.containsKey(t.getImportanceLvl())){
                         List<String> tasks = new ArrayList<>();
                         tasks.add(t.getName());
                         searchImptLvl.put(t.getImportanceLvl(), tasks);
                     }
                     else searchImptLvl.get(t.getImportanceLvl()).add(t.getName());
                    System.out.println("you added "+t.getName()+" to the to-do list!");

                } catch (AlreadyInList alreadyInList) {
                    System.out.println("This task is already in the list!");

                } catch (EmptyTaskException emptyTaskException){
                    System.out.println("You cannot add an empty task!");

                }
                finally {
                    System.out.println("\nDon't procrastinate!");
                }

            }

            if (operation.equals("2")) {
                try {
                    newToDoList.crossOff(scanner, toDoList, searchImptLvl);
                    System.out.println("The task was crossed off the to-do list!");
                } catch (NoTaskFoundException e) {
                    System.out.println("There was no task found in the list!");
                }

            }
            if (operation.equals("3")) {

                ArrayList<Task> sortedList = newToDoList.sortedList(toDoList);
                newToDoList.printList(scanner, sortedList);
            }

            if (operation.equals("4")){
                Collection<Task> tasks= newToDoList.getTasksFromTime(scanner, toDoList);
                for (Task t : tasks){
                    System.out.println(t.getName());
                }
            }

            if (operation.equals("5")){
                String s = newToDoList.printUrgency(scanner);
                if (searchImptLvl.get(s).size() > 0){
                    for (int i = 0; i < searchImptLvl.get(s).size(); i++) {
                        System.out.println(searchImptLvl.get(s).get(i));
                    }
                }

            }
            else if (operation.equals("quit")) {
                break;
            }


        }
        try {
            newToDoList.save(toDoList, FILE);
            newToDoList.saveMap(searchImptLvl, FILE);
        }catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }
        finally {
            System.out.println("");
            System.out.println("     -----Have a productive day!-----");
            newToDoList.printStatement(toDoList);
        }

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
    new ToDoListRun();

}

}

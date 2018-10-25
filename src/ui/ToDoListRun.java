// referenced from LittleCalculatorLecLab

package ui;

import exceptions.AlreadyInList;
import exceptions.NullOutputException;
import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListRun {
    private final String FILE = "outputfile.txt";

    private model.ToDoList newToDoList = new model.ToDoList();
    private Task task;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> toDoList = newToDoList.load("outputfile.txt");


    private ToDoListRun() throws IOException {
        String operation;

        while (true){

            ToDoList newToDoList = new ToDoList();

            System.out.println("\n                  ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do, [2] cross-off or [3] print sorted to-do list\n\n");
            operation = scanner.nextLine();



            if (operation.equals("1")) {
                try {
                    task = newToDoList.newToDo(scanner, toDoList);
                    System.out.println("task " +task.getName());

                    if (task.getImportanceLvl().equals(""))
                    {
                        System.out.println("   ... cannot be added to the to-do list");
                    }
                    else {
                        System.out.println("   ... was added to the to-do list");}
                } catch (AlreadyInList alreadyInList) {
                  //  alreadyInList.printStackTrace();
                    System.out.println("This task is already in the list!");

                }
                finally {
                    System.out.println("\nDon't procrastinate!");
                }
                toDoList.add(task);



            }

            if (operation.equals("2")) {
                newToDoList.crossOff(scanner, toDoList);


            }
            if (operation.equals("3")) {

                ArrayList<Task> sortedList = newToDoList.sortedList(toDoList);
                newToDoList.printList(scanner, sortedList);
            }
            else if (operation.equals("quit")) {
                break;
            }


        }
        try {
            newToDoList.save(toDoList, FILE);
        } catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }
        finally {
            System.out.println("");
            System.out.println("     -----Have a productive day!-----");
            newToDoList.printStatement(toDoList);
        }

    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}

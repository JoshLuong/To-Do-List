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
    private model.ToDoList newToDoList = new model.ToDoList();
    public Task task;
    private final String FILE = "outputfile.txt";

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Task> toDoList = newToDoList.load("outputfile.txt");


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
                } catch (AlreadyInList alreadyInList) {
                    alreadyInList.printStackTrace();
                    System.out.println("This task is already in the list!");
                }
                toDoList.add(task);

               if (task.getImportanceLvl().equals(""))
               {
                   System.out.println("task :  "+task.getName()+" cannot be added");
               }
               else {
                System.out.println("you added " + task.getName() + " to the to-do list");}
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
        System.out.println("");
        System.out.println("    -----Have a productive day!-----");
        newToDoList.printStatement(toDoList);


        try {
            newToDoList.save(toDoList, FILE);
        } catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }

    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}

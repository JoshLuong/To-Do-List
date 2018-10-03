// referenced from LittleCalculatorLecLab

package ui;

import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListRun {
    private model.ToDoList newToDoList = new model.ToDoList();
    public Task task;

    private Scanner scanner = new Scanner(System.in);
    public Scanner getScanner(){
        return scanner;
    }
    ArrayList<Task> toDoList = newToDoList.load("outputfile.txt");
    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    private ToDoListRun() throws IOException {
        String operation = "";
        String name = "";
        String importanceLvl = "";

        while (true){

            Task opEntry = new Task(name, importanceLvl);
            ToDoList newToDoList = new ToDoList();

            System.out.println("                  ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do, [2] cross-off or [3] print sorted to-do list");
            operation = scanner.nextLine();
            System.out.println("");
            System.out.println("you selected: "+operation);



            if (operation.equals("1")) {
                task = newToDoList.newToDo(scanner);
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
                System.out.println("Tasks to complete based on level of importance:");
                ArrayList<Task> sortedList = newToDoList.sortedList(toDoList);
                for (Task t : sortedList) {
                    System.out.println(t.getName()+" : "+t.getImportanceLvl());
                }

            }
            else if (operation.equals("quit")) {
                break;
            }


        }
        System.out.println("");
        newToDoList.printStatement(toDoList);
        System.out.println("    -----Have a productive day!-----");
        newToDoList.save(toDoList, "outputfile.txt");

    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}

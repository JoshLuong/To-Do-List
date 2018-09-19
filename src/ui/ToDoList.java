package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    ArrayList<Tasks> operationLog = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    ArrayList result = new ArrayList();

    public ToDoList(){
        String operation = ""; // VARIABLE
        Tasks opEntry = new Tasks();

        while (true){

            System.out.println("     ------Welcome to 'Task Manager'------");
            System.out.println("Please select an option: [1] to-do or [2] cross-off");
            operation = scanner.nextLine();
            System.out.println("you selected: "+operation);

            if (operation.equals("1")) {
                opEntry.setOperation(operation);
                result = newToDo(opEntry);
                opEntry.setResult(result);
                operationLog.add(opEntry);
            }

            if (operation.equals("2")) {
                opEntry.setOperation(operation);
                result = crossOff(opEntry);
                opEntry.setResult(result);
                operationLog.add(opEntry);
            }
            else if (operation.equals("quit")) {
                break;
            }
            System.out.println("{Task(s) to complete: "+result+"}");


        }
        System.out.println("-----Have a productive day!-----");
        printStatement();
        //System.out.println("Tasks left to complete " + result);
        //System.out.println("operation log: "+operationLog);

    }
    // PASSING PARAMETER
    private ArrayList newToDo(Tasks logEntry) {
        System.out.println("Please enter the task to do");
        String first = scanner.nextLine();

        ArrayList newList = logEntry.addTask(first);
        return newList ;
    }

    private ArrayList crossOff(Tasks logEntry1){
        System.out.println("Please enter the task completed");
        String search = scanner.nextLine();
        ArrayList crossedList = logEntry1.subTask(result, search);
        return crossedList;
    }


    public static void main(String[] args) {
        new ToDoList();
    }

    public void printStatement(){
        if (result.size() == 0){
            System.out.println("Good job, you've finished all your tasks!");
        }
        else {
            System.out.println("Tasks left to complete " + result);
        }
    }
}

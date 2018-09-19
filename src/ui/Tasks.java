package ui;

import java.util.ArrayList;

public class Tasks {

    String operation = "";
    ArrayList<String> tasks = new ArrayList<>(); // FIELD
    ArrayList result = null;




    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ArrayList addTask(String bookAdded){
        tasks.add(bookAdded);
        return tasks;
    }
    public void removeTask(String bookToRemove){
        //ArrayList newOperands = new ArrayList();
        tasks.remove(bookToRemove);
        //System.out.println(tasks.remove(bookToRemove));
    }


    public ArrayList subTask(ArrayList<String> logEntryResult, String taskCompleted){
        // LOOP & CONDITION
        // when given 2 tasks in list, you can take out the FIRST only
        // when given 3 tasks, you can only take out the middle
        // LOGentryres should stay the same, so you know how far to go down list
        // make new list
        ArrayList cloned = (ArrayList) logEntryResult.clone();
// need to clone to make counter stable
        for (Object t: cloned ) {
            if (t.equals(taskCompleted)) {
                removeTask(taskCompleted);
            }
        }
        return tasks;

    }


    public void setResult(ArrayList result) {
        this.result = result;
    }


    public String toString(){
        return operation+" "+ tasks +" equals "+result;
    }

}

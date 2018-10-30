package test;

import model.Task;
import model.RegularTask;
import model.SchoolTask;
import ui.ToDoListManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


//  just save TASK as object, then load the TIMES from TASK into the times list
public class TestToDoListManager {



    // sorts an empty list
    @Test
    public void testSortedList(){
        ToDoListManager toDoListManager = new ToDoListManager();
        Map<String, Task> tasks = new HashMap<>();
        ArrayList<Task> newList = toDoListManager.sortedList(tasks);
        assertTrue(newList.size() == 0);

    }

    // sorts list of 2 tasks in order
    @Test
    public void testSortedListSome(){
        ToDoListManager toDoListManager = new ToDoListManager();
        Map<String, Task> tasks = new HashMap<>();
        Task task1 = new RegularTask("shop", "urgent");
        Task task2 = new RegularTask("homework", "low");
        tasks.put(task1.getName(), task1);
        tasks.put(task2.getName(), task2);
        ArrayList<Task> newList = toDoListManager.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("shop"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("homework"));
        assertTrue(newList.get(1).getImportanceLvl().equals("low"));

    }
    // sorts list of 2 tasks out of  order
    @Test
    public void testSortedListMixed(){
        ToDoListManager toDoListManager = new ToDoListManager();
        Map<String, Task> tasks = new HashMap<>();
        Task task1 = new RegularTask("shop", "low");
        Task task2 = new SchoolTask("homework", "urgent");
        tasks.put(task1.getName(), task1);
        tasks.put(task2.getName(), task2);
        ArrayList<Task> newList = toDoListManager.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("homework"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("shop"));
        assertTrue(newList.get(1).getImportanceLvl().equals("low"));

    }
    // sorts list with more tasks, out of order
    @Test
    public void testSortedListMore(){
        ToDoListManager toDoListManager = new ToDoListManager();
        Map<String, Task> tasks = new HashMap<>();
        Task task1 = new RegularTask("shop", "low");
        Task task2 = new SchoolTask("math", "urgent");
        Task task3 = new SchoolTask("biology", "medium");
        Task task4 = new SchoolTask("chemistry", "low");
        Task task5 = new SchoolTask("essay", "urgent");
        tasks.put(task1.getName(), task1);
        tasks.put(task2.getName(), task2);
        tasks.put(task3.getName(), task3);
        tasks.put(task4.getName(), task4);
        tasks.put(task5.getName(), task5);

        ArrayList<Task> newList = toDoListManager.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("math"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("essay"));
        assertTrue(newList.get(1).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(2).getName().equals("biology"));
        assertTrue(newList.get(3).getName().equals("shop"));
        assertTrue(newList.get(4).getName().equals("chemistry"));

    }
}

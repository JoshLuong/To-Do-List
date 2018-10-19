package test;

import model.Task;
import model.RegularTask;
import model.SchoolTask;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;



public class TestToDoList {



    // sorts an empty list
    @Test
    public void testSortedList(){
        ToDoList toDoList = new ToDoList();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> newList = toDoList.sortedList(tasks);
        assertTrue(newList.size() == 0);

    }

    // sorts list of 2 tasks in order
    @Test
    public void testSortedListSome(){
        ToDoList toDoList = new ToDoList();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new RegularTask("shop", "urgent");
        Task task2 = new RegularTask("homework", "low");
        tasks.add(task1);
        tasks.add(task2);
        ArrayList<Task> newList = toDoList.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("shop"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("homework"));
        assertTrue(newList.get(1).getImportanceLvl().equals("low"));

    }
    // sorts list of 2 tasks out of  order
    @Test
    public void testSortedListMixed(){
        ToDoList toDoList = new ToDoList();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new RegularTask("shop", "low");
        Task task2 = new SchoolTask("homework", "urgent", "biology");
        tasks.add(task1);
        tasks.add(task2);
        ArrayList<Task> newList = toDoList.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("homework"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("shop"));
        assertTrue(newList.get(1).getImportanceLvl().equals("low"));

    }
    // sorts list with more tasks, out of order
    @Test
    public void testSortedListMore(){
        ToDoList toDoList = new ToDoList();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new RegularTask("shop", "low");
        Task task2 = new SchoolTask("math", "urgent","School");
        Task task3 = new SchoolTask("biology", "medium","School");
        Task task4 = new SchoolTask("chemistry", "low", "School");
        Task task5 = new SchoolTask("essay", "urgent","School");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);

        ArrayList<Task> newList = toDoList.sortedList(tasks);
        assertTrue(newList.get(0).getName().equals("math"));
        assertTrue(newList.get(0).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(1).getName().equals("essay"));
        assertTrue(newList.get(1).getImportanceLvl().equals("urgent"));
        assertTrue(newList.get(2).getName().equals("biology"));
        assertTrue(newList.get(3).getName().equals("shop"));
        assertTrue(newList.get(4).getName().equals("chemistry"));

    }
}

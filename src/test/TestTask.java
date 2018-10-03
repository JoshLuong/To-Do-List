package test;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestTask {
//    Task t;
//    String name ="";
//    String importanceLvl ="";
//    @BeforeEach
//    public void setup(){
//        t = new Task(name, importanceLvl);
//    }


    // tests that a Task is added with "" name
    @Test
    public void testAddTaskNoName(){
        String name = "";
        String importanceLvl = "";
        Task task = new Task(name, importanceLvl);
        Task newTask = task.addTask("","urgent");
        assertTrue(newTask.getName().equals(""));
        assertTrue(newTask.getImportanceLvl().equals("urgent"));

    }
    // tests that a Task is added with "" importance level
    @Test
    public void testAddTaskNoImp(){
        String name = "";
        String importanceLvl = "";
        Task task = new Task(name, importanceLvl);
        Task newTask = task.addTask("shop","");
        assertTrue(newTask.getImportanceLvl().equals(""));
        assertTrue(newTask.getName().equals("shop"));

    }
    // tests that a Task is added with "" name & impt. lvl.
    @Test
    public void testAddTaskBothEmpty(){
        String name = "";
        String importanceLvl = "";
        Task task = new Task(name, importanceLvl);
        Task newTask = task.addTask("","");
        assertTrue(newTask.getName().equals(""));
        assertTrue(newTask.getImportanceLvl().equals(""));

    }

    // tests that an importance level is ""
    @Test
    public void testGetImportanceLvlEmpty(){
        Task task = new Task("","");
        assertTrue(task.getImportanceLvl().equals(""));

    }
    // tests that a importance level is "urgent"
    @Test
    public void testGetImportanceLvlUrgent(){
        Task task = new Task("","urgent");
        assertTrue(task.getImportanceLvl().equals("urgent"));

    }
    // tests that a name is returned
    @Test
    public void testGetNameEmpty(){
        Task task = new Task("shop","urgent");
        String name = task.getName();
        assertTrue(name.equals("shop"));

    }



}

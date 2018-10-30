package test;

import exceptions.NullOutputException;
import model.*;
import org.junit.jupiter.api.Test;
import ui.ToDoListManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
// TEST https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestSavable {
    @Test
    public void testSave() throws IOException{
        ArrayList<Task> tasks = new ArrayList<>();
        RegularTask task = new RegularTask("eat","urgent");
        tasks.add(task);
        Savable save = new ToDoListManager();
        try {
            save.save(tasks, "saveTestOutputfile.txt");
        } catch (NullOutputException e) {
            e.printStackTrace();
            fail("NullOutput Exception thrown!");
        }
        List<String> line = Files.readAllLines(Paths.get("saveTestOutputfile.txt"));
        assertTrue(line.get(line.size() -1).equals("eat urgent Regular"));

    }
    @Test
    public void testSaveExceptionWithNoOutput() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        RegularTask task = new RegularTask("eat","urgent");
        tasks.add(task);
        Savable save = new ToDoListManager();
        try{
        save.save(tasks, "null.txt");
        fail("Shouldn't catch");
        }

        catch (NullOutputException e){
        }

    }
}

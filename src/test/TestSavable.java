package test;

import model.Savable;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestSavable {
    @Test
    public void testSAve() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Task("eat","urgent");
        tasks.add(task);
        Savable save = new ToDoList();
        save.save(tasks, "saveTestOutputfile.txt");
        List<String> line = Files.readAllLines(Paths.get("saveTestOutputfile.txt"));
        assertTrue(line.get(line.size() -1).equals("eat urgent"));

    }
}

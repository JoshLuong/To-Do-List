package test;

import exceptions.NullOutputException;
import model.*;
import org.junit.jupiter.api.Test;
import ui.ToDoListManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TEST https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestSavable {
    @Test
    public void testSave() throws IOException{
        Map<String, Task> map = new HashMap<>();
        RegularTask task = new RegularTask("eat","urgent");
        map.put(task.getName(),task);
        Savable save = new SaveAndLoad();
        try {
            save.save(map, "saveTestOutputfile.txt");
        } catch (NullOutputException e) {
            e.printStackTrace();
            fail("NullOutput Exception thrown!");
        }
        List<String> line = Files.readAllLines(Paths.get("saveTestOutputfile.txt"));
        assertTrue(line.get(line.size() -1).equals("eat eat urgent Regular "));

    }
    @Test
    public void testSaveExceptionWithNoOutput() throws IOException {
        Map<String, Task> map = new HashMap<>();
        RegularTask task = new RegularTask("eat","urgent");
        map.put(task.getName(),task);
        Savable save = new SaveAndLoad();
        try{
        save.save(map, "null.txt");
        fail("Shouldn't catch");
        }

        catch (NullOutputException e){
        }

    }
}

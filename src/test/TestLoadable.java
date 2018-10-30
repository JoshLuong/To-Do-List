package test;

import model.EstCompletionTime;
import model.Loadable;
import ui.ToDoListManager;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    @Test
    public void testLoad1() throws IOException, ClassNotFoundException {
        Loadable load = new ToDoListManager();
        List<EstCompletionTime> times = new ArrayList<>();
        assertTrue(load.load("loadTestOutputfile.txt", times).size()== 2);
        List<String> line = Files.readAllLines(Paths.get("loadTestOutputfile.txt"));
        assertTrue(line.get(0).equals("shop urgent Regular"));
        assertTrue(line.get(1).equals("homework low School"));
        testLoad(load);
    }

    public void testLoad(Loadable load) throws IOException, ClassNotFoundException {
        List<EstCompletionTime> times = new ArrayList<>();
        Loadable toDoList = new ToDoListManager();
        toDoList.load("loadTestOutputfile.txt", times);

    }




}

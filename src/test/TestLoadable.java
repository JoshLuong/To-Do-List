package test;

import model.Loadable;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    @Test
    public void testLoad1() throws IOException {
        Loadable load = new ToDoList();
        assertTrue(load.load("loadTestOutputfile.txt").size()== 2);
        List<String> line = Files.readAllLines(Paths.get("loadTestOutputfile.txt"));
        assertTrue(line.get(0).equals("shop urgent"));
        assertTrue(line.get(1).equals("homework low"));
        testLoad(load);
    }

    public void testLoad(Loadable load) throws IOException {
        Loadable toDoList = new ToDoList();
        toDoList.load("loadTestOutputfile.txt");

    }



}

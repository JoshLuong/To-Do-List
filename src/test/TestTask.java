package test;

import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class TestTask {
    protected Task t;
    protected String name ="";
    protected String importanceLvl ="";

    protected String subject="";

    // tests that an importance level is ""
    @Test
    public void testGetImportanceLvlEmpty(){
        assertTrue(t.getImportanceLvl().equals(""));

    }

    // tests that a importance level is "urgent"
    @Test
    public void testGetImportanceLvlUrgent(){
        t.setImportanceLevel("urgent");
        assertTrue(t.getImportanceLvl().equals("urgent"));

    }

    // tests that a name is returned
    @Test
    public void testGetNameEmpty(){
        t.setName("shop");
        String name = t.getName();
        assertTrue(name.equals("shop"));

    }
}

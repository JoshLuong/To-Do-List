package test;
import model.RegularTask;
import org.junit.jupiter.api.BeforeEach;


public class TestRegularTask extends TestTask{
    @BeforeEach
    public void setup(){
        t = new RegularTask(name, importanceLvl, "Regular");
    }



}

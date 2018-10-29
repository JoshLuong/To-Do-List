package test;

import model.SchoolTask;
import org.junit.jupiter.api.BeforeEach;


public class TestSchoolTask extends TestTask {

    @BeforeEach
    public void setup(){
        t = new SchoolTask(name, importanceLvl);
    }


}

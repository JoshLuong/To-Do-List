package model;

import java.util.List;

public class TaskFactory {
    public Task makeTask(String newTask, String type, String lvl) {
        Task task = null;
        if (type.equals("Regular")){
            task = new RegularTask(newTask, lvl);
        }
        if (type.equals("School")){
            task = new SchoolTask(newTask, lvl);}

        return task;
    }
}

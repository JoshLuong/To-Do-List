package model;

import java.io.Serializable;
import java.util.*;

public class EstCompletionTime implements Serializable{
    private String day;
    private ArrayList<Task> tasks = new ArrayList<>();


    public EstCompletionTime(String day){
        this.day = day;

    }

    public boolean hasTask(Task task){
        return this.tasks.contains(task);
    }

    public void addTask(Task task){
        if (!this.equals(task.getTime())){
            this.tasks.add(task);
            task.addTime(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstCompletionTime that = (EstCompletionTime) o;
        return Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {

        return Objects.hash(day);
    }

    public String getDay(){return day;}

    public ArrayList<Task> getTasks() { return tasks; }
}

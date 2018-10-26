package model;

import java.util.*;

public class EstCompletionTime {
    private int hours;
    private int minutes;
    private String time;
    private ArrayList<Task> tasks = new ArrayList<>();


    public EstCompletionTime(int hours, int minutes ){
        this.hours = hours;
        this.minutes = minutes;
        time = Integer.toString(hours)+":"+Integer.toString(minutes);

    }

    public boolean hasTask(Task task){
        return this.tasks.contains(task);
    }

//    public void addTask(Task task){
//        if(!tasks.contains(task)) {
//            tasks.add(task);
//            task.addTime(this);
//        }
//    }

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
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time);
    }

    public String getTime(){return time;}

    public ArrayList<Task> getTasks() { return tasks; }
}

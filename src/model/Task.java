package model;

import ui.ToDoListRun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashSet;

public abstract class Task {
    protected String name;
    protected String importanceLvl;
    protected String type;
    protected Collection<EstCompletionTime> times;
    protected EstCompletionTime time;

    public Task (String name, String importanceLvl, String type){
        this.name = name;
        this.importanceLvl = importanceLvl;
        this.type = type;
        times = new HashSet<>();
        time = new EstCompletionTime(0,0);
    }

    // EFFECTS: return the RegularTask's importance level
    public String getImportanceLvl (){
        return this.importanceLvl;
    }
    // REQUIRES: name cannot be empty
    // EFFECTS: returns RegularTask's name
    public String getName(){
        return this.name;
    }
    // EFFECTS: returns Task type
    public String getType(){
        return this.type;
    }
    //
    public void setName(String name){
        this.name = name;
    }
    public void setImportanceLevel(String lvl){
        this.importanceLvl = lvl;
    }

//    public void addTime(EstCompletionTime time){
//        if (!times.contains(time)) {
//            times.add(time);
//            time.addTask(this);
//        }
//    }

    public void addTime(EstCompletionTime time){
        if (!time.hasTask(this)){
            time.addTask(this);
        }
    }
//    public ArrayList<EstCompletionTime> getTime(){
//        ArrayList<EstCompletionTime> listTimes = new ArrayList<>();
//        for (EstCompletionTime t : times){
//            listTimes.add(t);
//        }
//        return  listTimes;
//    }

    public EstCompletionTime getTime() {
        return time;
    }


//    public ArrayList<String> getTime(){
//        ArrayList<String> listTimes = new ArrayList<>();
//        for (EstCompletionTime t : times){
//            listTimes.add(t.getTime());
//        }
//
//        return  listTimes;
//    }



    // Abstract method
    public abstract String done();

    public abstract String getSubject();
}

package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public abstract class Task implements Serializable {
    protected String name;
    protected String importanceLvl;
    protected String type;
    protected EstCompletionTime time;

    public Task (String name, String importanceLvl, String type){
        this.name = name;
        this.importanceLvl = importanceLvl;
        this.type = type;
        time = new EstCompletionTime("");
        time.addTask(this);
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
    public void setName(String name){
        this.name = name;
    }
    public void setImportanceLevel(String lvl){
        this.importanceLvl = lvl;
    }


    public void addTime(EstCompletionTime time){
        this.time = time;
        if (!time.hasTask(this)){
            time.addTask(this);
        }
    }

    public EstCompletionTime getTime() {
        return time;
    }


    // Abstract method
    public abstract String done();

}

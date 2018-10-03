package model;

public class Task // Implements OTher{
{

    String name = "";
    String importanceLvl = "";

    // MODIFIES: this
    // EFFECTS: returns a new Task

    //@Override on getname and getimptlvl
    public Task addTask(String taskAdded, String level){
        return new Task(taskAdded, level);

    }

    // EFFECTS: return the Task's importance level
    public String getImportanceLvl (){
        return importanceLvl;
    }
    // REQUIRES: name cannot be empty
    // EFFECTS: returns Task's name
    public String getName(){
        return name;
    }

    // REQUIRES: operation and name is not empty
    // MODIFIES: this
    // EFFECTS: constructs new Task with name and importance level
    public Task (String name, String importanceLvl){
        this.name = name;
        this.importanceLvl = importanceLvl;
    }

}

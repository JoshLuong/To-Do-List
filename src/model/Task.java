package model;

public abstract class Task {
    protected String name;
    protected String importanceLvl;
    protected String type;

    public Task (String name, String importanceLvl, String type){
        this.name = name;
        this.importanceLvl = importanceLvl;
        this.type = type;
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

    // Abstract method
    public abstract String done();

    public abstract String getSubject();
}

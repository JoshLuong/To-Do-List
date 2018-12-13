package model;

public class RegularTask extends Task {

    // REQUIRES: operation and name is not empty
    // MODIFIES: this
    // EFFECTS: constructs new RegularTask with name and importance level
    public RegularTask(String name, String importanceLvl){
        super(name,importanceLvl, "Regular");
    }



}

package model;

public class RegularTask extends Task {

    // REQUIRES: operation and name is not empty
    // MODIFIES: this
    // EFFECTS: constructs new RegularTask with name and importance level
    public RegularTask(String name, String importanceLvl, String type){
        this.name = name;
        this.importanceLvl = importanceLvl;
        this.type = type;
    }


    @Override
    //EFFECTS: returns string specific to RegularTask
    public String done() {
        return "You've completed all of your regular tasks!";
    }
}

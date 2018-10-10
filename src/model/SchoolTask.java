package model;

public class SchoolTask extends Task {
    public SchoolTask (String name, String importanceLvl, String type){
        this.name = name;
        this.importanceLvl = importanceLvl;
        this.type = type;
    }


    @Override
    //EFFECTS: returns string specific to SchoolTask
    public String done() {
        return "You've finished all of your homework, NERD!";

    }


}

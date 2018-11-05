package model;

public class SchoolTask extends Task {

    public SchoolTask (String name, String importanceLvl){
        super(name ,importanceLvl, "School");

    }


    @Override
    //EFFECTS: returns string specific to SchoolTask
    public String done() {
        return "\nYou've finished all of your homework, NERD!";

    }






}

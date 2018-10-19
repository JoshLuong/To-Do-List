package model;

public class SchoolTask extends Task {
    String subject;

    public SchoolTask (String name, String importanceLvl, String subject){
        super(name ,importanceLvl, "School");
        this.subject = subject;

    }


    @Override
    //EFFECTS: returns string specific to SchoolTask
    public String done() {
        return "\nYou've finished all of your homework, NERD!";

    }

    @Override
    public String getSubject() {
        return subject;
    }


}

package exceptions;

public class AlreadyInList extends Exception {
    public AlreadyInList(){
        super("This task is already present!");
    }
}

package exceptions;

public class EmptyTaskException extends Exception {
    public EmptyTaskException(){
        super("This task is empty!");
    }
}

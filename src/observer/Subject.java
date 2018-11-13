package observer;

import model.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject{
    private List<ToDoListObserver> observers = new ArrayList<>();

    public void addObserver(ToDoListObserver observer){
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void notifyObservers(Task task){
        for (ToDoListObserver o : observers){
            o.update(task);
        }

    }
}

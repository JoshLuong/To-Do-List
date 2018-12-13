package observer;

import model.Task;

public interface ToDoListObserver {
    void  update(Task task);
}

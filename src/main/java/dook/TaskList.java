package dook;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public int size() {
        return tasks.size();
    }

    public Task markTask(int taskNumber) throws DookException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DookException("That task number is out of range.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int taskNumber) throws DookException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DookException("That task number is out of range.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskNumber) throws DookException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DookException("That task number is out of range.");
        }
        return tasks.remove(taskNumber - 1);
    }
}

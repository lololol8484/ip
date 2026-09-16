package dook;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final Path FILE_PATH = Path.of("./data/dook.txt");

    public static void saveTasks(ArrayList<Task> tasks) throws DookException {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Todo) {
                data.append("T | ")
                    .append(task.isDone() ? "1" : "0")
                    .append(" | ")
                    .append(task.getDescription())
                    .append("\n");
            }
            else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                data.append("D | ")
                    .append(task.isDone() ? "1" : "0")
                    .append(" | ")
                    .append(task.getDescription())
                    .append(" | ")
                    .append(deadline.getBy())
                    .append("\n");
            }
            else if (task instanceof Event) {
                Event event = (Event) task;
                data.append("E | ")
                    .append(task.isDone() ? "1" : "0")
                    .append(" | ")
                    .append(task.getDescription())
                    .append(" | ")
                    .append(event.getFrom())
                    .append(" | ")
                    .append(event.getTo())
                    .append("\n");
            }
        }
        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.writeString(FILE_PATH, data.toString());
        }
        catch (IOException e) {
            throw new DookException("Could not save tasks.");
        }
    }

    public static ArrayList<Task> loadTasks() throws DookException {
        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }
        String data;
        try {
            data = Files.readString(FILE_PATH);
        }
        catch (IOException e) {
            throw new DookException("Could not load tasks.");
        }
        String[] lines = data.split("\\R");
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                throw new DookException("The data file contains invalid task data.");
            }
            String type = parts[0];
            String status = parts[1];
            if (!status.equals("0") && !status.equals("1")) {
                throw new DookException("The data file contains an invalid task status.");
            }
            Task task;
            if (type.equals("T")) {
                if (parts.length != 3) {
                    throw new DookException("The data file contains an invalid todo.");
                }
                task = new Todo(parts[2]);
            }
            else if (type.equals("D")) {
                if (parts.length != 4) {
                    throw new DookException("The data file contains an invalid deadline.");
                }
                task = new Deadline(parts[2], parts[3]);
            }
            else if (type.equals("E")) {
                if (parts.length != 5) {
                    throw new DookException("The data file contains an invalid event.");
                }
                task = new Event(parts[2], parts[3], parts[4]);
            }
            else {
                throw new DookException("The data file contains an invalid task type.");
            }
            if (status.equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }
}

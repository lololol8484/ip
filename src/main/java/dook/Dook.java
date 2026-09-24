package dook;

public class Dook {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final TaskList tasks;

    public Dook(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        TaskList loadedTasks;
        try {
            loadedTasks = storage.loadTasks();
        }
        catch (DookException e) {
            ui.showErrorMessage(e);
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public void run() {
        ui.showGreetingMessage();
        while (true) {
            String input = ui.readLine();
            try {
                CommandType commandType = parser.parseCommand(input);
                if (commandType == CommandType.BYE) {
                    ui.showExitMessage();
                    break;
                }
                else if (commandType == CommandType.LIST) {
                    ui.showTaskList(tasks);
                }
                else if (commandType == CommandType.MARK) {
                    int taskNumber = parser.parseMarkCommand(input);
                    Task task = tasks.markTask(taskNumber);
                    storage.saveTasks(tasks);
                    ui.showMarkConfirmation(task);
                }
                else if (commandType == CommandType.UNMARK) {
                    int taskNumber = parser.parseUnmarkCommand(input);
                    Task task = tasks.unmarkTask(taskNumber);
                    storage.saveTasks(tasks);
                    ui.showUnmarkConfirmation(task);
                }
                else if (commandType == CommandType.TODO) {
                    Todo todo = parser.parseTodoCommand(input);
                    tasks.addTask(todo);
                    storage.saveTasks(tasks);
                    ui.showAddTaskConfirmation(todo, tasks.size());
                }
                else if (commandType == CommandType.DEADLINE) {
                    Deadline deadline = parser.parseDeadlineCommand(input);
                    tasks.addTask(deadline);
                    storage.saveTasks(tasks);
                    ui.showAddTaskConfirmation(deadline, tasks.size());
                }
                else if (commandType == CommandType.EVENT) {
                    Event event = parser.parseEventCommand(input);
                    tasks.addTask(event);
                    storage.saveTasks(tasks);
                    ui.showAddTaskConfirmation(event, tasks.size());
                }
                else if (commandType == CommandType.DELETE) {
                    int taskNumber = parser.parseDeleteCommand(input);
                    Task deletedTask = tasks.deleteTask(taskNumber);
                    storage.saveTasks(tasks);
                    ui.showDeleteConfirmation(deletedTask, tasks.size());
                }
                else if (commandType == CommandType.UNKNOWN) {
                    throw new DookException("I'm sorry, but I don't know what that means :-(");
                }
                else {
                    throw new DookException("An unexpected command type was encountered.");
                }
            }
            catch (DookException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Dook("data/dook.txt").run();
    }
}

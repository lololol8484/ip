package dook;

import java.util.Scanner;
import java.util.ArrayList;

public class Dook {
    private static final String BANNER = "\t ____   ___   ___  _  __\n"
                                        + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                        + "\t| | | | | | | | | | ' / \n"
                                        + "\t| |_| | |_| | |_| | . \\ \n"
                                        + "\t|____/ \\___/ \\___/|_|\\_\\\n";
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void printGreetingMessage() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println(BANNER);
        System.out.println("\tHello! I'm Dook.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    private static void printExitMessage() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    private static void printTaskList() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    private static void printMarkConfirmation(int taskNumber) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(taskNumber - 1));
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Marks the specified task as done and prints a confirmation message.
     *
     * @param input the user's mark command
     * @throws DookException if the task number is not an integer or is out of range
     */
    private static void handleMarkCommand(String input) throws DookException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(MARK_COMMAND.length()));
        }
        catch (NumberFormatException e) {
            throw new DookException("The task number must be an integer.");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DookException("That task number is out of range.");
        }
        tasks.get(taskNumber - 1).markAsDone();
        Storage.saveTasks(tasks);
        printMarkConfirmation(taskNumber);
    }

    private static void printUnmarkConfirmation(int taskNumber) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.get(taskNumber - 1));
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Marks the specified task as not done and prints a confirmation message.
     *
     * @param input the user's unmark command
     * @throws DookException if the task number is not an integer or is out of range
     */
    private static void handleUnmarkCommand(String input) throws DookException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(UNMARK_COMMAND.length()));
        }
        catch (NumberFormatException e) {
            throw new DookException("The task number must be an integer.");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DookException("That task number is out of range.");
        }
        tasks.get(taskNumber - 1).markAsNotDone();
        Storage.saveTasks(tasks);
        printUnmarkConfirmation(taskNumber);
    }

    private static void printAddTaskConfirmation() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks.get(tasks.size() - 1));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Creates and adds a Todo, Deadline, or Event based on the user's command.
     *
     * @param input the user's add task command
     * @throws DookException if the command contains invalid or incomplete task details, or if the task list is full
     */
    private static void handleAddTaskCommand(String input) throws DookException {
        if (input.startsWith(TODO_COMMAND)) {
            String description = input.substring(TODO_COMMAND.length());
            if (description.trim().isEmpty()) {
                throw new DookException("The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(description));
        }
        else if (input.startsWith(DEADLINE_COMMAND)) {
            String content = input.substring(DEADLINE_COMMAND.length());
            if (content.trim().isEmpty()) {
                throw new DookException("The content of a deadline cannot be empty.");
            }
            // Split the content into description and by parts using the specified delimiter
            String[] deadlineParts = content.split(" /by ");
            if (deadlineParts.length != 2) {
                throw new DookException("A deadline must include exactly one description and one /by date.");
            }
            if (deadlineParts[0].trim().isEmpty()) {
                throw new DookException("The description of a deadline cannot be empty.");
            }
            if (deadlineParts[1].trim().isEmpty()) {
                throw new DookException("The /by date of a deadline cannot be empty.");
            }
            tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
        }
        else if (input.startsWith(EVENT_COMMAND)) {
            String content = input.substring(EVENT_COMMAND.length());
            if (content.trim().isEmpty()) {
                throw new DookException("The content of an event cannot be empty.");
            }
            // Split the content into description, from, and to parts using the specified delimiters
            String[] eventParts = content.split(" /from | /to ");
            if (eventParts.length != 3) {
                throw new DookException("An event must include exactly one description, one /from date, and one /to date.");
            }
            if (eventParts[0].trim().isEmpty()) {
                throw new DookException("The description of an event cannot be empty.");
            }
            if (eventParts[1].trim().isEmpty()) {
                throw new DookException("The /from date of an event cannot be empty.");
            }
            if (eventParts[2].trim().isEmpty()) {
                throw new DookException("The /to date of an event cannot be empty.");
            }
            tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
        }
        Storage.saveTasks(tasks);
        printAddTaskConfirmation();
    }

    /**
     * Identifies the user's command and delegates it to the appropriate handler.
     *
     * @param input the user's command
     * @throws DookException if the command is unknown or if the delegated handler encounters an error
     */
    private static void handleCommand(String input) throws DookException {
        if (input.equals(LIST_COMMAND)) {
            printTaskList();
        }
        else if (input.startsWith(MARK_COMMAND)) {
            handleMarkCommand(input);
        }
        else if (input.startsWith(UNMARK_COMMAND)) {
            handleUnmarkCommand(input);
        }
        else if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
            handleAddTaskCommand(input);
        }
        else {
            throw new DookException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void printErrorMessage(DookException e) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tOOPS!!! " + e.getMessage());
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public static void main(String[] args) {
        try {
            tasks = Storage.loadTasks();
        }
        catch (DookException e) {
            printErrorMessage(e);
        }
        printGreetingMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(BYE_COMMAND)) {
                printExitMessage();
                break;
            }

            try {
                handleCommand(input);
            }
            catch (DookException e) {
                printErrorMessage(e);
            }
        }
    }
}

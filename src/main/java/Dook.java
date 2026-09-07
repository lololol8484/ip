import java.util.Scanner;

public class Dook {
    private static final String BANNER = "\t ____   ___   ___  _  __\n"
                                        + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                        + "\t| | | | | | | | | | ' / \n"
                                        + "\t| |_| | |_| | |_| | . \\ \n"
                                        + "\t|____/ \\___/ \\___/|_|\\_\\\n";
    private static final int MAX_TASKS = 100;
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

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
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    private static void printMarkConfirmation(int taskNumber) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks[taskNumber - 1]);
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Marks the specified task as done and prints a confirmation message.
     *
     * @param input the user's mark command
     */
    private static void handleMarkCommand(String input) {
        int taskNumber = Integer.parseInt(input.substring(MARK_COMMAND.length()));
        tasks[taskNumber - 1].markAsDone();
        printMarkConfirmation(taskNumber);
    }

    private static void printUnmarkConfirmation(int taskNumber) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks[taskNumber - 1]);
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Marks the specified task as not done and prints a confirmation message.
     *
     * @param input the user's unmark command
     */
    private static void handleUnmarkCommand(String input) {
        int taskNumber = Integer.parseInt(input.substring(UNMARK_COMMAND.length()));
        tasks[taskNumber - 1].markAsNotDone();
        printUnmarkConfirmation(taskNumber);
    }

    private static void printAddTaskConfirmation() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks[taskCount - 1]);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    /**
     * Creates and adds a Todo, Deadline, or Event based on the user's command.
     *
     * @param input the user's add task command
     */
    private static void handleAddTaskCommand(String input) {
        if (input.startsWith(TODO_COMMAND)) {
            tasks[taskCount] = new Todo(input.substring(TODO_COMMAND.length()));
        }
        else if (input.startsWith(DEADLINE_COMMAND)) {
            // Split the input into description and by parts using the specified delimiter
            String[] deadlineParts = input.substring(DEADLINE_COMMAND.length()).split(" /by ");
            tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
        }
        else if (input.startsWith(EVENT_COMMAND)) {
            // Split the input into description, from, and to parts using the specified delimiters
            String[] eventParts = input.substring(EVENT_COMMAND.length()).split(" /from | /to ");
            tasks[taskCount] = new Event(eventParts[0], eventParts[1], eventParts[2]);
        }
        taskCount++;
        printAddTaskConfirmation();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(BYE_COMMAND)) {
                printExitMessage();
                break;
            }

            if (input.equals(LIST_COMMAND)) {
                printTaskList();
                continue;
            }

            if (input.startsWith(MARK_COMMAND)) {
                handleMarkCommand(input);
                continue;
            }

            if (input.startsWith(UNMARK_COMMAND)) {
                handleUnmarkCommand(input);
                continue;
            }

            if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
                handleAddTaskCommand(input);
                continue;
            }
        }
    }
}

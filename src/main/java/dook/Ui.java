package dook;

import java.util.Scanner;

public class Ui {
    private static final String BANNER = "\t ____   ___   ___  _  __\n"
                                        + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                        + "\t| | | | | | | | | | ' / \n"
                                        + "\t| |_| | |_| | |_| | . \\ \n"
                                        + "\t|____/ \\___/ \\___/|_|\\_\\\n";
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void showGreetingMessage() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println(BANNER);
        System.out.println("\tHello! I'm Dook.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showExitMessage() {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tHere are the tasks in your list:");
        int taskNumber = 1;
        for (Task task : tasks) {
            System.out.println("\t" + taskNumber + "." + task);
            taskNumber++;
        }
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showMarkConfirmation(Task task) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showUnmarkConfirmation(Task task) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showAddTaskConfirmation(Task task, int taskCount) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showDeleteConfirmation(Task deletedTask, int taskCount) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + deletedTask);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }

    public void showErrorMessage(DookException e) {
        System.out.println("\t" + LINE_SEPARATOR);
        System.out.println("\tOOPS!!! " + e.getMessage());
        System.out.println("\t" + LINE_SEPARATOR + "\n");
    }
}

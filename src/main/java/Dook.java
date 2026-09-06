import java.util.Scanner;

public class Dook {
    static final String banner = "\t ____   ___   ___  _  __\n"
                                + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                + "\t| | | | | | | | | | ' / \n"
                                + "\t| |_| | |_| | |_| | . \\ \n"
                                + "\t|____/ \\___/ \\___/|_|\\_\\\n";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println(banner);
        System.out.println("\tHello! I'm Dook.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t" + (i + 1) + "." + tasks[i]);
                }
                System.out.println("\t____________________________________________________________\n");
                continue;
            }

            if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + tasks[taskNumber]);
                System.out.println("\t____________________________________________________________\n");
                continue;
            }

            if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + tasks[taskNumber]);
                System.out.println("\t____________________________________________________________\n");
                continue;
            }

            if (input.startsWith("todo ")) {
                tasks[taskCount] = new Todo(input.substring(5));
            }
            else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
            }
            else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            }
            taskCount++;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t  " + tasks[taskCount - 1]);
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
            System.out.println("\t____________________________________________________________\n");
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________\n");
    }
}

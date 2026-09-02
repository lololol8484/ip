import java.util.Scanner;

public class Dook {
    static final String banner = "\t ____   ___   ___  _  __\n"
                                + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                + "\t| | | | | | | | | | ' / \n"
                                + "\t| |_| | |_| | |_| | . \\ \n"
                                + "\t|____/ \\___/ \\___/|_|\\_\\\n";
    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println(banner);
        System.out.println("\tHello! I'm Dook.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        while(true) {
            String input = in.nextLine();
            if(input.equals("bye")) {
                break;
            }

            if(input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for(int i = 0; i < taskCount; i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("\t____________________________________________________________\n");
                continue;
            }

            tasks[taskCount] = input;
            taskCount++;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tAdded: " + input);
            System.out.println("\t____________________________________________________________\n");
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________\n");
    }
}

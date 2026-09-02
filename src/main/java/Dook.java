import java.util.Scanner;

public class Dook {
    static final String banner = "\t ____   ___   ___  _  __\n"
                                + "\t|  _ \\ / _ \\ / _ \\| |/ /\n"
                                + "\t| | | | | | | | | | ' / \n"
                                + "\t| |_| | |_| | |_| | . \\ \n"
                                + "\t|____/ \\___/ \\___/|_|\\_\\\n";

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
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________\n");
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________\n");
    }
}

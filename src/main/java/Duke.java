import java.util.Scanner;

public class Duke {
    static Task[] botList = new Task[100];
    static int numItems = 0;

    static String introMsg = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";
    static String byeMsg = "Bye. Hope to see you again soon!";
    static String listMsg = "Here are the tasks in your list:\n";
    static String doneMsg = "Nice! I've marked this task as done: \n";
    static String todoMsg = "Got it. I've added this task: \n";

    static void introduce() {
        System.out.println(introMsg);
    }

    static void reply() {
        Scanner sc = new Scanner(System.in);
        outerLoop:
        while (true) {
            String userInput = sc.nextLine();
            String[] tokens = userInput.split("\\s+", 2);
            String command = tokens[0];
            String param = tokens.length == 1 ? null : tokens[1].strip();

            switch (command) {
                case "todo":
                    System.out.println(todoMsg);
                    botList[numItems] = new Todo(param);
                    System.out.println(botList[numItems]);
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    break;
                case "list":
                    System.out.println(listMsg);
                    for (int i = 0; i < numItems; i++) {
                        System.out.println((i + 1) + "." + botList[i]);
                    }
                    break;
                case "deadline":
                    System.out.println(todoMsg);

                    String[] taskItems = param.split(" /by ", 2);
                    String desc = taskItems[0].strip();
                    String due = taskItems[1].strip();

                    botList[numItems] = new Deadline(desc, due);
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    break;
                case "event":
                    System.out.println(todoMsg);

                    taskItems = param.split(" /at ", 2);
                    desc = taskItems[0].strip();
                    due = taskItems[1].strip();

                    botList[numItems] = new Event(desc, due);
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    break;
                case "done":
                    System.out.println(doneMsg);
                    int intParam = Integer.parseInt(param) - 1;
                    botList[intParam].markAsDone();
                    System.out.println(botList[intParam]);
                    break;
                case "bye":
                    System.out.println(byeMsg);
                    break outerLoop;
                default: // Adds task
                    botList[numItems++] = new Task(userInput);
                    System.out.println("added: " + userInput);
            }
        }
    }

    static void run() {
        introduce();
        reply();
    }

    public static void main(String[] args) {
        run();
    }
}

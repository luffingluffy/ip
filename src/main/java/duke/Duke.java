package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a chat-bot that stores list of tasks/events that the user
 * can add/delete/complete.
 */
public class Duke {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    /**
     * Starts the program by loading/creating the data file.
     */
    static void init() {
        storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
        ui = new Ui();
    }

    /**
     * Handles the program's reply until a bye command is given.
     */
    static void reply() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            try {
                Parser.parse(userInput, taskList);
                if (userInput.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                storage.write(taskList);
            }
        }
    }

    /**
     * Runs the program.
     */
    public static void main(String[] args) {
        init();
        ui.greet();
        reply();
    }
}

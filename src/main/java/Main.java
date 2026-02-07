import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // launching the app
        run();
    }

    public static void run() {
        // debugging
        Task test = new Task("ТЕСТИРОВАНИЕ", "ТЕСТИРОВАНИЕ ОПИСАНИЕ", LocalDate.now(), false);
        taskStorage.add(test);

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome in task manager! Choose Options to-do");
        System.out.println("1 - Create New Task\n2 - View Tasks \n3 - Update Task\n4 - Delete Task\n\n0 - Quit");

        taskManager(input);
    }


    // task manager storage, with all tasks
    private static ArrayList<Task> taskStorage = new ArrayList<>();
    private static void taskManager(Scanner input) throws InputMismatchException {
        byte choice;
        do {
            // choosing the option of CRUD
            try {
                choice = input.nextByte();

                input.nextLine();

                // 1, 2, 3, 4 cases
                switch (choice) {
                    case 1 -> createTask(input);
                    case 2 -> readTasks();
                    case 3 -> updateTask(input);
                    default -> System.out.println("Please enter existing number");
                }
            } catch (InputMismatchException e) {
                // clean input from mismatched data
                input.nextLine();
                System.out.println("Please write only numbers!");
                choice = -1;
            }
        } while (choice != 0);
    }


    private static void createTask(Scanner choiceOne) {
        System.out.println("Enter task name:");
        String name = choiceOne.nextLine();

        System.out.println("Enter description:");
        String desc = choiceOne.nextLine();

        Task task = new Task(name, desc, LocalDate.now(), false);
        taskStorage.add(task);
        System.out.println("Task was successfully created!");
    }


    private static void readTasks() {
        if (taskStorage.isEmpty()) {
            printNoTasks();
        } else {
            System.out.println("Your tasks:");
            showEachTask();
        }
    }


    private static void updateTask(Scanner input) {
        if (taskStorage.isEmpty()) {
            printNoTasks();
        } else {
            System.out.println("Choose one of the tasks, to edit:");
            showEachTask();

            int replaceChoice;
            do {
                replaceChoice = input.nextByte();

            } while (replaceChoice != 0);
        }
    }


    private static void showEachTask() {
        int counter = 0;
        for (Task eachTask : taskStorage) {
            counter++;
            System.out.println(
                    counter +
                            ". Title: " + eachTask.getTitle() +
                            ", Description: " + eachTask.getDescription() +
                            ", Creation Date: " + eachTask.getDate() +
                            (eachTask.getIsCompleted() ? " [DONE]" : " [UNDONE]")
            );
        }
    }

    private static void printNoTasks() {
        System.out.println("You don't have any tasks added yet!");
    }
}
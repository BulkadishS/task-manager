package com.bulkadishs.task_manager;

import com.bulkadishs.task_manager.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
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
//        Task test = new Task("ТЕСТИРОВАНИЕ", "ТЕСТИРОВАНИЕ ОПИСАНИЕ", LocalDate.now(), false);
//        taskStorage.add(test);

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
                    case 4 -> deleteTask(input);
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


    private static void createTask(Scanner input) {
        System.out.println("Enter task name:");
        String name = input.nextLine();

        System.out.println("Enter description:");
        String desc = input.nextLine();

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
            System.out.println("Enter number of the task, that you want to mark as done:");
            showEachTask();

            int index = input.nextInt() - 1;

            if (index >= 0 && index < taskStorage.size()) {
                Task choosedTask = taskStorage.get(index);
                choosedTask.setIsCompleted(true);
                System.out.println("Marked as completed, well done!");
            } else {
                System.out.println("Please choose existing one task!");
            }
        }
    }


    private static void deleteTask(Scanner input) {
        if (taskStorage.isEmpty()) {
            printNoTasks();
        } else {
            System.out.println("Enter number of the task, that you want to delete:");
            showEachTask();
            int index = input.nextInt() - 1;

            if (index >= 0 && index < taskStorage.size()) {
                Task choosedTask = taskStorage.get(index);
                taskStorage.remove(choosedTask);
                System.out.println("Successfully removed task number [" + (index + 1) + "]!");
            } else {
                System.out.println("Please choose existing one task!");
            }
        }
    }

//    private static void createStorageFile() throws FileNotFoundException{
//        try {
//            String separator = File.separator;
//            String path = separator + "Users" + separator + "denis_wr40x3z" + separator + "IdeaProjects" + separator + "learn" + separator + "src" + separator + "main" + separator + "java" + separator + "com" + separator + "bulkadishs" + separator + "task_manager" + separator + "storage";
//
//            File userStorage = new File(path);
//
//        } catch (FileNotFoundException) {
//            System.out.println("");
//        }
//    }

    private static void showEachTask() {
        int index = 0;
        for (Task eachTask : taskStorage) {
            index++;
            System.out.println(
                    index +
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
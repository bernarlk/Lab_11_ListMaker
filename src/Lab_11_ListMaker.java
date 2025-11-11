/*
 * Lab 11: List Maker
 * Course: IT1090C / IT6090C
 * Author: Your Name
 * Date: November 2024
 * Description:
 * This program creates a simple list editor using an ArrayList of Strings.
 * The user can add, delete, insert, print, or quit using a menu-driven interface.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Lab_11_ListMaker {

    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String cmd;
        boolean done = false;

        do {
            displayList();
            displayMenu();
            cmd = SafeInput.getRegExString(in, "Enter command [A,D,I,P,Q]: ", "[AaDdIiPpQq]");
            cmd = cmd.toUpperCase();

            switch (cmd) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?"))
                        done = true;
                    break;
            }

        } while (!done);

        System.out.println("Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        System.out.println("[A] Add  [D] Delete  [I] Insert  [P] Print  [Q] Quit");
    }

    private static void displayList() {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("  (empty list)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to add");
        list.add(item);
        System.out.println("Item added to end of list.");
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        displayList();
        int choice = SafeInput.getRangedInt(in, "Enter item number to delete", 1, list.size());
        list.remove(choice - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        if (list.isEmpty()) {
            System.out.println("List is empty. Use Add to create your first item.");
            return;
        }

        displayList();
        int position = SafeInput.getRangedInt(in, "Enter position to insert (1 - " + (list.size() + 1) + ")", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert");
        list.add(position - 1, item);
        System.out.println("Item inserted.");
    }

    private static void printList() {
        System.out.println("\n----- Current List -----");
        if (list.isEmpty()) {
            System.out.println("(List is empty)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
        System.out.println("------------------------");
    }
}

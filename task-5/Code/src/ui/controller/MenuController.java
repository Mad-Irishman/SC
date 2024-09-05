package ui.controller;

import autoservice.manager.impl.ServiceManager;

import ui.view.menu.Navigator;

import java.util.Scanner;

public class MenuController {
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(ServiceManager serviceManager) {
        this.builder = new Builder(serviceManager);
        this.navigator = new Navigator(builder.getRootMenu());
    }

    public void run() {
        builder.buildMenu(navigator);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                navigator.printMenu();
                System.out.println("Enter the menu item number: ");
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input) - 1;

                if (choice < 0 || choice >= navigator.getMenuItemCount()) {
                    throw new IndexOutOfBoundsException("Menu item number out of range.");
                }

                navigator.navigate(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid menu item number. Please choose a valid option.");
            } catch (NullPointerException e) {
                System.out.println("Error: Current menu is not set. Returning to root menu.");
                navigator.goToRootMenu();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

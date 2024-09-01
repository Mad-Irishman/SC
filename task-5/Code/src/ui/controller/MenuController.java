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
        navigator.setCurrentMenu(builder.getRootMenu());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            navigator.printMenu();

            System.out.println("Enter the menu item number: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input) - 1;
                navigator.navigate(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter the menu item number.");
            }
        }

    }
}

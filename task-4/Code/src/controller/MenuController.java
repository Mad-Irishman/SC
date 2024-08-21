package controller;

import view.menu.Navigator;

import java.util.Scanner;

public class MenuController {
    private Builder builder;
    private Navigator rootNavigator;
    private Navigator userNavigator;

    public MenuController() {
        this.builder = new Builder();
        this.builder.buildMenu();

        this.rootNavigator = new Navigator(builder.getRootMenu());
        this.userNavigator = new Navigator(builder.getUserMenu());
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Root Menu");
            System.out.println("2. User Menu");
            System.out.println("3. Exit Program");

            int choice = scanner.nextInt();
            boolean authenticated = false;

            switch (choice) {
                case 1:
                    authenticated = authenticate("root");
                    if (authenticated) {
                        run(rootNavigator);
                    } else {
                        System.out.println("Access denied.");
                    }
                    break;
                case 2:
                    authenticated = authenticate("user");
                    if (authenticated) {
                        run(userNavigator);
                    } else {
                        System.out.println("Access denied.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    return;  // Завершение программы
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private boolean authenticate(String menuType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter password for " + menuType + " menu:");
        String inputPassword = scanner.nextLine();
        String correctPassword = (menuType.equals("root")) ? "root123" : "user123";

        return inputPassword.equals(correctPassword);
    }

    private void run(Navigator navigator) {
        while (true) {
            navigator.printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            navigator.navigate(choice);

            // Проверка, если пользователь выбрал "Exit"
            if (navigator.isExitChosen()) {
                break;
            }
        }
    }
}

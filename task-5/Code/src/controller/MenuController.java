package controller;

import autoservice.manager.impl.ServiceManager;
import view.menu.Navigator;

import java.util.Scanner;

public class MenuController {
    private Builder builder;
    private Navigator navigator;


    public MenuController() {
        ServiceManager serviceManager = new ServiceManager();
        builder = new Builder(serviceManager);
        builder.buildMenu();
        navigator = new Navigator(builder.getRootMenu());
    }

    public void run() {
        while (true) {
            navigator.printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            navigator.navigate(choice - 1);
        }
    }
}
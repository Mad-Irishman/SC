package controller;

import view.menu.Navigator;

import java.util.Scanner;

public class MenuController {
    private Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
        builder.buildMenu();
        navigator = new Navigator(builder.getRootMenu());

        while (true) {
            navigator.printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            navigator.navigate(choice);
        }
    }
}
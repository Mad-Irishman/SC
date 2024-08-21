package controller;

import view.menu.Menu;
import view.menu.MenuItem;

public class Builder {
    private Menu rootMenu;
    private Menu userMenu;

    public void buildMenu() {
        // Построение корневого меню
        rootMenu = new Menu("Root Menu", new MenuItem[]{
                new MenuItem("Root Option 1", () -> System.out.println("Root Option 1 selected"), null),
                new MenuItem("Exit", () -> System.out.println("Exiting..."), null)
        });

        // Построение пользовательского меню
        userMenu = new Menu("User Menu", new MenuItem[]{
                new MenuItem("User Option 1", () -> System.out.println("User Option 1 selected"), null),
                new MenuItem("Exit", () -> System.out.println("Exiting..."), null)
        });
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getUserMenu() {
        return userMenu;
    }
}

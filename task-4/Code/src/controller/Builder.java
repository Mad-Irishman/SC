package controller;

import view.menu.Menu;
import view.menu.MenuItem;

public class Builder {
    private Menu rootMenu;

    public void buildMenu() {
        // Здесь можно построить меню, используя Menu и MenuItem.
        Menu subMenu = new Menu("Submenu", new MenuItem[] {
                new MenuItem("Subitem 1", () -> System.out.println("Subitem 1 selected"), null),
                new MenuItem("Back to Main", null, null)
        });

        rootMenu = new Menu("Main Menu", new MenuItem[] {
                new MenuItem("Go to Submenu", null, subMenu),
                new MenuItem("Exit", () -> System.out.println("Exiting..."), null)
        });
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}

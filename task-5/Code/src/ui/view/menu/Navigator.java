package ui.view.menu;

public class Navigator {
    private Menu currentMenu;
    private final Menu rootMenu;

    public Navigator(Menu rootMenu) {
        this.rootMenu = rootMenu;
        this.currentMenu = rootMenu;
    }


    public void setCurrentMenu(Menu currentMenu) {
        if (currentMenu == null) {
            System.out.println("Warning: Trying to set currentMenu to null. Reverting to rootMenu.");
            this.currentMenu = rootMenu;
        } else {
            this.currentMenu = currentMenu;
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void printMenu() {
        if (currentMenu == null) {
            System.out.println("Current menu is not set. Returning to root menu.");
            currentMenu = rootMenu;
        }
        System.out.println("Menu: " + currentMenu.getName());
        MenuItem[] items = currentMenu.getMenuItems();
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getTitle());
        }
    }

    public void navigate(int index) {
        if (currentMenu == null) {
            System.out.println("Error: Current menu is null. Returning to root menu.");
            currentMenu = rootMenu;
        }
        if (index >= 0 && index < currentMenu.getMenuItems().length) {
            currentMenu.getMenuItems()[index].doAction();
            Menu nextMenu = currentMenu.getMenuItems()[index].getNextMenu();
            if (nextMenu != null) {
                currentMenu = nextMenu;
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void goToRootMenu() {
        if (rootMenu != null) {
            this.currentMenu = rootMenu;
        } else {
            System.out.println("Root menu is not set.");
        }
    }
}

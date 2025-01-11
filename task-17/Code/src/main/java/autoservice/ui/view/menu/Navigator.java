package autoservice.ui.view.menu;

public class Navigator {
    private Menu currentMenu;
    private final Menu rootMenu;

    public Navigator(Menu rootMenu) {
        if (rootMenu == null) {
            throw new IllegalArgumentException("Root menu cannot be null.");
        }
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
        if (items == null || items.length == 0) {
            System.out.println("No menu items available.");
            return;
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getTitle());
        }
    }

    public void navigate(int index) {
        try {
            if (currentMenu == null) {
                System.out.println("Error: Current menu is null. Returning to root menu.");
                currentMenu = rootMenu;
            }

            MenuItem[] items = currentMenu.getMenuItems();
            if (index < 0 || index >= items.length) {
                System.out.println("Invalid choice. Please select a valid menu item.");
                return;
            }

            MenuItem selectedItem = items[index];
            Menu nextMenu = selectedItem.getNextMenu();
            selectedItem.doAction();
            if (nextMenu != null) {
                setCurrentMenu(nextMenu);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Menu index out of bounds. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during navigation: " + e.getMessage());
        }
    }

    public void goToRootMenu() {
        setCurrentMenu(rootMenu);
    }

    public int getMenuItemCount() {
        try {
            if (currentMenu == null) {
                System.out.println("Error: Current menu is null. Returning 0.");
                return 0;
            }
            MenuItem[] items = currentMenu.getMenuItems();
            if (items == null) {
                System.out.println("Error: Menu items are null.");
                return 0;
            }
            return items.length;
        } catch (Exception e) {
            System.out.println("Unexpected error while getting menu item count: " + e.getMessage());
            return 0;
        }
    }
}

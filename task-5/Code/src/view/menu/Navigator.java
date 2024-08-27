package view.menu;

public class Navigator {
    private Menu currentMenu;

    public Navigator(Menu rootMenu) {
        this.currentMenu = rootMenu;
    }

    public void printMenu() {
        System.out.println("Current Menu: " + currentMenu.getName());
        MenuItem[] items = currentMenu.getMenuItems();
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getTitle());
        }
    }

    public void navigate(int index) {
        MenuItem[] items = currentMenu.getMenuItems();
        if (index >= 0 && index < items.length) {
            MenuItem selectedItem = items[index];
            selectedItem.doAction();
            if (selectedItem.getNextMenu() != null) {
                currentMenu = selectedItem.getNextMenu();
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}

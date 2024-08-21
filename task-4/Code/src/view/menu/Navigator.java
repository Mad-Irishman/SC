package view.menu;

public class Navigator {
    private Menu currentMenu;
    private boolean exitChosen;

    public Navigator(Menu initialMenu) {
        this.currentMenu = initialMenu;
        this.exitChosen = false;
    }

    public void printMenu() {
        System.out.println(currentMenu.getName());
        for (int i = 0; i < currentMenu.getMenuItems().length; i++) {
            System.out.println(i + 1 + ". " + currentMenu.getMenuItems()[i].getTitle());
        }
    }

    public void navigate(int index) {
        if (index < 1 || index > currentMenu.getMenuItems().length) {
            System.out.println("Invalid choice");
            return;
        }
        MenuItem chosenItem = currentMenu.getMenuItems()[index - 1];
        chosenItem.doAction();
        if (chosenItem.getNextMenu() != null) {
            currentMenu = chosenItem.getNextMenu();
        } else if (chosenItem.getTitle().equalsIgnoreCase("Exit")) {
            exitChosen = true;
        }
    }

    public boolean isExitChosen() {
        return exitChosen;
    }
}

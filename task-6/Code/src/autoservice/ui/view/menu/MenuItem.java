package autoservice.ui.view.menu;

import autoservice.ui.actions.IAction;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public IAction getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        try {
            if (action != null) {
                System.out.println("Executing action for menu item: " + title);
                action.execute();
            } else {
                System.out.println("No action defined for menu item: " + title);
            }
        } catch (Exception e) {
            System.out.println("Error while executing action for menu item '" + title + "': " + e.getMessage());
        }
    }
}
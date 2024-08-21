package view.menu;

import autoservice.models.actions.IAction;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        if (action != null) {
            action.execute();
        }
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public String getTitle() {
        return title;
    }
}

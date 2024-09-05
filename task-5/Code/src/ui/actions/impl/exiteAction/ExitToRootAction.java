package ui.actions.impl.exiteAction;

import ui.actions.IAction;
import ui.view.menu.Navigator;

public class ExitToRootAction implements IAction {
    private final Navigator navigator;

    public ExitToRootAction(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        try {
            navigator.goToRootMenu();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}

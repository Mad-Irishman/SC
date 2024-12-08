package autoservice.ui.actions.impl.exiteAction;

import autoservice.ui.actions.IAction;
import autoservice.ui.view.menu.Navigator;

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

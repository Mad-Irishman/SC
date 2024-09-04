package ui.actions.impl;

import ui.actions.IAction;
import ui.view.menu.Navigator;

public class ExitToRootAction implements IAction {
    private final Navigator navigator;

    public ExitToRootAction(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void execute() {
        navigator.goToRootMenu();
    }
}

package ui.controller;

import autoservice.manager.impl.ServiceManager;
import ui.actions.impl.masterAction.AddMasterAction;
import ui.actions.impl.masterAction.AllMastersAction;
import ui.actions.impl.masterAction.RemoveMasterAction;
import ui.view.menu.Menu;
import ui.view.menu.MenuItem;
import ui.view.menu.Navigator;

public class Builder {
    private Menu rootMenu;
    private Navigator navigator;
    private final ServiceManager serviceManager;

    public Builder(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void buildMenu(Navigator navigator) {
        this.navigator = navigator;

        Menu masterMenu = new Menu("Master", new MenuItem[]{
                new MenuItem("Add master", new AddMasterAction(serviceManager), null),
                new MenuItem("All masters", new AllMastersAction(serviceManager), null),
                new MenuItem("Remove master", new RemoveMasterAction(serviceManager), null),
                new MenuItem("Exit to root menu", () -> navigator.goToRootMenu(), null)
        });

        Menu garageMenu = new Menu("Garage", new MenuItem[]{
                new MenuItem("", null, null)
        });

        Menu orderMenu = new Menu("Order", new MenuItem[]{
                new MenuItem("", null, null)
        });

        rootMenu = new Menu("Root menu", new MenuItem[]{
                new MenuItem("Master menu", null, masterMenu),
                new MenuItem("Garage menu", null, garageMenu),
                new MenuItem("Order menu", null, orderMenu)
        });

    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}

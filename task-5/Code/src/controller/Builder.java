package controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.actions.impl.ActionsWithMasters;
import view.menu.Menu;
import view.menu.MenuItem;

public class Builder {
    private Menu rootMenu;
    private ServiceManager serviceManager;

    public Builder(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void buildMenu() {
        MenuItem item1 = new MenuItem("Show List 1", new ActionsWithMasters(serviceManager), null);
        MenuItem item2 = new MenuItem("Show List 2", new ActionsWithMasters(serviceManager), null);

        rootMenu = new Menu("Main Menu", new MenuItem[] { item1, item2 });
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
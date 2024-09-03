package ui.controller;

import autoservice.manager.impl.ServiceManager;
import ui.actions.impl.garageAction.AddGaragePlaceAction;
import ui.actions.impl.garageAction.AllGaragePlacesAction;
import ui.actions.impl.garageAction.GetAvailableGaragePlaces;
import ui.actions.impl.garageAction.RemoveGaragePlaceAction;
import ui.actions.impl.masterAction.*;
import ui.actions.impl.orderAction.*;
import ui.view.menu.Menu;
import ui.view.menu.MenuItem;
import ui.view.menu.Navigator;

public class Builder {
    private Menu rootMenu;
    private final ServiceManager serviceManager;

    public Builder(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void buildMenu(Navigator navigator) {

        // Вылетает ошибка при переходе в startMenu(rootMenu)
        Menu masterMenu = new Menu("Master", new MenuItem[]{
                new MenuItem("Add master", new AddMasterAction(serviceManager), null),
                new MenuItem("All masters", new AllMastersAction(serviceManager), null),
                new MenuItem("Remove master", new RemoveMasterAction(serviceManager), null),
                new MenuItem("Get masters by order", new GetMastersByOrderAction(serviceManager), null),
                new MenuItem("Get sorted masters", new GetSortedMastersAction(serviceManager), null),
                new MenuItem("Exit to root menu", navigator::goToRootMenu, null),
        });

        Menu garageMenu = new Menu("Garage", new MenuItem[]{
                new MenuItem("Add garage place", new AddGaragePlaceAction(serviceManager), null),
                new MenuItem("All garage places", new AllGaragePlacesAction(serviceManager), null),
                new MenuItem("Remove garage place", new RemoveGaragePlaceAction(serviceManager), null),
                new MenuItem("Get available garage places", new GetAvailableGaragePlaces(serviceManager), null),
                new MenuItem("Exit to root menu", navigator::goToRootMenu, null)
        });

        Menu orderMenu = new Menu("Order", new MenuItem[]{
                new MenuItem("Create order", new CreateOrderAction(serviceManager), null),
                new MenuItem("All orders", new AllOrdersAction(serviceManager), null),
                new MenuItem("Remove order", new RemoveOrderAction(serviceManager), null),
                new MenuItem("Get order by status", new GetSortedOrdersAction(serviceManager), null),
                new MenuItem("Get current order", new GetCurentOrdersAction(serviceManager), null),
                new MenuItem("Exit to root menu", navigator::goToRootMenu, null)
        });

        rootMenu = new Menu("Root menu", new MenuItem[]{
                new MenuItem("Master menu", null, masterMenu),
                new MenuItem("Garage menu", null, garageMenu),
                new MenuItem("Order menu", null, orderMenu),
                new MenuItem("Exit", () -> System.exit(0), null)
        });

        navigator.setCurrentMenu(rootMenu);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}

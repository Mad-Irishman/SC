package ui.controller;

import autoservice.manager.impl.ServiceManager;
import ui.actions.impl.exiteAction.ExitToRootAction;
import ui.actions.impl.garageAction.AddGaragePlaceAction;
import ui.actions.impl.garageAction.AllGaragePlacesAction;
import ui.actions.impl.garageAction.GetAvailableGaragePlaces;
import ui.actions.impl.garageAction.RemoveGaragePlaceAction;
import ui.actions.impl.masterAction.*;
import ui.actions.impl.orderAction.*;
import ui.view.menu.Menu;
import ui.view.menu.MenuItem;

public class Builder {
    private final Menu rootMenu;
    private final ServiceManager serviceManager;

    public Builder(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.rootMenu = buildMenus();
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu buildMenus() {

        Menu masterMenu = new Menu("Master", new MenuItem[]{
                new MenuItem("Add master", new AddMasterAction(serviceManager), null),
                new MenuItem("All masters", new AllMastersAction(serviceManager), null),
                new MenuItem("Remove master", new RemoveMasterAction(serviceManager), null),
                new MenuItem("Get masters by order", new GetMastersByOrderAction(serviceManager), null),
                new MenuItem("Get sorted masters", new GetSortedMastersAction(serviceManager), null),
                new MenuItem("Export masters", new ExportMastersAction(serviceManager), null),
                new MenuItem("Import masters", new ImportMastersAction(serviceManager), null),
                new MenuItem("Exit to root menu", new ExitToRootAction(null), null)
        });

        Menu garageMenu = new Menu("Garage", new MenuItem[]{
                new MenuItem("Add garage place", new AddGaragePlaceAction(serviceManager), null),
                new MenuItem("All garage places", new AllGaragePlacesAction(serviceManager), null),
                new MenuItem("Remove garage place", new RemoveGaragePlaceAction(serviceManager), null),
                new MenuItem("Get available garage places", new GetAvailableGaragePlaces(serviceManager), null),
                new MenuItem("Export garage", null, null),
                new MenuItem("Import garage", null, null),
                new MenuItem("Exit to root menu", new ExitToRootAction(null), null)
        });

        Menu orderMenu = new Menu("Order", new MenuItem[]{
                new MenuItem("Create order", new CreateOrderAction(serviceManager), null),
                new MenuItem("All orders", new AllOrdersAction(serviceManager), null),
                new MenuItem("Remove order", new RemoveOrderAction(serviceManager), null),
                new MenuItem("Get order by status", new GetSortedOrdersAction(serviceManager), null),
                new MenuItem("Get current order", new GetCurentOrdersAction(serviceManager), null),
                new MenuItem("Export orders", null, null),
                new MenuItem("Import orders", null, null),
                new MenuItem("Exit to root menu", new ExitToRootAction(null), null)
        });
        
        Menu rootMenu = new Menu("Root menu", new MenuItem[]{
                new MenuItem("Master menu", null, masterMenu),
                new MenuItem("Garage menu", null, garageMenu),
                new MenuItem("Order menu", null, orderMenu),
                new MenuItem("Exit", () -> System.exit(0), null)
        });

        setExitToRootMenu(masterMenu, rootMenu);
        setExitToRootMenu(garageMenu, rootMenu);
        setExitToRootMenu(orderMenu, rootMenu);

        return rootMenu;
    }

    private void setExitToRootMenu(Menu menu, Menu rootMenu) {
        for (MenuItem item : menu.getMenuItems()) {
            if (item.getTitle().equals("Exit to root menu")) {
                item.setNextMenu(rootMenu);
                break;
            }
        }
    }
}

package autoservice.ui.actions.impl.orderAction;

import autoservice.utils.export.csv.OrderCSVExporter;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ExportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            OrderCSVExporter.exportOrdersToCSV(serviceManager.getOrders());
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}

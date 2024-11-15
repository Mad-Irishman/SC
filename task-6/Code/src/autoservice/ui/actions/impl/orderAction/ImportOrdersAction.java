package autoservice.ui.actions.impl.orderAction;

import autoservice.utils.imports.csv.OrderCSVImporter;
import autoservice.manager.impl.ServiceManager;

import autoservice.ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ImportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            OrderCSVImporter.importOrdersFromCSV(serviceManager);
            System.out.println("Import successful.");
        } catch (IOException e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }
}

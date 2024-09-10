package ui.actions.impl.orderAction;

import CSVUtils.MasterImportExport.MasterCSVImporter;
import CSVUtils.OrderImportExport.OrderCSVImporter;
import autoservice.manager.impl.ServiceManager;

import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ImportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to import: ");
        String filePath = scanner.nextLine();

        try {
            OrderCSVImporter.importOrdersFromCSV(serviceManager, filePath);
            System.out.println("Import successful.");
        } catch (IOException e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }
}

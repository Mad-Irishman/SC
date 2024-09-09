package ui.actions.impl.orderAction;

import CSVUtils.OrderImportExport.OrderCSVExporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ExportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to export: ");
        String filePath = scanner.nextLine();

        try {
            OrderCSVExporter.exportOrdersToCSV(serviceManager.getOrders(), filePath);
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}

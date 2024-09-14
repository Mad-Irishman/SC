package ui.actions.impl.garageAction;

import CSVUtils.GarageImportExport.GarageCSVExporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ExportGaragesAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportGaragesAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to export garages: ");
        String filePath = scanner.nextLine();

        try {
            GarageCSVExporter.exportGaragesToCSV(serviceManager.getGarages(),serviceManager.getMasters(), filePath);
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}
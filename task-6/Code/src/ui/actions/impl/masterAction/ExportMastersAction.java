package ui.actions.impl.masterAction;

import CSVUtils.MasterImportExport.MasterCSVExporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ExportMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to export: ");
        String filePath = scanner.nextLine();

        try {
            MasterCSVExporter.exportMastersToCSV(serviceManager.getMasters(), filePath);
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}

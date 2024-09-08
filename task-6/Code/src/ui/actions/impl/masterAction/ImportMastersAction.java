package ui.actions.impl.masterAction;

import CSVUtils.MasterImportExport.MasterCSVImporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ImportMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to import: ");
        String filePath = scanner.nextLine();

        try {
            MasterCSVImporter.importMastersFromCSV(serviceManager.getMasters(), filePath);
            System.out.println("Import successful.");
        } catch (IOException e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }
}

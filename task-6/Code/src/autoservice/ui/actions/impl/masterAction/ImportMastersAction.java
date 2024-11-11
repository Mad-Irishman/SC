package autoservice.ui.actions.impl.masterAction;

import autoservice.utils.imports.csv.MasterCSVImporter;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ImportMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            MasterCSVImporter.importMastersFromCSV(serviceManager);
            System.out.println("Import successful.");
        } catch (IOException e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }
}

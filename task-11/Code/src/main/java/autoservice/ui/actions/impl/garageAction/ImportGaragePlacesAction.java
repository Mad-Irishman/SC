package autoservice.ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;
import autoservice.utils.imports.csv.GarageCSVImporter;

import java.io.IOException;

public class ImportGaragePlacesAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportGaragePlacesAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            GarageCSVImporter.importGaragePlacesFromCSV(serviceManager);
            System.out.println("Import successful.");
        } catch (IOException e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }

}

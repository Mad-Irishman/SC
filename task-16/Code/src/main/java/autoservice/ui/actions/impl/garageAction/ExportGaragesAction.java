package autoservice.ui.actions.impl.garageAction;

import autoservice.utils.export.csv.GarageCSVExporter;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;

import java.io.IOException;

public class ExportGaragesAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportGaragesAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            GarageCSVExporter.exportGaragesToCSV(serviceManager.allGaragePlaces());
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}
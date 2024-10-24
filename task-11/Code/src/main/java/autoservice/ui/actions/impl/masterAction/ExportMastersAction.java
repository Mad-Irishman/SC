package autoservice.ui.actions.impl.masterAction;

import autoservice.utils.export.csv.MasterCSVExporter;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;

import java.io.IOException;

public class ExportMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            MasterCSVExporter.exportMastersToCSV(serviceManager.getMasters());
            System.out.println("Export successful.");
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}

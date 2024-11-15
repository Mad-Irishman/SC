package autoservice.ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.ui.actions.IAction;

import java.util.Comparator;
import java.util.List;

public class GetSortedMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public GetSortedMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            Comparator<Master> nameComparator = Comparator.comparing(Master::getName);

            List<Master> sortedMasters = serviceManager.getSortedMasters(serviceManager.getMasters(), List.of(nameComparator));

            if (sortedMasters.isEmpty()) {
                System.out.println("No masters found.");
            } else {
                System.out.println("Masters sorted by name:");
                for (Master master : sortedMasters) {
                    System.out.println(" - " + master.getName());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

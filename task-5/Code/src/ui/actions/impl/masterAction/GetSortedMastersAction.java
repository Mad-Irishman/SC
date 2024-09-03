package ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import ui.actions.IAction;

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
                System.out.println("Мастера не найдены.");
            } else {
                System.out.println("Отсортированные мастера по имени:");
                for (Master master : sortedMasters) {
                    System.out.println(" - " + master.getName());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
        }
    }
}

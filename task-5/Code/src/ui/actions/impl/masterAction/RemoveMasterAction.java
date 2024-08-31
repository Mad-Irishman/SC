package ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import ui.actions.IAction;

import java.util.List;
import java.util.Scanner;

public class RemoveMasterAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveMasterAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {

        System.out.println("Список мастеров:");
        List<Master> masters = serviceManager.getAllMasterInGarage();
        if (masters.isEmpty()) {
            System.out.println("Нет доступных мастеров для удаления.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String nameMaster = null;
        Master masterToRemove = null;

        while (nameMaster == null || nameMaster.trim().isEmpty()) {
            System.out.println("Введите имя мастера, которого вы хотите удалить: ");
            nameMaster = scanner.nextLine().trim();

            for (Master master : serviceManager.getAllMasterInGarage()) {
                if (master.getName().equalsIgnoreCase(nameMaster.trim())) {
                    masterToRemove = master;
                    break;
                }
            }

            if (masterToRemove != null) {
                if (masterToRemove.isAvailable() == MasterStatus.AVAILABLE) {
                    serviceManager.removeMaster(masterToRemove);
                    System.out.println("Мастер " + nameMaster + " успешно удален.");
                } else {
                    System.out.println("Невозможно удалить мастера, так как у него есть незавершенные заказы.");
                }
            } else {
                System.out.println("Мастер с именем " + nameMaster + " не найден.");
            }

        }
    }
}
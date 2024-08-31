package ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import ui.actions.IAction;

import java.util.Scanner;

public class RemoveMasterAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveMasterAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String nameMaster = null;

        while (nameMaster == null || nameMaster.trim().isEmpty()) {
            System.out.println("Введите имя мастера, которого вы хотите удалить: ");
            nameMaster = scanner.nextLine().trim();

            if (nameMaster.isEmpty()) {
                System.out.println("Имя не может быть пустым. Пожалуйста, попробуйте снова.");
            }
        }

        Master master = new Master(nameMaster);
        serviceManager.removeMaster(master);
        System.out.println("Матсер " + nameMaster + " успешно удален.");
    }
}
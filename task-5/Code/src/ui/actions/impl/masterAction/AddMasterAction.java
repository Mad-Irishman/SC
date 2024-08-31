package ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import ui.actions.IAction;

import java.util.Scanner;

public class AddMasterAction implements IAction {
    private final ServiceManager serviceManager;

    public AddMasterAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String nameMaster = null;

        //Реализовать постоянный запрос на ввод имени мастера
        while (nameMaster == null || nameMaster.trim().isEmpty()) {
            System.out.println("Введите имя мастера: ");
            nameMaster = scanner.nextLine().trim();

            if (nameMaster.isEmpty()) {
                System.out.println("Имя не может быть пустым. Пожалуйста, попробуйте снова.");
            }
        }

        Master master = new Master(nameMaster);
        serviceManager.addMaster(master);
        System.out.println("Мастер " + nameMaster + " успешно добавлен.");

    }
}
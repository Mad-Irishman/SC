package autoservice.ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.ui.actions.IAction;

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

        while (nameMaster == null || nameMaster.trim().isEmpty()) {
            System.out.println("Enter the master's name: ");
            nameMaster = scanner.nextLine().trim();

            if (nameMaster.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }

        try {
            Master master = new Master(nameMaster);
            serviceManager.addMaster(master);
            System.out.println("Master " + nameMaster + " has been successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating master: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the master: " + e.getMessage());
        }
    }
}

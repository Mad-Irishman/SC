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
        try {
            System.out.println("List of masters:");
            List<Master> masters = serviceManager.getAllMasterInGarage();

            if (masters.isEmpty()) {
                System.out.println("No available masters for removal.");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            String nameMaster = null;
            Master masterToRemove = null;

            while (nameMaster == null || nameMaster.trim().isEmpty()) {
                System.out.println("Enter the name of the master you want to remove: ");
                nameMaster = scanner.nextLine().trim();

                for (Master master : masters) {
                    if (master.getName().equalsIgnoreCase(nameMaster.trim())) {
                        masterToRemove = master;
                        break;
                    }
                }

                if (masterToRemove != null) {
                    if (masterToRemove.isAvailable() == MasterStatus.AVAILABLE) {
                        serviceManager.removeMaster(masterToRemove);
                        System.out.println("Master " + nameMaster + " has been successfully removed.");
                    } else {
                        System.out.println("Cannot remove the master as they have unfinished orders.");
                    }
                } else {
                    System.out.println("Master with the name " + nameMaster + " not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred while removing the master: " + e.getMessage());
        }
    }
}

package autoservice.ui.actions.impl.masterAction;

import autoservice.dto.masterDTO.differentDTO.MasterDTOForGet;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class AllMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public AllMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        List<MasterDTOForGet> allMasters = serviceManager.getMasters();
        try {
            if (serviceManager.getAllMasterInGarage().isEmpty()) {
                System.out.println("No masters available in the garage.");
            } else {
                for (int i = 0; i < allMasters.size(); i++) {
                    System.out.println((i + 1) + ". " + allMasters.get(i).getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving the list of masters: " + e.getMessage());
        }
    }
}

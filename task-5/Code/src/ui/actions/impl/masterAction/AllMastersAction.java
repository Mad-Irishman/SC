package ui.actions.impl.masterAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;


public class AllMastersAction implements IAction {
    private final ServiceManager serviceManager;

    public AllMastersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        List<Master> allMasters = new ArrayList<>(serviceManager.getAllMasterInGarage());
        for (int i = 0; i < allMasters.size(); i++) {
            System.out.println((i + 1) + ". " + allMasters.get(i).getName());
        }
    }
}
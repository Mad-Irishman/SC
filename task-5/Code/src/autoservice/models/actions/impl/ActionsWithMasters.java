package autoservice.models.actions.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.actions.IAction;


import java.util.List;

public class ActionsWithMasters implements IAction {
    private ServiceManager serviceManager;

    public ActionsWithMasters(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }


    @Override
    public void execute() {

    }

}
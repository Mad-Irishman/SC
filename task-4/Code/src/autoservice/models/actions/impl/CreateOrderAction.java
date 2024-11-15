package autoservice.models.actions.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.actions.IAction;

public class CreateOrderAction implements IAction {
    private ServiceManager serviceManager;

    public CreateOrderAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        // Логика создания заказа (сбор ввода, вызов serviceManager.createOrder() и т.д.)
    }
}

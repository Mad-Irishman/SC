package autoservice.models.master;

import autoservice.models.master.exception.MasterException;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;

public class Master {
    private String name;
    private MasterStatus isAvailable;
    private Order ordersMaster;

    public Master(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new MasterException("Master's name cannot be empty");
        }
        this.name = name;
        this.isAvailable = MasterStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MasterException {
        if (name == null || name.trim().isEmpty()) {
            throw new MasterException("Master's name cannot be empty");
        }
        this.name = name;
    }

    public MasterStatus isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(MasterStatus available) throws MasterException {
        if (available == null) {
            throw new MasterException("Master's status cannot be null");
        }
        this.isAvailable = available;
    }

    public Order getOrderMaster() {
        return this.ordersMaster;
    }

    public void setOrderMaster(Order orderMaster) {
        this.ordersMaster = orderMaster;
    }

    public void assignOrderMaster(Order order) throws MasterException {
        if (order == null) {
            throw new MasterException("Order cannot be null");
        }
        this.ordersMaster = order;
    }

    public Order getOrdersMaster() {
        return this.ordersMaster;
    }
}

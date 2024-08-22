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
            throw new MasterException("Имя мастера не может быть пустым");
        }
        this.name = name;
        this.isAvailable = MasterStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new MasterException("Имя мастера не может быть пустым");
        }
        this.name = name;
    }

    public MasterStatus isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(MasterStatus available) {
        if (available == null) {
            throw new MasterException("Статус мастера не может быть null");
        }
        this.isAvailable = available;
    }

    public Order getOrderMaster() {
        return this.ordersMaster;
    }

    public void setOrderMaster(Order orderMaster) {
        this.ordersMaster = orderMaster;
    }

    public void assignOrderMaster(Order order) {
        if (order == null) {
            throw new MasterException("Заказ не может быть null");
        }
        this.ordersMaster = order;
    }

    public Order getOrdersMaster() {
        return this.ordersMaster;
    }
}

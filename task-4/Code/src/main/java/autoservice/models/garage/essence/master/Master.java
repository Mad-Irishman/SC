package main.java.autoservice.models.garage.essence.master;

import main.java.autoservice.models.garage.essence.master.masterStatus.MasterStatus;
import main.java.autoservice.models.order.Order;

import java.util.List;

public class Master {
    private String name;
    private MasterStatus isAvailable;
    private Order ordersMaster;


    public Master(String name) {
        this.name = name;
        this.isAvailable = MasterStatus.AVAILABLE;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MasterStatus isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(MasterStatus available) {
        this.isAvailable = available;
    }

    public Order getOrderMaster() {
        return this.ordersMaster;
    }

    public void setOrderMaster(Order orderMaster) {
        this.ordersMaster = orderMaster;
    }

    public void assingOrderMaster(Order order) {
        this.ordersMaster = order;
    }

    public Order getOrdersMaster() {
        return this.ordersMaster;
    }

}

package main.java.autoservice.models;

import main.java.autoservice.models.order.Order;

public class Master {
    private String name;
    private boolean isAvailable;
    private Order ordersMaster;

    public Master(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
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

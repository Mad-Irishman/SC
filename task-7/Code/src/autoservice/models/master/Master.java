package autoservice.models.master;

import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import java.util.UUID;

public class Master {
    private final String id;
    private String name;
    private MasterStatus isAvailable;
    private Order ordersMaster;

    public Master(String name) {
        this.id = generateUniqueId();
        this.name = name;
        this.isAvailable = MasterStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public MasterStatus isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(MasterStatus available){
        this.isAvailable = available;
    }

    public Order getOrderMaster() {
        return this.ordersMaster;
    }

    public void setOrderMaster(Order orderMaster) {
        this.ordersMaster = orderMaster;
    }

    public void assignOrderMaster(Order order) {
        this.ordersMaster = order;
    }

    public Order getOrdersMaster() {
        return this.ordersMaster;
    }

    public String getId() {
        return id;
    }

    public String getIdOrder() {
        return ordersMaster.getIdOrder();
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}

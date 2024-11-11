package autoservice.models.order;

import autoservice.models.GaragePlace;
import autoservice.models.Master;
import autoservice.models.order.status.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private static int idOrder;
    private String discription;
    private Master assignedMaster;
    private GaragePlace assignedGaragePlace;
    private OrderStatus statusOrder;
    private LocalDateTime startTime;
    private LocalDateTime endTime;



    public Order(String discription, Master assignedMaster, GaragePlace assignedGaragePlace, LocalDateTime startTime, int durationInHours) {
        this.idOrder++;
        this.discription = discription;
        this.statusOrder = OrderStatus.CREATED;
        this.assignedMaster = assignedMaster;
        this.assignedGaragePlace = assignedGaragePlace;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(durationInHours);

    }

    public int getIdOrder() {
        return this.idOrder;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public OrderStatus getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(OrderStatus statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Master getAssignedMaster() {
        return this.assignedMaster;
    }

    public void setAssignedMaster(Master assignedMaster) {
        this.assignedMaster = assignedMaster;
    }

    public GaragePlace getAssignedGaragePlace() {
        return this.assignedGaragePlace;
    }

    public void setAssignedGaragePlace(GaragePlace assignedGaragePlace) {
        this.assignedGaragePlace = assignedGaragePlace;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
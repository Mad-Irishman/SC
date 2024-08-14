package autoservice.models.order;

import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private static int idOrder;
    private String discription;
    private Master assignedMaster;
    private GaragePlace assignedGaragePlace;
    private OrderStatus statusOrder;
    private LocalDateTime submissionDate;
    private LocalDateTime completionDate;
    private LocalDateTime plannedStartDate;
    private double price;


    public Order(String discription, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        idOrder++;
        this.discription = discription;
        this.statusOrder = OrderStatus.CREATED;
        this.submissionDate = submissionDate;
        this.completionDate = completionDate;
        this.plannedStartDate = plannedStartDate;
        this.price = price;

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

    public LocalDateTime getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(LocalDateTime plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
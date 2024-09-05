package autoservice.models.order;

import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.exception.OrderException;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private static int idOrder;
    private String description;
    private Master assignedMaster;
    private GaragePlace assignedGaragePlace;
    private OrderStatus statusOrder;
    private LocalDateTime submissionDate;
    private LocalDateTime completionDate;
    private LocalDateTime plannedStartDate;
    private double price;

    public Order(String description, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        idOrder++;
        this.description = description;
        this.statusOrder = OrderStatus.CREATED;
        this.submissionDate = submissionDate;
        this.completionDate = completionDate;
        this.plannedStartDate = plannedStartDate;
        this.price = price;
    }

    public int getIdOrder() {
        return this.idOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws OrderException {
        if (description == null || description.trim().isEmpty()) {
            throw new OrderException("Order description cannot be empty");
        }
        this.description = description;
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

    public void setSubmissionDate(LocalDateTime submissionDate) throws OrderException {
        if (submissionDate == null) {
            throw new OrderException("Submission date cannot be null");
        }
        if (submissionDate.isAfter(this.completionDate)) {
            throw new OrderException("Submission date cannot be after completion date");
        }
        this.submissionDate = submissionDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) throws OrderException {
        if (completionDate == null) {
            throw new OrderException("Completion date cannot be null");
        }
        if (this.submissionDate != null && completionDate.isBefore(this.submissionDate)) {
            throw new OrderException("Completion date cannot be before submission date");
        }
        this.completionDate = completionDate;
    }

    public LocalDateTime getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(LocalDateTime plannedStartDate) throws OrderException {
        if (plannedStartDate == null) {
            throw new OrderException("Planned start date cannot be null");
        }
        if (plannedStartDate.isAfter(this.completionDate)) {
            throw new OrderException("Planned start date cannot be after completion date");
        }
        this.plannedStartDate = plannedStartDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws OrderException {
        if (price < 0) {
            throw new OrderException("Order price cannot be negative");
        }
        this.price = price;
    }

    public boolean contains(Master master) throws OrderException {
        if (master == null) {
            throw new OrderException("Master cannot be null");
        }
        return this.assignedMaster != null && this.assignedMaster.equals(master);
    }
}

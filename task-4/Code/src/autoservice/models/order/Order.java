package autoservice.models.order;

import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.exception.OrderException;
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

    public String getDescription() {
        return discription;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new OrderException("Описание заказа не может быть пустым");
        }
        this.discription = description;
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
        if (submissionDate == null) {
            throw new OrderException("Дата подачи не может быть null");
        }
        if (submissionDate.isAfter(this.completionDate)) {
            throw new OrderException("Дата подачи не может быть после даты завершения");
        }
        this.submissionDate = submissionDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        if (completionDate == null) {
            throw new OrderException("Дата завершения не может быть null");
        }
        if (this.submissionDate != null && completionDate.isBefore(this.submissionDate)) {
            throw new OrderException("Дата завершения не может быть до даты подачи");
        }
        this.completionDate = completionDate;
    }

    public LocalDateTime getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(LocalDateTime plannedStartDate) {
        if (plannedStartDate == null) {
            throw new OrderException("Планируемая дата начала не может быть null");
        }
        if (plannedStartDate.isAfter(this.completionDate)) {
            throw new OrderException("Планируемая дата начала не может быть после даты завершения");
        }
        this.plannedStartDate = plannedStartDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new OrderException("Цена заказа не может быть отрицательной");
        }
        this.price = price;
    }

    public boolean contains(Master master) {
        if (master == null) {
            throw new OrderException("Мастер не может быть null");
        }
        return this.assignedMaster != null && this.assignedMaster.equals(master);
    }
}

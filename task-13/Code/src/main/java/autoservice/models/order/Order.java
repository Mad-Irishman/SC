package autoservice.models.order;

import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.orderStatus.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id_order", unique = true, nullable = false)
    private final String idOrder;
    @Column(name = "description", nullable = false)
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_master", referencedColumnName = "id", nullable = false)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Master assignedMaster;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_garage_place", referencedColumnName = "place_number", nullable = false)
    private GaragePlace assignedGaragePlace;
    @Column(name = "status_order", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus statusOrder;
    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;
    @Column(name = "completion_date", nullable = false)
    private LocalDateTime completionDate;
    @Column(name = "planned_start_date", nullable = false)
    private LocalDateTime plannedStartDate;
    @Column(name = "price", nullable = false)
    private double price;

    public Order() {
        this.idOrder = generationId();
        this.statusOrder = OrderStatus.CREATED;
    }

    public Order(String description, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        this.idOrder = generationId();
        this.description = description;
        this.statusOrder = OrderStatus.CREATED;
        this.submissionDate = submissionDate;
        this.completionDate = completionDate;
        this.plannedStartDate = plannedStartDate;
        this.price = price;
    }

    public Order(String idOrder, String description, Master assignedMaster, GaragePlace assignedGaragePlace, OrderStatus statusOrder, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        this.idOrder = idOrder;
        this.description = description;
        this.assignedMaster = assignedMaster;
        this.assignedGaragePlace = assignedGaragePlace;
        this.statusOrder = statusOrder;
        this.submissionDate = submissionDate;
        this.completionDate = completionDate;
        this.plannedStartDate = plannedStartDate;
        this.price = price;
    }

    public Order(String id, String description, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        this.idOrder = id;
        this.description = description;
        this.submissionDate = submissionDate;
        this.completionDate = completionDate;
        this.plannedStartDate = plannedStartDate;
        this.price = price;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    @JsonSetter(nulls = Nulls.SKIP)
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

    public boolean contains(Master master) {
        return this.assignedMaster != null && this.assignedMaster.equals(master);
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', description='%s', status='%s', master='%s', garagePlace='%s', submissionDate='%s', completionDate='%s', plannedStartDate='%s', price=%.2f}",
                idOrder,
                description,
                statusOrder,
                assignedMaster != null ? assignedMaster.getName() : "Not assigned",
                assignedGaragePlace != null ? assignedGaragePlace.getPlaceNumber() : "Not assigned",
                submissionDate != null ? submissionDate.toString() : "N/A",
                completionDate != null ? completionDate.toString() : "N/A",
                plannedStartDate != null ? plannedStartDate.toString() : "N/A",
                price);
    }


    private String generationId() {
        return UUID.randomUUID().toString();
    }
}

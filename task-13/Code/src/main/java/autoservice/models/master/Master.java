package autoservice.models.master;

import autoservice.models.master.masterStatus.MasterStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "masters")
public class Master {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private final String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "is_available",  columnDefinition = "master_status default 'AVAILABLE'")
    private MasterStatus isAvailable;
//    private Order ordersMaster;

    public Master() {
        this.id = generateUniqueId();
        this.isAvailable = MasterStatus.AVAILABLE;
    }

    public Master(String name) {
        this.id = generateUniqueId();
        this.name = name;
        this.isAvailable = MasterStatus.AVAILABLE;
    }

    public Master(String id, String name, MasterStatus isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MasterStatus isAvailable() {
        return isAvailable;
    }

    public void setAvailable(MasterStatus available) {
        this.isAvailable = available;
    }

//    public Order getOrderMaster() {
//        return this.ordersMaster;
//    }

//    public void setOrderMaster(Order orderMaster) {
//        this.ordersMaster = orderMaster;
//    }
//
//    public void assignOrderMaster(Order order) {
//        this.ordersMaster = order;
//    }

//    public Order getOrdersMaster() {
//        return this.ordersMaster;
//    }

    public String getId() {
        return id;
    }


    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}

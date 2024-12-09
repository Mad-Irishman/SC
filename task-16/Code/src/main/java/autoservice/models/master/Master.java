package autoservice.models.master;

import autoservice.models.master.masterStatus.MasterStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "masters")
public class Master {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private final String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "is_available", nullable = false)
    @Enumerated(EnumType.STRING)
    private MasterStatus available;

    public Master() {
        this.id = generateUniqueId();
        this.available = MasterStatus.AVAILABLE;
    }

    public Master(String name) {
        this.id = generateUniqueId();
        this.name = name;
        this.available = MasterStatus.AVAILABLE;
    }

    public Master(String id, String name, MasterStatus available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

}

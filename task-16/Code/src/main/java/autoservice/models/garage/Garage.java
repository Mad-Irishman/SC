package autoservice.models.garage;

import autoservice.config.properties.ConfigProperty;
import autoservice.models.garage.garageStatus.GarageStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class Garage {
    @Value("${garage.id}")
    private String id;

    private GarageStatus isAvailable;

    @ConfigProperty(propertyName = "canRemoveGaragePlace", type = boolean.class)
    private boolean canRemoveGaragePlace;

    @ConfigProperty(propertyName = "canAddGaragePlace", type = boolean.class)
    private boolean canAddGaragePlace;

    @ConfigProperty(propertyName = "canRemoveOrder", type = boolean.class)
    private boolean canRemoveOrder;

    @ConfigProperty(propertyName = "canRescheduleOrder", type = boolean.class)
    private boolean canRescheduleOrder;

    public Garage() {
        this.id = generateUniqueId();
        this.isAvailable = GarageStatus.AVAILABLE;
    }

    //Все остальные getters/setters

    public boolean getCanRescheduleOrder() {
        return canRescheduleOrder;
    }


    public boolean getCanRemoveOrder() {
        return canRemoveOrder;
    }

    public boolean getCanRemoveGaragePlace() {
        return canRemoveGaragePlace;
    }


    public boolean getCanAddGaragePlace() {
        return canAddGaragePlace;
    }


    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
package autoservice.models.garage;

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

    @Value("${garage.canRemoveGaragePlace}")
    private boolean canRemoveGaragePlace;

    @Value("${garage.canAddGaragePlace}")
    private boolean canAddGaragePlace;

    @Value("${garage.canRemoveOrder}")
    private boolean canRemoveOrder;

    @Value("${garage.canRescheduleOrder}")
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
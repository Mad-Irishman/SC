package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;

import java.util.List;

public class GetAvailableGaragePlaces implements IAction {
    private final ServiceManager serviceManager;

    public GetAvailableGaragePlaces(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            List<Garage> garages = serviceManager.getGarages();
            List<GaragePlace> availableGaragePlaces = serviceManager.getAvailableGaragePlaces(garages);

            if (availableGaragePlaces.isEmpty()) {
                System.out.println("Доступные гаражные места не найдены.");
            } else {
                System.out.println("Доступные гаражные места:");
                for (GaragePlace place : availableGaragePlaces) {
                    System.out.println(place.getPlaceNumber());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении доступных гаражных мест: " + e.getMessage());
        }
    }
}
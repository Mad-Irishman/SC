package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class AllGaragePlacesAction implements IAction {
    private final ServiceManager serviceManager;

    public AllGaragePlacesAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        List<GaragePlace> allGaragePlaces = new ArrayList<>(serviceManager.allGaragePlaces());
        for (int i = 0; i < allGaragePlaces.size(); i++) {
            System.out.println("Номер места: "+ allGaragePlaces.get(i).getPlaceNumber() + " " +
                    "Статус: " + allGaragePlaces.get(i).isOccupied());

        }
    }
}

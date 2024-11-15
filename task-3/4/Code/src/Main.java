import autoservice.manager.impl.ServiceManager;
import autoservice.models.GaragePlace;
import autoservice.models.Master;


import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ServiceManager manager = new ServiceManager();

        Master master1 = new Master("John Doe");
        Master master2 = new Master("Jane Smith");
        GaragePlace place1 = new GaragePlace(1);
        GaragePlace place2 = new GaragePlace(2);

        manager.addMaster(master1);
        manager.addMaster(master2);
        manager.addGaragePlace(place1);
        manager.addGaragePlace(place2);

        LocalDateTime startTime1 = LocalDateTime.now().plusHours(1);
        LocalDateTime startTime2 = LocalDateTime.now().plusHours(2);

        manager.createOrder("Change oil", master1, place1, startTime1, 2);
        manager.createOrder("Fix brakes", master2, place2, startTime2, 3);

        manager.showAllOrders();
        manager.showAvailableMasters();
        manager.showAvailableGaragePlaces();

        manager.removeMaster(master1);

        manager.removeGaragePlace(place1);

        manager.completeOrder(manager.getOrderById(1));

        manager.cancelOrder(manager.getOrderById(2));

        manager.adjustOrdersForDelay(1, 1);

        manager.showAllOrders();
        manager.showAvailableMasters();
        manager.showAvailableGaragePlaces();
    }
}









package autoservice.service;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.repository.impl.GaragePlaceRepositoryImpl;
import autoservice.repository.impl.MasterRepositoryImpl;
import autoservice.repository.impl.OrderRepositoryImpl;

import java.util.List;

public interface GarageServiceInterface {
    Garage getGarage();

    MasterRepositoryImpl getMasterDAO();

    void addMaster(Master master);

    List<Master> allMasters();

    void removeMaster(Master master);

    List<Master> getAvailableMasters();

    Master getMasterById(String id);

    void updateMaster(Master master);

    GaragePlaceRepositoryImpl getGaragePlaceDAO();

    void addGaragePlace(GaragePlace place);

    List<GaragePlace> allGaragePlaces();

    void removeGaragePlace(GaragePlace place);

    List<GaragePlace> getAvailableGaragePlaces();

    void updateGaragePlace(GaragePlace garagePlace);

    void createOrder(Order order);

    List<Order> allOrders();

    boolean removeOrder(Order order);

    OrderRepositoryImpl getOrderDAO();

}

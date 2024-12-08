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

    String addMaster(Master master);

    List<Master> allMasters();

    String removeMaster(Master master);

    List<Master> getAvailableMasters();

    Master getMasterById(String id);

    void updateMaster(Master master);

    GaragePlaceRepositoryImpl getGaragePlaceDAO();

    Integer addGaragePlace(GaragePlace place);

    List<GaragePlace> allGaragePlaces();

    Integer removeGaragePlace(GaragePlace place);

    List<GaragePlace> getAvailableGaragePlaces();

    void updateGaragePlace(GaragePlace garagePlace);

    String createOrder(Order order);

    List<Order> allOrders();

    String removeOrder(Order order);

    OrderRepositoryImpl getOrderDAO();

}

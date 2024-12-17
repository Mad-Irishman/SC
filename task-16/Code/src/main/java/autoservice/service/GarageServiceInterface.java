package autoservice.service;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.repository.GaragePlaceRepository;
import autoservice.repository.MasterRepository;
import autoservice.repository.OrderRepository;
import autoservice.repository.impl.GaragePlaceRepositoryImpl;
import autoservice.repository.impl.MasterRepositoryImpl;
import autoservice.repository.impl.OrderRepositoryImpl;

import java.util.List;

public interface GarageServiceInterface {
    Garage getGarage();

    MasterRepository getMasterRepository();

    String addMaster(Master master);

    List<Master> allMasters();

    String removeMaster(Master master);

    List<Master> getAvailableMasters();

    Master getMasterById(String id);

    void updateMaster(Master master);

    GaragePlaceRepository getGaragePlaceRepository();

    Integer addGaragePlace(GaragePlace place);

    List<GaragePlace> allGaragePlaces();

    Integer removeGaragePlace(GaragePlace place);

    List<GaragePlace> getAvailableGaragePlaces();

    void updateGaragePlace(GaragePlace garagePlace);

    GaragePlace getGaragePlaceByNumber(int id);

    OrderRepository getOrderRepository();

    String createOrder(Order order);

    List<Order> allOrders();

    String removeOrder(Order order);

}

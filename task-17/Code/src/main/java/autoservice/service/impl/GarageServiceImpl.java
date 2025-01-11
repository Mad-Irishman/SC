package autoservice.service.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import autoservice.repository.GaragePlaceRepository;
import autoservice.repository.MasterRepository;
import autoservice.repository.OrderRepository;
import autoservice.service.GarageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);
    private final Garage garage;
    private final GaragePlaceRepository garagePlaceRepository;
    private final MasterRepository masterRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public GarageServiceImpl(Garage garage, GaragePlaceRepository garagePlaceRepository, MasterRepository masterRepository, OrderRepository orderRepository) {
        this.garage = garage;
        this.garagePlaceRepository = garagePlaceRepository;
        this.masterRepository = masterRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Garage getGarage() {
        return garage;
    }

    @Override
    //Работа с masters
    public MasterRepository getMasterRepository() {
        return masterRepository;
    }

    @Override
    public String addMaster(Master master) {
        return masterRepository.addMaster(master);
    }

    @Override
    public List<Master> allMasters() {
        return masterRepository.allMasters();
    }

    @Override
    public String removeMaster(Master master) {
        return masterRepository.removeMasterByName(master);
    }

    @Override
    public List<Master> getAvailableMasters() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master : masterRepository.allMasters()) {
            if (master.getAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
    }

    @Override
    public Master getMasterById(String id) {
        return masterRepository.getMasterById(id);
    }

    @Override
    public void updateMaster(Master master) {
        masterRepository.updateMaster(master);
    }

    @Override
    //Работа с garagePlaces
    public GaragePlaceRepository getGaragePlaceRepository() {
        return garagePlaceRepository;
    }

    @Override
    public Integer addGaragePlace(GaragePlace place) {
        logger.info("Validating and adding garage place...");
        return garagePlaceRepository.addGaragePlace(place);
    }

    @Override
    public List<GaragePlace> allGaragePlaces() {
        return garagePlaceRepository.getAllGaragePlaces();
    }

    @Override
    public Integer removeGaragePlace(GaragePlace place) {
        return garagePlaceRepository.removeGaragePlace(place);
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        List<GaragePlace> availablePlaces = new ArrayList<>();
        for (GaragePlace place : getGaragePlaceRepository().getAllGaragePlaces()) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }

    @Override
    public void updateGaragePlace(GaragePlace garagePlace) {
        garagePlaceRepository.updateGaragePlace(garagePlace);
    }

    @Override
    public GaragePlace getGaragePlaceByNumber(int id) {
        return garagePlaceRepository.getGaragePlaceByNumber(id);
    }

    //    Работа с orders
    @Override
    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    @Override
    public String createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public List<Order> allOrders() {
        return orderRepository.allOrders();
    }

    @Override
    public String removeOrder(Order order) {
        return orderRepository.deleteOrder(order);
    }

}

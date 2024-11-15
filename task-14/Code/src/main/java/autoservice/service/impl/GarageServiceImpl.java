package autoservice.service.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import autoservice.repository.impl.GaragePlaceRepositoryImpl;
import autoservice.repository.impl.MasterRepositoryImpl;
import autoservice.repository.impl.OrderRepositoryImpl;
import autoservice.service.GarageServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageServiceInterface {
    private final Garage garage;
    private final GaragePlaceRepositoryImpl garagePlaceDAO;
    private final MasterRepositoryImpl masterDAO;
    private final OrderRepositoryImpl orderDAO;

    public GarageServiceImpl() {
        this.garage = new Garage();
        this.garagePlaceDAO = new GaragePlaceRepositoryImpl();
        this.masterDAO = new MasterRepositoryImpl();
        this.orderDAO = new OrderRepositoryImpl();
    }

    public Garage getGarage() {
        return garage;
    }

    //Работа с masters
    public MasterRepositoryImpl getMasterDAO() {
        return masterDAO;
    }

    public void addMaster(Master master) {
        masterDAO.addMaster(master);
    }

    public List<Master> allMasters() {
        return masterDAO.allMasters();
    }

    public void removeMaster(Master master) {
        masterDAO.deleteMasterByName(master);
    }

    public List<Master> getAvailableMasters() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master : masterDAO.allMasters()) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
    }

    public Master getMasterById(String id) {
        return masterDAO.getMasterById(id);
    }

    public void updateMaster(Master master) {
        masterDAO.updateMaster(master);
    }

    //Работа с garagePlaces
    public GaragePlaceRepositoryImpl getGaragePlaceDAO() {
        return garagePlaceDAO;
    }

    public void addGaragePlace(GaragePlace place) {
        garagePlaceDAO.addGaragePlace(place);
    }

    public List<GaragePlace> allGaragePlaces() {
        return garagePlaceDAO.getAllGaragePlaces();
    }

    public void removeGaragePlace(GaragePlace place) {
        garagePlaceDAO.removeGaragePlace(place);
    }

    public List<GaragePlace> getAvailableGaragePlaces() {
        List<GaragePlace> availablePlaces = new ArrayList<>();
        for (GaragePlace place : getGaragePlaceDAO().getAllGaragePlaces()) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }

    public void updateGaragePlace(GaragePlace garagePlace) {
        garagePlaceDAO.updateGaragePlace(garagePlace);
    }

    //    Работа с orders
    public void createOrder(Order order) {
        orderDAO.createOrder(order);
    }

    public List<Order> allOrders() {
        return orderDAO.allOrders();
    }

    public boolean removeOrder(Order order) {
        return orderDAO.deleteOrder(order);
    }

    public OrderRepositoryImpl getOrderDAO() {
        return orderDAO;
    }

}

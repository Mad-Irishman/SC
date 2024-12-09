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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);
    private final Garage garage;
    private final GaragePlaceRepositoryImpl garagePlaceDAO;
    private final MasterRepositoryImpl masterDAO;
    private final OrderRepositoryImpl orderDAO;

    @Autowired
    public GarageServiceImpl(Garage garage, GaragePlaceRepositoryImpl garagePlaceDAO, MasterRepositoryImpl masterDAO, OrderRepositoryImpl orderDAO) {
        this.garage = garage;
        this.garagePlaceDAO = garagePlaceDAO;
        this.masterDAO = masterDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public Garage getGarage() {
        return garage;
    }

    @Override
    //Работа с masters
    public MasterRepositoryImpl getMasterDAO() {
        return masterDAO;
    }

    @Override
    public String addMaster(Master master) {
        return masterDAO.addMaster(master);
    }

    @Override
    public List<Master> allMasters() {
        return masterDAO.allMasters();
    }

    @Override
    public String removeMaster(Master master) {
        return masterDAO.removeMasterByName(master);
    }

    @Override
    public List<Master> getAvailableMasters() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master : masterDAO.allMasters()) {
            if (master.getAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
    }

    @Override
    public Master getMasterById(String id) {
        return masterDAO.getMasterById(id);
    }

    @Override
    public void updateMaster(Master master) {
        masterDAO.updateMaster(master);
    }

    @Override
    //Работа с garagePlaces
    public GaragePlaceRepositoryImpl getGaragePlaceDAO() {
        return garagePlaceDAO;
    }

    @Override
    public Integer addGaragePlace(GaragePlace place) {
        logger.info("Validating and adding garage place...");
        return garagePlaceDAO.addGaragePlace(place);
    }

    @Override
    public List<GaragePlace> allGaragePlaces() {
        return garagePlaceDAO.getAllGaragePlaces();
    }

    @Override
    public Integer removeGaragePlace(GaragePlace place) {
        return garagePlaceDAO.removeGaragePlace(place);
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        List<GaragePlace> availablePlaces = new ArrayList<>();
        for (GaragePlace place : getGaragePlaceDAO().getAllGaragePlaces()) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }

    @Override
    public void updateGaragePlace(GaragePlace garagePlace) {
        garagePlaceDAO.updateGaragePlace(garagePlace);
    }

    public GaragePlace getGaragePlaceByNumber(int id) {
        return garagePlaceDAO.getGaragePlaceByNumber(id);
    }

    @Override
    //    Работа с orders
    public String createOrder(Order order) {
        return orderDAO.createOrder(order);
    }

    @Override
    public List<Order> allOrders() {
        return orderDAO.allOrders();
    }

    @Override
    public String removeOrder(Order order) {
        return orderDAO.deleteOrder(order);
    }

    @Override
    public OrderRepositoryImpl getOrderDAO() {
        return orderDAO;
    }

}

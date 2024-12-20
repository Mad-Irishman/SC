package autoservice.models.garage;

import autoservice.config.properties.ConfigProperty;
import autoservice.repository.impl.GaragePlaceRepositoryImpl;
import autoservice.repository.impl.MasterRepositoryImpl;
import autoservice.repository.impl.OrderRepositoryImpl;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.garage.garageStatus.GarageStatus;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

public class Garage {
    private final String id;
    @JsonIgnore
    private final GaragePlaceRepositoryImpl garagePlaceDAO;
    @JsonIgnore
    private final MasterRepositoryImpl masterDAO;
    @JsonIgnore
    private final OrderRepositoryImpl orderDAO;

    private GarageStatus isAvailable;

    @ConfigProperty(propertyName = "canRemoveGaragePlace", type = boolean.class)
    private boolean canRemoveGaragePlace;

    @ConfigProperty(propertyName = "canAddGaragePlace", type = boolean.class)
    private boolean canAddGaragePlace;

    @ConfigProperty(propertyName = "canRemoveOrder", type = boolean.class)
    private boolean canRemoveOrder;

    @ConfigProperty(propertyName = "canRescheduleOrder", type = boolean.class)
    private boolean canRescheduleOrder;

    public Garage() {
        this.id = generateUniqueId();
        this.isAvailable = GarageStatus.AVAILABLE;
        this.masterDAO = new MasterRepositoryImpl();
        this.garagePlaceDAO = new GaragePlaceRepositoryImpl();
        this.orderDAO = new OrderRepositoryImpl();
    }


    public Garage(String id, GaragePlaceRepositoryImpl garagePlaceDAO, MasterRepositoryImpl masterDAO, OrderRepositoryImpl orderDAO, GarageStatus isAvailable, boolean canRemoveGaragePlace, boolean canAddGaragePlace, boolean canRemoveOrder, boolean canRescheduleOrder) {
        this.id = id;
        this.garagePlaceDAO = garagePlaceDAO;
        this.masterDAO = masterDAO;
        this.orderDAO = orderDAO;
        this.isAvailable = isAvailable;
        this.canRemoveGaragePlace = canRemoveGaragePlace;
        this.canAddGaragePlace = canAddGaragePlace;
        this.canRemoveOrder = canRemoveOrder;
        this.canRescheduleOrder = canRescheduleOrder;
    }

    //Работа с masters
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


    //Работа с garagePlaces
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
        for (GaragePlace place : allGaragePlaces()) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }

    public List<GaragePlace> getGaragePlaces() {
        return new ArrayList<>(allGaragePlaces());
    }

    //Работа с orders
    public void createOrder(Order order) {
        orderDAO.createOrder(order);
    }

    public List<Order> allOrders() {
        return orderDAO.allOrders();
    }

    public void removeOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    //Все остальные getters/setters
    public String getId() {
        return this.id;
    }

    public GaragePlaceRepositoryImpl getGaragePlaceDAO() {
        return garagePlaceDAO;
    }

    public MasterRepositoryImpl getMasterDAO() {
        return masterDAO;
    }

    public OrderRepositoryImpl getOrderDAO() {
        return orderDAO;
    }

    public GarageStatus getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(GarageStatus isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getCanRescheduleOrder() {
        return canRescheduleOrder;
    }

    public void setCanRescheduleOrder(boolean canRescheduleOrder) {
        this.canRescheduleOrder = canRescheduleOrder;
    }

    public boolean getCanRemoveOrder() {
        return canRemoveOrder;
    }

    public void setCanRemoveOrder(boolean canRemoveOrder) {
        this.canRemoveOrder = canRemoveOrder;
    }

    public boolean getCanRemoveGaragePlace() {
        return canRemoveGaragePlace;
    }

    public void setCanRemoveGaragePlace(boolean canRemoveGaragePlace) {
        this.canRemoveGaragePlace = canRemoveGaragePlace;
    }

    public boolean getCanAddGaragePlace() {
        return canAddGaragePlace;
    }

    public void setCanAddGaragePlace(boolean canAddGaragePlace) {
        this.canAddGaragePlace = canAddGaragePlace;
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
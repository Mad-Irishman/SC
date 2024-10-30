package autoservice.models.garage;

import autoservice.config.properties.ConfigProperty;
import autoservice.dataBase.DAO.garargePlace.impl.GaragePlaceDAOImpl;
import autoservice.dataBase.DAO.master.impl.MasterDAOImpl;
import autoservice.dataBase.DAO.order.impl.OrderDAOImpl;
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
    private final GaragePlaceDAOImpl garagePlaceDAO;
    @JsonIgnore
    private final MasterDAOImpl masterDAO;
    @JsonIgnore
    private final OrderDAOImpl orderDAO;

    private final List<GaragePlace> garagePlaces;
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
        this.garagePlaces = new ArrayList<>();
        this.isAvailable = GarageStatus.AVAILABLE;
        this.masterDAO = new MasterDAOImpl();
        this.garagePlaceDAO = new GaragePlaceDAOImpl();
        this.orderDAO = new OrderDAOImpl();
    }


    public Garage(String id, GaragePlaceDAOImpl garagePlaceDAO, MasterDAOImpl masterDAO, OrderDAOImpl orderDAO, List<GaragePlace> garagePlaces, GarageStatus isAvailable, boolean canRemoveGaragePlace, boolean canAddGaragePlace, boolean canRemoveOrder, boolean canRescheduleOrder) {
        this.id = id;
        this.garagePlaceDAO = garagePlaceDAO;
        this.masterDAO = masterDAO;
        this.orderDAO = orderDAO;
        this.garagePlaces = garagePlaces;
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
        for (GaragePlace place : garagePlaces) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }

    public List<GaragePlace> getGaragePlaces() {
        return new ArrayList<>(garagePlaces);
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

    public GaragePlaceDAOImpl getGaragePlaceDAO() {
        return garagePlaceDAO;
    }

    public MasterDAOImpl getMasterDAO() {
        return masterDAO;
    }

    public OrderDAOImpl getOrderDAO() {
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
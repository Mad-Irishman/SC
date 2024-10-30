package autoservice.models.garage;

import autoservice.config.properties.ConfigProperty;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
//import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.garage.garageStatus.GarageStatus;
import autoservice.models.order.Order;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

public class Garage {
    private final String id;
    private final List<GaragePlace> garagePlaces;
    private final List<Order> orders;
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
        this.orders = new ArrayList<>();
        this.isAvailable = GarageStatus.AVAILABLE;
    }

    public Garage(String id, List<GaragePlace> garagePlaces, List<Master> masters, List<Order> orders, GarageStatus isAvailable, boolean canRemoveGaragePlace, boolean canAddGaragePlace, boolean canRemoveOrder, boolean canRescheduleOrder) {
        this.id = id;
        this.garagePlaces = garagePlaces;
        this.orders = orders;
        this.isAvailable = isAvailable;
        this.canRemoveGaragePlace = canRemoveGaragePlace;
        this.canAddGaragePlace = canAddGaragePlace;
        this.canRemoveOrder = canRemoveOrder;
        this.canRescheduleOrder = canRescheduleOrder;
    }

    public String getId() {
        return this.id;
    }

    public GarageStatus getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(GarageStatus isAvailable) {
        this.isAvailable = isAvailable;
    }

//    public void addMaster(Master master) {
//        masters.add(master);
//    }

//    public void removeMaster(Master master) {
//        masters.remove(master);
//    }

//    public List<Master> getAvailableMasters() {
//        List<Master> availableMasters = new ArrayList<>();
//        for (Master master : masterDAO.allMasters()) {
//            if (master.isAvailable() == MasterStatus.AVAILABLE) {
//                availableMasters.add(master);
//            }
//        }
//        return availableMasters;
//    }

//    public List<Master> getAllMasters() {
//        return masters;
//    }

//    public void addGaragePlace(GaragePlace place) {
//        garagePlaces.add(place);
//    }

//    public void removeGaragePlace(GaragePlace place) {
//        garagePlaces.remove(place);
//
//    }

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

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
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

//    private void loadProperties() {
//        Properties properties = new Properties();
//        try (InputStream input = new FileInputStream("src/autoservice/config/config.properties")) {
//            properties.load(input);
//
//            this.canRemoveGaragePlace = Boolean.parseBoolean(properties.getProperty("canRemoveGaragePlace"));
//            this.canAddGaragePlace = Boolean.parseBoolean(properties.getProperty("canAddGaragePlace"));
//            this.canRescheduleOrder = Boolean.parseBoolean(properties.getProperty("canRescheduleOrder"));
//            this.canRemoveOrder = Boolean.parseBoolean(properties.getProperty("canRemoveOrder"));
//
//
//        } catch (FileNotFoundException e) {
//            this.canRemoveGaragePlace = false;
//            this.canAddGaragePlace = false;
//            this.canRescheduleOrder = false;
//            this.canRemoveOrder = false;
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            this.canRemoveGaragePlace = false;
//            this.canAddGaragePlace = false;
//            this.canRescheduleOrder = false;
//            this.canRemoveOrder = false;
//            throw new RuntimeException(e);
//        }
//    }
}

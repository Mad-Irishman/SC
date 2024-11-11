package autoservice.manager.impl;

import autoservice.DI.Inject;
import autoservice.assistantManager.impl.Assistant;
import autoservice.config.properties.Configurator;
import autoservice.manager.ServiceManagerInterface;
import autoservice.exception.managerException.ServiceManagerException;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

public class ServiceManager implements ServiceManagerInterface {
    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);
    @Inject
    private Garage garage;
    @Inject
    private Assistant assistant;

    public ServiceManager() {
    }

    public ServiceManager(Garage garage, Assistant assistant) {
        this.garage = garage;
        this.assistant = assistant;
        try {
            Configurator.configure(garage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOrders(List<Order> orders) {
        for (Order order : orders) {
            garage.createOrder(order);
        }
    }

    @Override
    public void setMasters(List<Master> masters) {
        for (Master master : masters) {
            if (garage.getMasterById(master.getId()) == null) {
                garage.addMaster(master);
            } else {
                logger.info("Master with ID {} already exists, skipping addition.", master.getId());
            }
        }
    }

    @Override
    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    @Override
    public void addMaster(Master master) throws ServiceManagerException {
        if (master == null) {
            throw new ServiceManagerException("Master cannot be null");
        }
        try {
            garage.addMaster(master);
            logger.info("Master was added to the garage");
        } catch (Exception e) {
            throw new ServiceManagerException("Error adding master. Please try again later.");
        }
    }

    @Override
    public void removeMaster(Master master) throws ServiceManagerException {
        if (master == null) {
            throw new ServiceManagerException("Master cannot be null");
        }
        try {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                garage.removeMaster(master);
            } else {
                System.out.println("Cannot remove the master because they have an active order.");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error removing master. Please try again later.");
        }
    }

    @Override
    public Master getMasterById(String id) throws ServiceManagerException {
        if (id == null || id.trim().isEmpty()) {
            throw new ServiceManagerException("Master ID cannot be null or empty");
        }
        try {
            for (Master master : garage.allMasters()) {
                if (master.getId().equals(id)) {
                    return master;
                }
            }
            return null;
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving master by ID: " + id);
        }
    }


    @Override
    public List<Master> getMasters() throws ServiceManagerException {
        try {
            return garage.allMasters();
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving master list");
        }
    }


    @Override
    public List<Master> getAllMasterInGarage() throws ServiceManagerException {
        try {
            return garage.allMasters();
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving masters from garage");
        }
    }

    @Override
    public Master findMasterByName(String masterName) {
        if (masterName == null || masterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Master name cannot be null or empty");
        }

        Optional<Master> foundMaster = garage.allMasters().stream()
                .filter(master -> master.getName().equalsIgnoreCase(masterName))
                .findFirst();

        return foundMaster.orElseThrow(() -> new IllegalArgumentException("Master with name '" + masterName + "' not found"));
    }

    @Override
    public GaragePlace findGaragePlaceByNumber(String garagePlaceNumber) {
        if (garagePlaceNumber == null || garagePlaceNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Garage place number cannot be null or empty");
        }

        int placeNumber;
        try {
            placeNumber = Integer.parseInt(garagePlaceNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid garage place number format: " + garagePlaceNumber);
        }

        List<GaragePlace> garagePlaces = garage.allGaragePlaces();

        Optional<GaragePlace> foundGaragePlace = garagePlaces.stream()
                .filter(garagePlace -> garagePlace.getPlaceNumber() == placeNumber)
                .findFirst();

        return foundGaragePlace.orElseThrow(() ->
                new IllegalArgumentException("Garage place with number '" + garagePlaceNumber + "' not found"));
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        if (!garage.getCanAddGaragePlace()) {
            garage.addGaragePlace(garagePlace);
        } else {
            System.out.println("You cannot add garage spaces at this time.");
        }
    }

    @Override
    public void removeGaragePlace(GaragePlace garagePlace) throws ServiceManagerException {
        if (!garagePlace.isOccupied() && !garage.getCanRemoveGaragePlace()) {
            this.garage.removeGaragePlace(garagePlace);
        } else {
            System.out.println("Cannot remove garage place because it is occupied or prohibited at administrator level");
        }
    }

    @Override
    public List<GaragePlace> allGaragePlaces() throws ServiceManagerException {
        try {
            return garage.allGaragePlaces();
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving garage places: " + e.getMessage());
        }
    }

    @Override
    public GaragePlace getGaragePlaceByNumber(int placeNumber) {
        for (GaragePlace place : garage.allGaragePlaces()) {
            if (place.getPlaceNumber() == placeNumber) {
                return place;
            }
        }
        return null;
    }

    @Override
    public Garage getGarage() {
        return garage;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        return garage.getAvailableGaragePlaces();
    }

    @Override // надо придумать как после создания заказа обновить данные в бд
    public void createOrder(String description, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) throws ServiceManagerException {
        if (description == null || submissionDate == null || completionDate == null || plannedStartDate == null) {
            throw new ServiceManagerException("Order parameters cannot be null");
        }
        try {
            if (!garage.getAvailableMasters().isEmpty() && !garage.allGaragePlaces().isEmpty()) {
                Order order = new Order(description, submissionDate, completionDate, plannedStartDate, price);
                order.setAssignedMaster(garage.getAvailableMasters().get(0));
                order.getAssignedMaster().setAvailable(MasterStatus.OCCUPIED);

                order.setAssignedGaragePlace(garage.getAvailableGaragePlaces().get(0));
                garage.getAvailableGaragePlaces().get(0).setIdOrder(order.getIdOrder());
                order.getAssignedGaragePlace().setOccupied(true);

                garage.createOrder(order);
                garage.getGaragePlaceDAO().updateGaragePlace(order.getAssignedGaragePlace());
                garage.getMasterDAO().updateMaster(order.getAssignedMaster());

                System.out.println("Order created: " + order.getDescription());
            } else {
                System.out.println("No available masters or garage places to create an order.");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error creating order. Please try again later.");
        }
    }

    @Override
    public void addOrder(Order order) throws ServiceManagerException { // короче надо придумать как после создания или добавления заказа обновить данные в бд
        if (order == null) {
            throw new ServiceManagerException("Order cannot be null");
        }

        try {
            List<Master> availableMasters = garage.getAvailableMasters();
            List<GaragePlace> availableGaragePlaces = garage.getAvailableGaragePlaces();

            if (availableMasters.isEmpty()) {
                throw new ServiceManagerException("No available masters to assign to the order");
            }

            if (availableGaragePlaces.isEmpty()) {
                throw new ServiceManagerException("No available garage places to assign to the order");
            }

            Master assignedMaster = availableMasters.get(0);
            GaragePlace assignedGaragePlace = availableGaragePlaces.get(0);
            assignedGaragePlace.setIdOrder(order.getIdOrder());

            order.setAssignedMaster(assignedMaster);
            order.setAssignedGaragePlace(assignedGaragePlace);

            assignedMaster.setAvailable(MasterStatus.OCCUPIED);
            assignedGaragePlace.setOccupied(true);

            garage.createOrder(order);
            garage.getGaragePlaceDAO().updateGaragePlace(order.getAssignedGaragePlace());
            garage.getMasterDAO().updateMaster(order.getAssignedMaster());

            System.out.println("Order added: " + order.getDescription());
        } catch (Exception e) {
            throw new ServiceManagerException("Error adding order. Please try again later.");
        }
    }


    @Override
    public List<Order> getOrders() {
        return garage.allOrders();
    }

    @Override
    public List<Order> getAllOrdersInGarage() {
        return garage.allOrders();
    }

    @Override
    public Order getOrderByDescription(String description) throws ServiceManagerException {
        if (description == null || description.trim().isEmpty()) {
            throw new ServiceManagerException("Order description cannot be empty.");
        }
        try {
            for (Order order : garage.allOrders()) {
                if (order.getDescription().equalsIgnoreCase(description)) {
                    return order;
                }
            }
            throw new ServiceManagerException("Order with description " + description + " not found.");
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving order by description: " + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(String id) throws ServiceManagerException {
        try {
            for (Order order : garage.allOrders()) {
                if (order.getIdOrder().equals(id)) {
                    return order;
                }
            }
            throw new ServiceManagerException("Order with ID " + id + " not found.");
        } catch (Exception e) {
            throw new ServiceManagerException("Error retrieving order by ID. Please try again later.");
        }
    }

    @Override
    public void removeOrder(Order order) {
        if (garage.getOrderDAO().deleteOrder(order) && !garage.getCanRemoveOrder()) {
            order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
            order.getAssignedGaragePlace().setOccupied(false);
            garage.removeOrder(order);
            garage.getMasterDAO().updateMaster(order.getAssignedMaster());
            garage.getGaragePlaceDAO().updateGaragePlace(order.getAssignedGaragePlace());
            System.out.println("Order removed: " + order);
        } else {
            System.out.println("Order not found or cannot be removed.");
        }
    }

    @Override
    public void completeOrder(Order order) throws ServiceManagerException {
        if (order == null) {
            throw new ServiceManagerException("Order cannot be null");
        }
        try {
            if (order.getStatusOrder() == OrderStatus.CREATED) {
                order.setStatusOrder(OrderStatus.COMPLETED);
                order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
                order.getAssignedGaragePlace().setOccupied(false);
                System.out.println("Order completed: " + order);
            } else {
                System.out.println("Order already completed or does not exist.");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error completing order. Please try again later.");
        }
    }

    @Override
    public void cancelOrder(Order order) throws ServiceManagerException {
        if (order == null) {
            throw new ServiceManagerException("Order cannot be null");
        }
        try {
            if (order.getStatusOrder() == OrderStatus.CREATED && garage.allOrders().contains(order)) {
                order.setStatusOrder(OrderStatus.CANCELLED);
                order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
                order.getAssignedGaragePlace().setOccupied(false);
                System.out.println("Order cancelled: " + order);
            } else {
                System.out.println("Order not found: " + order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while cancelling the order. Please try again later.");
        }
    }

    @Override
    public void adjustOrdersForDelay(String orderId, int delayInHours) {
        if (delayInHours > 0 && !garage.getCanRescheduleOrder()) {
            Order delayedOrder = getOrderById(orderId);
            LocalDateTime newCompletionDate = delayedOrder.getCompletionDate().plusHours(delayInHours);
            delayedOrder.setCompletionDate(newCompletionDate);
            System.out.println("Order " + delayedOrder.getIdOrder() + " delayed. New completion time: " + newCompletionDate);

            for (Order order : garage.allOrders()) {
                if (!Objects.equals(order.getIdOrder(), delayedOrder.getIdOrder())) {
                    LocalDateTime newStartTime = order.getSubmissionDate().plusHours(delayInHours);
                    LocalDateTime newEstimatedEndTime = order.getCompletionDate().plusHours(delayInHours);
                    order.setSubmissionDate(newStartTime);
                    order.setCompletionDate(newEstimatedEndTime);
                    System.out.println("Order " + order.getIdOrder() + " adjusted. New start time: " + newStartTime
                            + ", New completion time: " + newEstimatedEndTime);
                }
            }
        } else {
            System.out.println("At the moment it is not possible to change the order time");
        }
    }

    @Override
    public void showAllOrders() {
        System.out.println("All orders:");
        for (Order order : garage.allOrders()) {
            System.out.println(order);
        }
    }

    @Override
    public void showAvailableMasters() {
        System.out.println("Available masters:");
        for (Master master : garage.allMasters()) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                System.out.println(master);
            }
        }
    }

    @Override
    public void showAvailableGaragePlaces() {
        System.out.println("Available garage places:");
        for (GaragePlace place : garage.allGaragePlaces()) {
            if (!place.isOccupied()) {
                System.out.println(place);
            }
        }
    }

    @Override
    public List<Master> getMastersByOrders(List<Master> masters, Order order) throws ServiceManagerException {
        if (order == null) {
            throw new ServiceManagerException("Order cannot be null");
        }
        try {
            for (Master master : assistant.getMastersByOrders(masters, order)) {
                System.out.println(master);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving masters for the order. Please try again later.");
        }
        return masters;
    }

    @Override
    public List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators) throws ServiceManagerException {
        if (comparators == null) {
            throw new ServiceManagerException("Comparator list cannot be null");
        }
        try {
            for (Master master : assistant.getSortedMasters(masters, comparators)) {
                System.out.println(master);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while sorting masters. Please try again later.");
        }
        return masters;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) throws ServiceManagerException {
        try {
            for (GaragePlace place : assistant.getAvailableGaragePlaces(garages)) {
                System.out.println(place);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving available garage places. Please try again later.");
        }
        return null;
    }

    @Override
    public void getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date) throws ServiceManagerException {
        if (date == null) {
            throw new ServiceManagerException("Date cannot be null");
        }
        try {
            System.out.println(assistant.getFreePlacesOnDate(orders, masters, garages, date));
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving free places for the given date. Please try again later.");
        }
    }

    @Override
    public void getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages) throws ServiceManagerException {
        try {
            System.out.println(assistant.getNearestFreeDate(masters, orders, garages));
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving the nearest free date. Please try again later.");
        }
    }

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) throws ServiceManagerException {
        if (comparators == null) {
            throw new ServiceManagerException("Comparator list cannot be null");
        }
        try {
            for (Order order : assistant.getSortedOrders(orders, comparators)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while sorting orders. Please try again later.");
        }
        return orders;
    }

    @Override
    public void getOrdersByMaster(List<Order> orders, Master master) throws ServiceManagerException {
        if (master == null) {
            throw new ServiceManagerException("Master cannot be null");
        }
        try {
            for (Order order : assistant.getOrdersByMaster(orders, master)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving orders by master. Please try again later.");
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) throws ServiceManagerException {
        try {
            for (Order order : assistant.getCurrentOrders(orders)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving current orders. Please try again later.");
        }
        return orders;
    }

    @Override
    public void getOrdersByStatus(List<Order> orders, OrderStatus status) throws ServiceManagerException {
        if (status == null) {
            throw new ServiceManagerException("Order status cannot be null");
        }
        try {
            for (Order order : assistant.getOrdersByStatus(orders, status)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving orders by status. Please try again later.");
        }
    }

    @Override
    public void getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) throws ServiceManagerException {
        if (orders == null || startTime == null || endTime == null) {
            throw new ServiceManagerException("Parameters cannot be null");
        }
        if (startTime.isAfter(endTime)) {
            throw new ServiceManagerException("Start time cannot be after end time");
        }
        try {
            for (Order order : assistant.getOrdersByTimeFrame(orders, startTime, endTime)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Error while retrieving orders by time frame. Please try again later.");
        }
    }
}
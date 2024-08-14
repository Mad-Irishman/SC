package autoservice.manager.impl;

import autoservice.assistantManager.impl.Assistant;
import autoservice.manager.ServiceManagerInterface;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceManager implements ServiceManagerInterface {
    private final List<Master> masters;
    private final Garage garage;
    private final List<Garage> garages;
    private final List<Order> orders;
    private Assistant assistant;
    private static final int DEFAULT_NUMBER_OF_MASTERS = 10;
    private static final int DEFAULT_NUMBER_OF_PLACES = 10;


    public ServiceManager() {
        this.masters = new ArrayList<>();
        this.garage = new Garage();
        this.garages = new ArrayList<>();
        this.garages.add(garage);
        this.orders = new ArrayList<>();
        this.assistant = new Assistant();
        initializeMasters(DEFAULT_NUMBER_OF_MASTERS);
        initializeGaragePlaces(DEFAULT_NUMBER_OF_PLACES);
    }

    private void initializeMasters(int numberOfMasters) {
        for (int i = 1; i <= numberOfMasters; i++) {
            Master master = new Master("Master " + i);
            this.garage.addMaster(master);
        }
    }

    private void setAssignOrderMaster(Order order) {
        for (Master master : garage.getAvailableMaster()) {
            master.setOrderMaster(order);
        }
    }


    public void addMaster(Master master) {
        this.garage.addMaster(master);
    }

    public void removeMaster(Master master) {
        if (master.isAvailable() == MasterStatus.AVAILABLE) {
            this.garage.removeMaster(master);
        } else {
            System.out.println("It is impossible to delete a master because he has an order");
        }
    }

    public List<Master> getMasters() {
        return masters;
    }


    private void initializeGaragePlaces(int numberOfPlaces) {
        for (int i = 1; i <= numberOfPlaces; i++) {
            this.garage.addGaragePlace(new GaragePlace(i));
        }
    }

    public void addGaragePlace(GaragePlace garagePlace) {
        this.garage.addGaragePlace(garagePlace);
    }

    public void removeGaragePlace(GaragePlace garagePlace) {
        if (!garagePlace.isOccupied()) {
            this.garage.removeGaragePlace(garagePlace);
        } else {
            System.out.println("It is impossible to delete a place because the order is there");
        }
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void createOrder(String discription, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        if (!garage.getAvailableMaster().isEmpty() && !garage.getAvailableGaragePlaces().isEmpty()) {
            Order order = new Order(discription, submissionDate, completionDate, plannedStartDate, price);
            order.setAssignedMaster(garage.getAvailableMaster().get(0));
            order.getAssignedMaster().setAvailable(MasterStatus.OCCUPIED);

            order.setAssignedGaragePlace(garage.getAvailableGaragePlaces().get(0));
            order.getAssignedGaragePlace().setOccupied(true);
            orders.add(order);
            System.out.println("Order created: " + order);
        } else {
            System.out.println("Master or place is not available for order creation.");
        }
    }

    public List<Order> getOrders() {
        return List.of();
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getIdOrder() == id) {
                return order;
            }
        }
        System.out.println("Order not found: " + id);
        return null;
    }

    public void removeOrder(Order order) {
        if (orders.remove(order)) {
            order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
            order.getAssignedMaster().assingOrderMaster(null);
            order.getAssignedGaragePlace().setOccupied(false);
            System.out.println("Order removed: " + order);
        } else {
            System.out.println("Order not found: " + order);
        }
    }

    public void completeOrder(Order order) {
        if (order != null && order.getStatusOrder() == OrderStatus.CREATED) {
            order.setStatusOrder(OrderStatus.COMPLETED);
            order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
            order.getAssignedMaster().assingOrderMaster(null);
            order.getAssignedGaragePlace().setOccupied(false);
            System.out.println("Order completed: " + order);
        } else {
            System.out.println("Order is already completed or does not exist.");
        }
    }

    public void cancelOrder(Order order) {
        if (order != null && order.getStatusOrder() == OrderStatus.CREATED && orders.contains(order)) {
            order.setStatusOrder(OrderStatus.CANCELLED);
            order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
            order.getAssignedMaster().assingOrderMaster(null);
            order.getAssignedGaragePlace().setOccupied(false);
            System.out.println("Order cancelled: " + order);
        } else {
            System.out.println("Order not found: " + order);
        }
    }

    public void adjustOrdersForDelay(int orderId, int delayInHours) {
        Order delayedOrder = getOrderById(orderId);
        if (delayedOrder == null) {
            System.out.println("Order with ID " + orderId + " not found.");
            return;
        }

        LocalDateTime newCompletionDate = delayedOrder.getCompletionDate().plusHours(delayInHours);
        delayedOrder.setCompletionDate(newCompletionDate);
        System.out.println("Order " + delayedOrder.getIdOrder() + " delayed. New end time: " + newCompletionDate);

        for (Order order : orders) {
            if (order.getIdOrder() != delayedOrder.getIdOrder()) {
                LocalDateTime newStartTime = order.getSubmissionDate().plusHours(delayInHours);
                LocalDateTime newEstimatedEndTime = order.getCompletionDate().plusHours(delayInHours);
                order.setSubmissionDate(newStartTime);
                order.setCompletionDate(newEstimatedEndTime);
                System.out.println("Order " + order.getIdOrder() + " adjusted. New start time: " + newStartTime
                        + ", New end time: " + newEstimatedEndTime);
            }
        }
    }

    public void showAllOrders() {
        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void showAvailableMasters() {
        System.out.println("Available Masters:");
        for (Master master : masters) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                System.out.println(master);
            }
        }
    }

    public void showAvailableGaragePlaces() {
        System.out.println("Available Garage Places:");
        for (GaragePlace place : garage.getAvailableGaragePlaces()) {
            if (!place.isOccupied()) {
                System.out.println(place);
            }
        }
    }

    public void getMastersByOrders(Order order) {
        for (Master master : assistant.getMastersByOrders(order)) {
            System.out.println(master);
        }
    }

    public void getSortedMasters(List<Comparator<Master>> comparators) {
        for (Master master : assistant.getSortedMasters(comparators)) {
            System.out.println(master);
        }
    }

    public void getAvailableGaragePlaces() {
        for (GaragePlace place : assistant.getAvailableGaragePlaces()) {
            System.out.println(place);
        }
    }

    public void getFreePlacesOnDate(LocalDateTime date) {
        System.out.println(assistant.getFreePlacesOnDate(date));
    }

    public void getNearestFreeDate() {
        System.out.println(assistant.getNearestFreeDate());
    }

    public void getSortedOrders(List<Comparator<Order>> comparators) {
        for (Order order: assistant.getSortedOrders(comparators)) {
            System.out.println(order);
        }
    }

    public void getOrdersByMaster(Master master) {
        for (Order order: assistant.getOrdersByMaster(master)) {
            System.out.println(order);
        }
    }

    public void getCurrentOrders() {
        for (Order order: assistant.getCurrentOrders()) {
            System.out.println(order);
        }
    }

    public void getOrdersByStatus(OrderStatus status) {
        for (Order order: assistant.getOrdersByStatus(status)) {
            System.out.println(order);
        }
    }

    public void getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        for (Order order: assistant.getOrdersByTimeFrame(orders, startTime, endTime)) {
            System.out.println(order);
        }
    }

}
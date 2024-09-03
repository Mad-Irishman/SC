package autoservice.manager.impl;

import autoservice.assistantManager.impl.Assistant;
import autoservice.manager.ServiceManagerInterface;
import autoservice.manager.exception.ServiceManagerException;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.garagePlace.exception.GaragePlaceException;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.impl.DataSort;
import autoservice.servicesSorting.GaragePlacesSort.impl.GaragePlacesSort;
import autoservice.servicesSorting.MastersSort.impl.MastersSort;
import autoservice.servicesSorting.OrdersSort.impl.OrdersSort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceManager implements ServiceManagerInterface {
    private final List<Master> masters;
    private final Garage garage;
    private final List<Garage> garages;
    private final List<Order> orders;
    private final Assistant assistant;
    private static final int DEFAULT_NUMBER_OF_MASTERS = 10;
    private static final int DEFAULT_NUMBER_OF_PLACES = 10;

    public ServiceManager() {
        this.masters = new ArrayList<>();
        this.garage = new Garage();
        this.garages = new ArrayList<>();
        this.garages.add(garage);
        this.orders = new ArrayList<>();

        MastersSort mastersSort = new MastersSort();
        GaragePlacesSort garagePlacesSort = new GaragePlacesSort();
        DataSort dataSort = new DataSort();
        OrdersSort ordersSort = new OrdersSort();
        this.assistant = new Assistant(mastersSort, garagePlacesSort, dataSort, ordersSort);

        initializeMasters(DEFAULT_NUMBER_OF_MASTERS);
        initializeGaragePlaces(DEFAULT_NUMBER_OF_PLACES);
    }

    @Override
    public void addMaster(Master master) {
        if (master == null) {
            throw new ServiceManagerException("Мастер не может быть null");
        }
        try {
            this.garage.addMaster(master);
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при добавлении мастера. Попробуйте снова позже.");
        }
    }

    @Override
    public void removeMaster(Master master) {
        if (master == null) {
            throw new ServiceManagerException("Мастер не может быть null");
        }
        try {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                this.garage.removeMaster(master);
            } else {
                System.out.println("Невозможно удалить мастера, так как у него есть заказ");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при удалении мастера. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Master> getMasters() {
        return masters;
    }

    @Override
    public List<Master> getAllMasterInGarage() {
        return garage.getAllMasters();
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        if (garagePlace == null) {
            throw new ServiceManagerException("Гаражное место не может быть null");
        }
        try {
            this.garage.addGaragePlace(garagePlace);
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при добавлении гаражного места. Попробуйте снова позже.");
        }
    }

    @Override
    public void removeGaragePlace(GaragePlace garagePlace) {
        if (garagePlace == null) {
            throw new ServiceManagerException("Гаражное место не может быть null");
        }
        try {
            if (!garagePlace.isOccupied()) {
                this.garage.removeGaragePlace(garagePlace);
            } else {
                System.out.println("Невозможно удалить место, так как оно занято");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при удалении гаражного места. Попробуйте снова позже.");
        }
    }

    public List<GaragePlace> allGaragePlaces() {
        try {
            if (garage == null) {
                throw new ServiceManagerException("Гараж не инициализирован.");
            }
            return this.garage.getGaragePlaces();
        } catch (ServiceManagerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении списка мест в гараже: " + e.getMessage());
        }
    }

    public GaragePlace getGaragePlaceByNumber(int placeNumber) {
        try {
            if (garage == null) {
                throw new ServiceManagerException("Гараж не инициализирован.");
            }
            if (placeNumber <= 0) {
                throw new GaragePlaceException("Номер места должен быть положительным числом.");
            }
            for (GaragePlace place : garage.getGaragePlaces()) {
                if (place.getPlaceNumber() == placeNumber) {
                    return place;
                }
            }
            throw new GaragePlaceException("Место с номером " + placeNumber + " не найдено.");
        } catch (GaragePlaceException e) {
            throw e;
        } catch (ServiceManagerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении места в гараже по номеру: " + e.getMessage());
        }
    }

    @Override
    public List<Garage> getGarages() {
        return garages;
    }

    @Override
    public void createOrder(String description, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price) {
        if (description == null || submissionDate == null || completionDate == null || plannedStartDate == null) {
            throw new ServiceManagerException("Параметры заказа не могут быть null");
        }
        try {
            if (!garage.getAvailableMaster().isEmpty() && !garage.getAvailableGaragePlaces().isEmpty()) {
                Order order = new Order(description, submissionDate, completionDate, plannedStartDate, price);
                order.setAssignedMaster(garage.getAvailableMaster().get(0));
                order.getAssignedMaster().setAvailable(MasterStatus.OCCUPIED);

                order.setAssignedGaragePlace(garage.getAvailableGaragePlaces().get(0));
                order.getAssignedGaragePlace().setOccupied(true);
                orders.add(order);
                System.out.println("Заказ создан: " + order.getDescription());
            } else {
                System.out.println("Мастер или место не доступны для создания заказа.");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при создании заказа. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderByDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new ServiceManagerException("Описание заказа не может быть пустым.");
        }
        for (Order order : orders) {
            if (order.getDescription().equalsIgnoreCase(description)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order getOrderById(int id) {
        try {
            for (Order order : orders) {
                if (order.getIdOrder() == id) {
                    return order;
                }
            }
            throw new ServiceManagerException("Заказ с ID " + id + " не найден.");
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении заказа по ID. Попробуйте снова позже.");
        }
    }

    @Override
    public void removeOrder(Order order) {
        if (order == null) {
            throw new ServiceManagerException("Заказ не может быть null");
        }
        try {
            if (orders.remove(order)) {
                order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
                order.getAssignedMaster().assignOrderMaster(null);
                order.getAssignedGaragePlace().setOccupied(false);
                System.out.println("Заказ удален: " + order);
            } else {
                System.out.println("Заказ не найден: " + order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при удалении заказа. Попробуйте снова позже.");
        }
    }

    @Override
    public void completeOrder(Order order) {
        if (order == null) {
            throw new ServiceManagerException("Заказ не может быть null");
        }
        try {
            if (order.getStatusOrder() == OrderStatus.CREATED) {
                order.setStatusOrder(OrderStatus.COMPLETED);
                order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
                order.getAssignedMaster().assignOrderMaster(null);
                order.getAssignedGaragePlace().setOccupied(false);
                System.out.println("Заказ завершен: " + order);
            } else {
                System.out.println("Заказ уже завершен или не существует.");
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при завершении заказа. Попробуйте снова позже.");
        }
    }

    @Override
    public void cancelOrder(Order order) {
        if (order == null) {
            throw new ServiceManagerException("Заказ не может быть null");
        }
        try {
            if (order.getStatusOrder() == OrderStatus.CREATED && orders.contains(order)) {
                order.setStatusOrder(OrderStatus.CANCELLED);
                order.getAssignedMaster().setAvailable(MasterStatus.AVAILABLE);
                order.getAssignedMaster().assignOrderMaster(null);
                order.getAssignedGaragePlace().setOccupied(false);
                System.out.println("Заказ отменен: " + order);
            } else {
                System.out.println("Заказ не найден: " + order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при отмене заказа. Попробуйте снова позже.");
        }
    }

    @Override
    public void adjustOrdersForDelay(int orderId, int delayInHours) {
        if (delayInHours < 0) {
            throw new ServiceManagerException("Задержка не может быть отрицательной");
        }
        try {
            Order delayedOrder = getOrderById(orderId);
            LocalDateTime newCompletionDate = delayedOrder.getCompletionDate().plusHours(delayInHours);
            delayedOrder.setCompletionDate(newCompletionDate);
            System.out.println("Заказ " + delayedOrder.getIdOrder() + " задержан. Новое время завершения: " + newCompletionDate);

            for (Order order : orders) {
                if (order.getIdOrder() != delayedOrder.getIdOrder()) {
                    LocalDateTime newStartTime = order.getSubmissionDate().plusHours(delayInHours);
                    LocalDateTime newEstimatedEndTime = order.getCompletionDate().plusHours(delayInHours);
                    order.setSubmissionDate(newStartTime);
                    order.setCompletionDate(newEstimatedEndTime);
                    System.out.println("Заказ " + order.getIdOrder() + " скорректирован. Новое время начала: " + newStartTime
                            + ", Новое время завершения: " + newEstimatedEndTime);
                }
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при корректировке заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public void showAllOrders() {
        System.out.println("Все заказы:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Override
    public void showAvailableMasters() {
        System.out.println("Доступные мастера:");
        for (Master master : masters) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                System.out.println(master);
            }
        }
    }

    @Override
    public void showAvailableGaragePlaces() {
        System.out.println("Доступные гаражные места:");
        for (GaragePlace place : garage.getAvailableGaragePlaces()) {
            if (!place.isOccupied()) {
                System.out.println(place);
            }
        }
    }

    @Override
    public List<Master> getMastersByOrders(List<Master> masters, Order order) {
        if (order == null) {
            throw new ServiceManagerException("Заказ не может быть null");
        }
        try {
            for (Master master : assistant.getMastersByOrders(masters, order)) {
                System.out.println(master);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении мастеров по заказу. Попробуйте снова позже.");
        }
        return masters;
    }

    @Override
    public List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators) {
        if (comparators == null) {
            throw new ServiceManagerException("Список компараторов не может быть null");
        }
        try {
            for (Master master : assistant.getSortedMasters(masters, comparators)) {
                System.out.println(master);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при сортировке мастеров. Попробуйте снова позже.");
        }
        return masters;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) {
        try {
            for (GaragePlace place : assistant.getAvailableGaragePlaces(garages)) {
                System.out.println(place);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении доступных гаражных мест. Попробуйте снова позже.");
        }
        return null;
    }

    @Override
    public void getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date) {
        if (date == null) {
            throw new ServiceManagerException("Дата не может быть null");
        }
        try {
            System.out.println(assistant.getFreePlacesOnDate(orders, masters, garages, date));
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении свободных мест на указанную дату. Попробуйте снова позже.");
        }
    }

    @Override
    public void getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages) {
        try {
            System.out.println(assistant.getNearestFreeDate(masters, orders, garages));
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении ближайшей свободной даты. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) {
        if (comparators == null) {
            throw new ServiceManagerException("Список компараторов не может быть null");
        }
        try {
            for (Order order : assistant.getSortedOrders(orders, comparators)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при сортировке заказов. Попробуйте снова позже.");
        }
        return orders;
    }

    @Override
    public void getOrdersByMaster(List<Order> orders, Master master) {
        if (master == null) {
            throw new ServiceManagerException("Мастер не может быть null");
        }
        try {
            for (Order order : assistant.getOrdersByMaster(orders, master)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении заказов по мастеру. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) {
        try {
            for (Order order : assistant.getCurrentOrders(orders)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении текущих заказов. Попробуйте снова позже.");
        }
        return orders;
    }

    @Override
    public void getOrdersByStatus(List<Order> orders, OrderStatus status) {
        if (status == null) {
            throw new ServiceManagerException("Статус заказа не может быть null");
        }
        try {
            for (Order order : assistant.getOrdersByStatus(orders, status)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении заказов по статусу. Попробуйте снова позже.");
        }
    }

    @Override
    public void getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        if (orders == null || startTime == null || endTime == null) {
            throw new ServiceManagerException("Параметры не могут быть null");
        }
        if (startTime.isAfter(endTime)) {
            throw new ServiceManagerException("Время начала не может быть после времени окончания");
        }
        try {
            for (Order order : assistant.getOrdersByTimeFrame(orders, startTime, endTime)) {
                System.out.println(order);
            }
        } catch (Exception e) {
            throw new ServiceManagerException("Ошибка при получении заказов по временным рамкам. Попробуйте снова позже.");
        }
    }

    private void initializeMasters(int numberOfMasters) {
        for (int i = 1; i <= numberOfMasters; i++) {
            Master master = new Master("Master " + i);
            this.garage.addMaster(master);
        }
    }

    private void initializeGaragePlaces(int numberOfPlaces) {
        for (int i = 1; i <= numberOfPlaces; i++) {
            this.garage.addGaragePlace(new GaragePlace(i));
        }
    }
}

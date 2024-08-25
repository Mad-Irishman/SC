package autoservice.assistantManager.impl;

import autoservice.assistantManager.AssistantInterface;
import autoservice.assistantManager.exception.AssistantException;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.impl.DataSort;
import autoservice.servicesSorting.GaragePlacesSort.impl.GaragePlacesSort;
import autoservice.servicesSorting.MastersSort.impl.MastersSort;
import autoservice.servicesSorting.OrdersSort.impl.OrdersSort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class Assistant implements AssistantInterface {
    private final MastersSort mastersSort;
    private final GaragePlacesSort garagePlacesSort;
    private final DataSort dataSort;
    private final OrdersSort ordersSort;

    public Assistant(MastersSort mastersSort, GaragePlacesSort garagePlacesSort, DataSort dataSort, OrdersSort ordersSort) {
        this.mastersSort = mastersSort;
        this.garagePlacesSort = garagePlacesSort;
        this.dataSort = dataSort;
        this.ordersSort = ordersSort;
    }

    @Override
    public List<Master> getMastersByOrders(List<Master> masters, Order order) {
        if (order == null) {
            throw new AssistantException("Заказ не может быть null");
        }
        try {
            return mastersSort.getMastersByOrders(masters, order);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении мастеров по заказу. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators) {
        if (comparators == null) {
            throw new AssistantException("Список компараторов не может быть null");
        }
        try {
            return mastersSort.getSortedMasters(masters, comparators);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при сортировке мастеров. Попробуйте снова позже.");
        }
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) {
        try {
            return garagePlacesSort.getAvailableGaragePlaces(garages);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении доступных гаражных мест. Попробуйте снова позже.");
        }
    }

    @Override
    public int getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date) {
        if (date == null) {
            throw new AssistantException("Дата не может быть null");
        }
        try {
            return dataSort.getFreePlacesOnDate(orders, masters, garages, date);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении свободных мест на указанную дату. Попробуйте снова позже.");
        }
    }

    @Override
    public LocalDateTime getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages) {
        try {
            return dataSort.getNearestFreeDate(masters, orders, garages);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении ближайшей свободной даты. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) {
        if (comparators == null) {
            throw new AssistantException("Список компараторов не может быть null");
        }
        try {
            return ordersSort.getSortedOrders(orders, comparators);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при сортировке заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByMaster(List<Order> orders, Master master) {
        if (master == null) {
            throw new AssistantException("Мастер не может быть null");
        }
        try {
            return ordersSort.getOrdersByMaster(orders, master);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении заказов по мастеру. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) {
        try {
            return ordersSort.getCurrentOrders(orders);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении текущих заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status) {
        if (status == null) {
            throw new AssistantException("Статус заказа не может быть null");
        }
        try {
            return ordersSort.getOrdersByStatus(orders, status);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении заказов по статусу. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        if (orders == null || startTime == null || endTime == null) {
            throw new AssistantException("Параметры не могут быть null");
        }
        if (startTime.isAfter(endTime)) {
            throw new AssistantException("Время начала не может быть после времени окончания");
        }
        try {
            return ordersSort.getOrdersByTimeFrame(orders, startTime, endTime);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении заказов по временным рамкам. Попробуйте снова позже.");
        }
    }
}

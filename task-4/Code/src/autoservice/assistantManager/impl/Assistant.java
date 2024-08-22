package autoservice.assistantManager.impl;

import autoservice.assistantManager.AssistantInterface;
import autoservice.assistantManager.exception.AssistantException;
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
        if (mastersSort == null || garagePlacesSort == null || dataSort == null || ordersSort == null) {
            throw new AssistantException("Один или несколько зависимых сервисов не могут быть null");
        }
        this.mastersSort = mastersSort;
        this.garagePlacesSort = garagePlacesSort;
        this.dataSort = dataSort;
        this.ordersSort = ordersSort;
    }

    @Override
    public List<Master> getMastersByOrders(Order order) {
        if (order == null) {
            throw new AssistantException("Заказ не может быть null");
        }
        try {
            return mastersSort.getMastersByOrders(order);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении мастеров по заказу. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        if (comparators == null) {
            throw new AssistantException("Список компараторов не может быть null");
        }
        try {
            return mastersSort.getSortedMasters(comparators);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при сортировке мастеров. Попробуйте снова позже.");
        }
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        try {
            return garagePlacesSort.getAvailableGaragePlaces();
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении доступных гаражных мест. Попробуйте снова позже.");
        }
    }

    @Override
    public int getFreePlacesOnDate(LocalDateTime date) {
        if (date == null) {
            throw new AssistantException("Дата не может быть null");
        }
        try {
            return dataSort.getFreePlacesOnDate(date);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении свободных мест на указанную дату. Попробуйте снова позже.");
        }
    }

    @Override
    public LocalDateTime getNearestFreeDate() {
        try {
            return dataSort.getNearestFreeDate();
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении ближайшей свободной даты. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getSortedOrders(List<Comparator<Order>> comparators) {
        if (comparators == null) {
            throw new AssistantException("Список компараторов не может быть null");
        }
        try {
            return ordersSort.getSortedOrders(comparators);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при сортировке заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByMaster(Master master) {
        if (master == null) {
            throw new AssistantException("Мастер не может быть null");
        }
        try {
            return ordersSort.getOrdersByMaster(master);
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении заказов по мастеру. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getCurrentOrders() {
        try {
            return ordersSort.getCurrentOrders();
        } catch (Exception e) {
            throw new AssistantException("Ошибка при получении текущих заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        if (status == null) {
            throw new AssistantException("Статус заказа не может быть null");
        }
        try {
            return ordersSort.getOrdersByStatus(status);
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

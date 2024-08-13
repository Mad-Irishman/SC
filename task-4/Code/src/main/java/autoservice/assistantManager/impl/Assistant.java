package main.java.autoservice.assistantManager.impl;

import main.java.autoservice.assistantManager.AssistantInterface;
import main.java.autoservice.manager.impl.ServiceManager;
import main.java.autoservice.models.garage.Garage;
import main.java.autoservice.models.garage.essence.garagePlace.GaragePlace;
import main.java.autoservice.models.garage.essence.master.Master;
import main.java.autoservice.models.order.Order;
import main.java.autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Assistant implements AssistantInterface {
    private ServiceManager serviceManager;

    public List<Master> getMastersByOrders(Order order) {
        return serviceManager.getMasters().stream()
                .filter(master -> master.getOrdersMaster() != null)
                .collect(Collectors.toList());
    }

    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        return serviceManager.getMasters().stream()
                .sorted(combineComparators(comparators))
                .collect(Collectors.toList());
    }

    public List<Order> getSortedOrders(List<Comparator<Order>> comparators) {
        return serviceManager.getOrders().stream()
                .sorted(combineComparators(comparators))
                .collect(Collectors.toList());
    }

    public List<GaragePlace> getAvailableGaragePlaces() {
        return serviceManager.getGarages().stream()
                .flatMap(garage -> garage.getAvailableGaragePlaces().stream())
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByMaster(Master master) {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getAssignedMaster().equals(master))
                .collect(Collectors.toList());
    }

    public List<Order> getCurrentOrders() {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByStatusAndTimeFrame(OrderStatus status, LocalDateTime startTime, LocalDateTime endTime) {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == status &&
                        order.getCompletionDate() != null &&
                        !order.getCompletionDate().isBefore(startTime) &&
                        !order.getCompletionDate().isAfter(endTime))
                .collect(Collectors.toList());
    }

    public int getFreePlacesOnDate(LocalDateTime date) {
        List<Master> occupiedMasters = serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS || order.getStatusOrder() == OrderStatus.CREATED)
                .filter(order -> order.getSubmissionDate().isBefore(date) && order.getCompletionDate().isAfter(date))
                .map(Order::getAssignedMaster)
                .distinct()
                .collect(Collectors.toList());

        List<GaragePlace> occupiedPlaces = serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS || order.getStatusOrder() == OrderStatus.CREATED)
                .filter(order -> order.getSubmissionDate().isBefore(date) && order.getCompletionDate().isAfter(date))
                .map(Order::getAssignedGaragePlace)
                .distinct()
                .collect(Collectors.toList());

        long freeMastersCount = serviceManager.getMasters().stream()
                .filter(master -> !occupiedMasters.contains(master))
                .count();

        long freePlacesCount = serviceManager.getGarages().stream()
                .flatMap(garage -> garage.getAvailableGaragePlaces().stream())
                .filter(place -> !occupiedPlaces.contains(place))
                .count();

        return Math.min((int) freeMastersCount, (int) freePlacesCount);
    }

    public LocalDateTime getNearestFreeDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nearestFreeDate = now;

        List<LocalDateTime> occupiedDates = serviceManager.getOrders().stream()
                .flatMap(order -> List.of(order.getSubmissionDate(), order.getCompletionDate()).stream())
                .sorted()
                .collect(Collectors.toList());

        for (LocalDateTime occupiedDate : occupiedDates) {
            if (occupiedDate.isAfter(nearestFreeDate)) {
                if (isFreeAt(occupiedDate)) {
                    nearestFreeDate = occupiedDate;
                    break;
                }
            }
        }

        return nearestFreeDate.isEqual(now) ? now.plusDays(1) : nearestFreeDate;
    }

    private boolean isFreeAt(LocalDateTime dateTime) {
        boolean freeMasterExists = serviceManager.getMasters().stream()
                .noneMatch(master -> serviceManager.getOrders().stream()
                        .anyMatch(order -> order.getAssignedMaster().equals(master) &&
                                order.getSubmissionDate().isBefore(dateTime) &&
                                order.getCompletionDate().isAfter(dateTime))
                );

        boolean freePlaceExists = serviceManager.getGarages().stream()
                .flatMap(garage -> garage.getGaragePlaces().stream())
                .noneMatch(place -> serviceManager.getOrders().stream()
                        .anyMatch(order -> order.getAssignedGaragePlace().equals(place) &&
                                order.getSubmissionDate().isBefore(dateTime) &&
                                order.getCompletionDate().isAfter(dateTime))
                );

        return freeMasterExists && freePlaceExists;
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        return comparators.stream()
                .reduce(Comparator::thenComparing)
                .orElseThrow(() -> new IllegalArgumentException("Список компараторов не должен быть пустым"));
    }
}

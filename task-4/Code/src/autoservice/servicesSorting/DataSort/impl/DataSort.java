package autoservice.servicesSorting.DataSort.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.DataSortInterface;
import autoservice.servicesSorting.DataSort.exception.DataSortException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DataSort implements DataSortInterface {
    private final ServiceManager serviceManager;

    public DataSort(ServiceManager serviceManager) {
        if (serviceManager == null) {
            throw new IllegalArgumentException("ServiceManager cannot be null. ");
        }
        this.serviceManager = serviceManager;
    }

    @Override
    public int getFreePlacesOnDate(LocalDateTime date) {
        try {
            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null. ");
            }
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

            if (freeMastersCount == 0 || freePlacesCount == 0) {
                throw new DataSortException("No free masters or garage places available on the specified date. ");
            }

            return Math.min((int) freeMastersCount, (int) freePlacesCount);
        } catch (Exception e) {
            throw new DataSortException("Error calculating free places on date: " + e.getMessage());
        }
    }

    @Override
    public LocalDateTime getNearestFreeDate() {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nearestFreeDate = now;

            List<LocalDateTime> occupiedDates = serviceManager.getOrders().stream()
                    .flatMap(order -> List.of(order.getSubmissionDate(), order.getCompletionDate()).stream())
                    .sorted()
                    .collect(Collectors.toList());

            for (LocalDateTime occupiedDate : occupiedDates) {
                if (occupiedDate.isAfter(nearestFreeDate) && isFreeAt(occupiedDate)) {
                    nearestFreeDate = occupiedDate;
                    break;
                }
            }

            return nearestFreeDate.isEqual(now) ? now.plusDays(1) : nearestFreeDate;
        } catch (Exception e) {
            throw new DataSortException("Error finding nearest free date: " + e.getMessage());
        }
    }


    private boolean isFreeAt(LocalDateTime dateTime) {
        try {
            if (dateTime == null) {
                throw new IllegalArgumentException("DateTime cannot be null. ");
            }
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
        } catch (Exception e) {
            throw new DataSortException("Error checking availability at date: " + e.getMessage());
        }
    }
}

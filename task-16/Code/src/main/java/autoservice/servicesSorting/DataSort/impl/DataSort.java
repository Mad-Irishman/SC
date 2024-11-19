package autoservice.servicesSorting.DataSort.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.DataSortInterface;
import autoservice.exception.dataSortException.DataSortException;
import autoservice.service.impl.GarageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataSort implements DataSortInterface {

    private final GarageServiceImpl garageService;

    @Autowired
    public DataSort(GarageServiceImpl garageService) {
        this.garageService = garageService;
    }

    @Override
    public int getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date) {
        if (date == null) {
            throw new DataSortException("Date cannot be null.");
        }

        try {
            List<Master> occupiedMasters = orders.stream()
                    .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS || order.getStatusOrder() == OrderStatus.CREATED)
                    .filter(order -> order.getSubmissionDate().isBefore(date) && order.getCompletionDate().isAfter(date))
                    .map(Order::getAssignedMaster)
                    .distinct()
                    .collect(Collectors.toList());

            List<GaragePlace> occupiedPlaces = orders.stream()
                    .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS || order.getStatusOrder() == OrderStatus.CREATED)
                    .filter(order -> order.getSubmissionDate().isBefore(date) && order.getCompletionDate().isAfter(date))
                    .map(Order::getAssignedGaragePlace)
                    .distinct()
                    .collect(Collectors.toList());

            long freeMastersCount = masters.stream()
                    .filter(master -> !occupiedMasters.contains(master))
                    .count();

            List<GaragePlace> availablePlaces = garageService.getAvailableGaragePlaces();
            long freePlacesCount = availablePlaces.stream()
                    .filter(place -> !occupiedPlaces.contains(place))
                    .count();

            if (freeMastersCount == 0 || freePlacesCount == 0) {
                throw new DataSortException("No free masters or garage places available on the specified date.");
            }

            return Math.min((int) freeMastersCount, (int) freePlacesCount);
        } catch (Exception e) {
            throw new DataSortException("Error calculating free places on date: " + e.getMessage());
        }
    }

    @Override
    public LocalDateTime getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages) {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nearestFreeDate = now;

            List<LocalDateTime> occupiedDates = orders.stream()
                    .flatMap(order -> Stream.of(order.getSubmissionDate(), order.getCompletionDate()))
                    .sorted()
                    .collect(Collectors.toList());

            for (LocalDateTime occupiedDate : occupiedDates) {
                if (occupiedDate.isAfter(nearestFreeDate) && isFreeAt(masters, orders, occupiedDate)) {
                    nearestFreeDate = occupiedDate;
                    break;
                }
            }

            return nearestFreeDate.isEqual(now) ? now.plusDays(1) : nearestFreeDate;
        } catch (Exception e) {
            throw new DataSortException("Error finding nearest free date: " + e.getMessage());
        }
    }

    private boolean isFreeAt(List<Master> masters, List<Order> orders, LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new DataSortException("DateTime cannot be null.");
        }

        try {
            boolean freeMasterExists = masters.stream()
                    .noneMatch(master -> orders.stream()
                            .anyMatch(order -> order.getAssignedMaster().equals(master) &&
                                    order.getSubmissionDate().isBefore(dateTime) &&
                                    order.getCompletionDate().isAfter(dateTime))
                    );

            List<GaragePlace> availablePlaces = garageService.getAvailableGaragePlaces();
            boolean freePlaceExists = availablePlaces.stream()
                    .noneMatch(place -> orders.stream()
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

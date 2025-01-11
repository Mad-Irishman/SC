package autoservice.servicesSorting.DataSort;

import autoservice.models.garage.Garage;
import autoservice.models.master.Master;
import autoservice.models.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface DataSortInterface {

    int getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date);

    LocalDateTime getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages);
}

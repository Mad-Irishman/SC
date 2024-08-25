package autoservice.servicesSorting.MastersSort;

import autoservice.models.master.Master;
import autoservice.models.order.Order;

import java.util.Comparator;
import java.util.List;

public interface MastersSortInterface {

    List<Master> getMastersByOrders(List<Master> masters, Order order);

    List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators);
}

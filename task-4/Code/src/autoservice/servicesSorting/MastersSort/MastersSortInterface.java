package autoservice.servicesSorting.MastersSort;

import autoservice.models.master.Master;
import autoservice.models.order.Order;

import java.util.Comparator;
import java.util.List;

public interface MastersSortInterface {

    List<Master> getMastersByOrders(Order order);

    List<Master> getSortedMasters(List<Comparator<Master>> comparators);
}

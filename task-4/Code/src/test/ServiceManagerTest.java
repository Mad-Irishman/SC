//package test;
//
//
//import main.java.autoservice.manager.impl.ServiceManager;
//import main.java.autoservice.models.garage.Garage;
//import main.java.autoservice.models.garage.essence.GaragePlace;
//import main.java.autoservice.models.garage.essence.master.Master;
//import main.java.autoservice.models.garage.essence.master.masterStatus.MasterStatus;
//import main.java.autoservice.models.order.Order;
//import main.java.autoservice.models.order.orderStatus.OrderStatus;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class ServiceManagerTest {
//    public void testAddMaster() {
//        ServiceManager manager = new ServiceManager();
//        Master master = new Master("John Doe");
//        manager.addMaster(master);
//        assert manager.getMastersSortedByAvailabilityAndName().contains(master) : "Failed to add master.";
//    }
//
//    public void testRemoveMaster() {
//        ServiceManager manager = new ServiceManager();
//        Master master = new Master("John Doe");
//        manager.addMaster(master);
//        manager.removeMaster(master);
//        assert !manager.getMastersSortedByAvailabilityAndName().contains(master) : "Failed to remove master.";
//    }
//
//    public void testGetMastersByOrders() {
//        ServiceManager manager = new ServiceManager();
//        Master master = new Master("John Doe");
//        manager.addMaster(master);
//        Order order = new Order("Test Order", master, new GaragePlace(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        master.assingOrderMaster(order);
//        List<Master> masters = manager.getMastersByOrders(order);
//        assert masters.contains(master) : "Failed to get masters by orders.";
//    }
//
//    public void testGetMastersSortedByAvailabilityAndName() {
//        ServiceManager manager = new ServiceManager();
//        Master master1 = new Master("John Doe");
//        Master master2 = new Master("Jane Doe");
//        manager.addMaster(master1);
//        manager.addMaster(master2);
//        List<Master> sortedMasters = manager.getMastersSortedByAvailabilityAndName();
//        assert sortedMasters.get(0).getName().equals("Jane Doe") : "Failed to sort masters.";
//    }
//
//    public void testAddGaragePlace() {
//        Garage garage = new Garage();
//        GaragePlace place = new GaragePlace(1);
//        garage.addGaragePlace(place);
//        assert garage.getGaragePlaces().contains(place) : "Failed to add garage place.";
//    }
//
//    public void testRemoveGaragePlace() {
//        Garage garage = new Garage();
//        GaragePlace place = new GaragePlace(1);
//        garage.addGaragePlace(place);
//        garage.removeGaragePlace(place);
//        assert !garage.getGaragePlaces().contains(place) : "Failed to remove garage place.";
//    }
//
//    public void testGetAvailableGaragePlaces() {
//        Garage garage = new Garage();
//        GaragePlace place1 = new GaragePlace(1);
//        GaragePlace place2 = new GaragePlace(2);
//        place2.setOccupied(true);
//        garage.addGaragePlace(place1);
//        garage.addGaragePlace(place2);
//        List<GaragePlace> availablePlaces = garage.getAvailableGaragePlaces();
//        assert availablePlaces.contains(place1) && !availablePlaces.contains(place2) : "Failed to get available garage places.";
//    }
//
//    public void testCreateOrder() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        assert master.isAvailable() == MasterStatus.AVAILABLE && place.isOccupied() : "Failed to create order.";
//    }
//
//    public void testGetOrdersByMaster() {
//        ServiceManager manager = new ServiceManager();
//        Master master = new Master("John Doe");
//        manager.addMaster(master);
//        Order order = new Order("Test Order", master, new GaragePlace(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        master.assingOrderMaster(order);
//        List<Order> orders = manager.getOrdersByMaster(master);
//        assert orders.contains(order) : "Failed to get orders by master.";
//    }
//
//    public void testGetOrderById() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        Order order = manager.getOrderById(1);
//        assert order != null : "Failed to get order by ID.";
//    }
//
//    public void testRemoveOrder() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        Order order = manager.getOrderById(1);
//        manager.removeOrder(order);
//        assert manager.getOrderById(1) == null : "Failed to remove order.";
//    }
//
//    public void testCompleteOrder() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        Order order = manager.getOrderById(1);
//        manager.completeOrder(order);
//        assert order.getStatusOrder() == OrderStatus.COMPLETED : "Failed to complete order.";
//    }
//
//    public void testCancelOrder() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        Order order = manager.getOrderById(1);
//        manager.cancelOrder(order);
//        assert order.getStatusOrder() == OrderStatus.CANCELLED : "Failed to cancel order.";
//    }
//
//    public void testAdjustOrdersForDelay() {
//        ServiceManager manager = new ServiceManager();
//        Garage garage = new Garage();
//        Master master = new Master("John Doe");
//        GaragePlace place = new GaragePlace(1);
//        manager.addMaster(master);
//        garage.addGaragePlace(place);
//        manager.createOrder("Test Order", master, place, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDateTime.now(), 100.0);
//        manager.adjustOrdersForDelay(1, 2);
//        Order order = manager.getOrderById(1);
//        assert order.getCompletionDate().isAfter(order.getSubmissionDate().plusHours(1)) : "Failed to adjust orders for delay.";
//    }
//
//    public void testSortListOrders() {
//        ServiceManager manager = new ServiceManager();
//        List<Order> sortedOrders = manager.sortListOrders();
//        assert sortedOrders.size() >= 0 : "Failed to sort list orders.";
//    }
//
//
//    public void testGetOrdersByStatusAndTimeFrame() {
//        ServiceManager manager = new ServiceManager();
//        List<Order> orders = manager.getOrdersByStatusAndTimeFrame(OrderStatus.COMPLETED, LocalDateTime.now().minusDays(1), LocalDateTime.now());
//        assert orders.size() >= 0 : "Failed to get orders by status and time frame.";
//    }
//
//
//    public void testGetCurrentOrders() {
//        ServiceManager serviceManager = new ServiceManager();
//        Master master = new Master("John Doe");
//        GaragePlace garagePlace = new GaragePlace(1);
//        serviceManager.addMaster(master);
//        serviceManager.addGaragePlace(garagePlace);
//
//        Order order1 = new Order("Repair", master, garagePlace, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(1), 100);
//        Order order2 = new Order("Maintenance", master, garagePlace, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1), 150);
//        serviceManager.createOrder(order1.getDiscription(), master, garagePlace, order1.getSubmissionDate(), order1.getCompletionDate(), order1.getPlannedStartDate(), order1.getPrice());
//        serviceManager.createOrder(order2.getDiscription(), master, garagePlace, order2.getSubmissionDate(), order2.getCompletionDate(), order2.getPlannedStartDate(), order2.getPrice());
//
//        List<Order> currentOrders = serviceManager.getCurrentOrders();
//        assert currentOrders.contains(order1) : "Test failed: Order should be in current orders.";
//        assert !currentOrders.contains(order2) : "Test failed: Order should not be in current orders.";
//        System.out.println("GetCurrentOrdersTest passed.");
//    }
//
//    public void testGetSortedCurrentOrders() {
//        ServiceManager serviceManager = new ServiceManager();
//        Master master = new Master("John Doe");
//        GaragePlace garagePlace = new GaragePlace(1);
//        serviceManager.addMaster(master);
//        serviceManager.addGaragePlace(garagePlace);
//
//        Order order1 = new Order("Repair A", master, garagePlace, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(1), 100);
//        Order order2 = new Order("Repair B", master, garagePlace, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1), 150);
//        serviceManager.createOrder(order1.getDiscription(), master, garagePlace, order1.getSubmissionDate(), order1.getCompletionDate(), order1.getPlannedStartDate(), order1.getPrice());
//        serviceManager.createOrder(order2.getDiscription(), master, garagePlace, order2.getSubmissionDate(), order2.getCompletionDate(), order2.getPlannedStartDate(), order2.getPrice());
//
//        List<Order> sortedCurrentOrders = serviceManager.getSortedCurrentOrders();
//        assert sortedCurrentOrders.get(0).equals(order1) : "Test failed: Current orders not sorted correctly.";
//        assert sortedCurrentOrders.get(1).equals(order2) : "Test failed: Current orders not sorted correctly.";
//        System.out.println("GetSortedCurrentOrdersTest passed.");
//    }
//
//    public void testGetNearestFreeDate() {
//        ServiceManager serviceManager = new ServiceManager();
//        GaragePlace garagePlace = new GaragePlace(1);
//        serviceManager.addGaragePlace(garagePlace);
//
//        LocalDateTime nearestFreeDate = serviceManager.getNearestFreeDate();
//        assert nearestFreeDate != null : "Test failed: Nearest free date not found.";
//        System.out.println("testGetNearestFreeDate passed.");
//    }
//
//    public void testShowAllOrders() {
//        ServiceManager manager = new ServiceManager();
//        manager.showAllOrders();
//        assert true : "Failed to show all orders.";
//    }
//
//    public void testShowAvailableMasters() {
//        ServiceManager manager = new ServiceManager();
//        manager.showAvailableMasters();
//        assert true : "Failed to show available masters.";
//    }
//
//    public void testShowAvailableGaragePlaces() {
//        ServiceManager manager = new ServiceManager();
//        manager.showAvailableGaragePlaces();
//        assert true : "Failed to show available garage places.";
//    }
//}

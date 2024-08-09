package main.java.autoservice;

import test.ServiceManagerTest;

public class Main {
    public static void main(String[] args) {
        ServiceManagerTest test = new ServiceManagerTest();
        test.testAddMaster();
        test.testRemoveMaster();
        test.testGetMastersByOrders();
        test.testGetMastersSortedByAvailabilityAndName();
        test.testAddGaragePlace();
        test.testRemoveGaragePlace();
        test.testGetAvailableGaragePlaces();
        test.testCreateOrder();
        test.testGetOrdersByMaster();
        test.testGetOrderById();
        test.testRemoveOrder();
        test.testCompleteOrder();
        test.testCancelOrder();
        test.testAdjustOrdersForDelay();
        test.testSortListOrders();
        test.testGetCurrentOrders();
        test.testGetSortedCurrentOrders();
        test.testGetOrdersByStatusAndTimeFrame();
        test.testGetNearestFreeDate();
        test.testShowAllOrders();
        test.testShowAvailableMasters();
        test.testShowAvailableGaragePlaces();
    }
}

//package autoservice.ui.actions.impl.orderAction;
//
//import autoservice.manager.impl.ServiceManager;
//import autoservice.exception.managerException.ServiceManagerException;
//import autoservice.models.order.Order;
//import autoservice.ui.actions.IAction;
//
//import java.util.List;
//
//public class GetCurentOrdersAction implements IAction {
//    private final ServiceManager serviceManager;
//
//    public GetCurentOrdersAction(ServiceManager serviceManager) {
//        this.serviceManager = serviceManager;
//    }
//
//    @Override
//    public void execute() {
//        try {
//            List<Order> allOrders = serviceManager.getOrders();
//            List<Order> currentOrders = serviceManager.getCurrentOrders(allOrders);
//
//            if (currentOrders.isEmpty()) {
//                System.out.println("No current orders.");
//            } else {
//                System.out.println("Current orders:");
//                for (Order order : currentOrders) {
//                    System.out.println(" - Order ID: " + order.getIdOrder() +
//                            ", Submission Date: " + order.getSubmissionDate() +
//                            ", Status: " + order.getStatusOrder() +
//                            ", Price: " + order.getPrice());
//                }
//            }
//
//        } catch (ServiceManagerException e) {
//            System.out.println("Error retrieving orders from service: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Unexpected error: " + e.getMessage());
//        }
//    }
//}

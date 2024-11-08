import autoservice.DI.DIContainer;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.controller.MenuController;
import autoservice.utils.deserialization.Deserializer;
import autoservice.utils.serialization.Serializer;

public class    Main {

    private static ServiceManager serviceManager;

    public static void main(String[] args) {
        DIContainer diContainer = new DIContainer();
        serviceManager = diContainer.getInstance(ServiceManager.class);

//        ServiceManager deserializedServiceManager = deserializeServiceManager();
//        if (deserializedServiceManager != null) {
//            updateServiceManagerState(serviceManager, deserializedServiceManager);
//            System.out.println("ServiceManager успешно загружен.");
//        } else {
//            System.out.println("Данные не найдены, используется новый ServiceManager.");
//        }

        MenuController controller = diContainer.getInstance(MenuController.class);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            serializeServiceManager(serviceManager);
//            System.out.println("ServiceManager сериализован перед завершением программы.");
//        }));

        controller.run();
    }

//    private static void serializeServiceManager(ServiceManager serviceManager) {
//        Serializer.serializeToFile(serviceManager);
//    }
//
//    private static ServiceManager deserializeServiceManager() {
//        return Deserializer.deserializeFromFile(ServiceManager.class);
//    }
//
//    private static void updateServiceManagerState(ServiceManager original, ServiceManager deserialized) {
//        original.setMasters(deserialized.getMasters());
//        original.setGarage(deserialized.getGarage());
//        original.setOrders(deserialized.getOrders());
//    }
}

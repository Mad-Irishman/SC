import autoservice.DI.AppConfig;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.controller.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ServiceManager serviceManager = context.getBean(ServiceManager.class);
        MenuController menuController = new MenuController(serviceManager);
        menuController.run();




//        DIContainer diContainer = new DIContainer();
//        serviceManager = diContainer.getInstance(ServiceManager.class);

//        ServiceManager deserializedServiceManager = deserializeServiceManager();
//        if (deserializedServiceManager != null) {
//            updateServiceManagerState(serviceManager, deserializedServiceManager);
//            System.out.println("ServiceManager успешно загружен.");
//        } else {
//            System.out.println("Данные не найдены, используется новый ServiceManager.");
//        }

//        MenuController controller = diContainer.getInstance(MenuController.class);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            serializeServiceManager(serviceManager);
//            System.out.println("ServiceManager сериализован перед завершением программы.");
//        }));

//        controller.run();
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

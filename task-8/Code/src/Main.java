import autoservice.DI.DIContainer;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.controller.MenuController;
import autoservice.utils.deserialization.Deserializer;
import autoservice.utils.serialization.Serializer;

public class Main {

    private static DIContainer diContainer;
    private static ServiceManager serviceManager;

    public static void main(String[] args) {
        diContainer = new DIContainer();
        serviceManager = deserializeServiceManager();
        if (serviceManager == null) {
            serviceManager = diContainer.getInstance(ServiceManager.class);
            System.out.println("Данные не найдены, создан новый ServiceManager.");
        } else {
            System.out.println("ServiceManager успешно загружен.");
        }
        MenuController controller = diContainer.getInstance(MenuController.class);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            serializeServiceManager(serviceManager);
            System.out.println("ServiceManager сериализован перед завершением программы.");
        }));
        controller.run();
    }
    private static void serializeServiceManager(ServiceManager serviceManager) {
        Serializer.serializeToFile(serviceManager);
    }
    private static ServiceManager deserializeServiceManager() {
        return Deserializer.deserializeFromFile(ServiceManager.class);
    }
}

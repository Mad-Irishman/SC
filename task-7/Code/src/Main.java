import autoservice.manager.impl.ServiceManager;
import autoservice.ui.controller.MenuController;
import autoservice.utils.deserialization.Deserializer;
import autoservice.utils.serialization.Serializer;


public class Main {

    private static ServiceManager serviceManager;

    public static void main(String[] args) {
        serviceManager = deserializeServiceManager();

        if (serviceManager == null) {
            serviceManager = new ServiceManager();
            System.out.println("Данные не найдены, создан новый ServiceManager.");
        } else {
            System.out.println("ServiceManager успешно загружен.");
        }

        MenuController controller = new MenuController(serviceManager);

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
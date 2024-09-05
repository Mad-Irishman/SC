import autoservice.manager.impl.ServiceManager;
import ui.controller.MenuController;

public class Main {
    public static void main(String[] args) {
        ServiceManager serviceManager = new ServiceManager();
        MenuController controller = new MenuController(serviceManager);
        controller.run();
    }
}
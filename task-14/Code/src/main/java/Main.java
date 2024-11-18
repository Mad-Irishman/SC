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
    }
}

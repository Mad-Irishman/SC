import autoservice.manager.impl.ServiceManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServiceManager serviceManager = context.getBean("serviceManager", ServiceManager.class);
    }
}

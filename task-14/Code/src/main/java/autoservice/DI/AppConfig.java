package autoservice.DI;

import autoservice.assistantManager.impl.Assistant;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.garage.Garage;
import autoservice.servicesSorting.DataSort.impl.DataSort;
import autoservice.servicesSorting.GaragePlacesSort.impl.GaragePlacesSort;
import autoservice.servicesSorting.MastersSort.impl.MastersSort;
import autoservice.servicesSorting.OrdersSort.impl.OrdersSort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "autoservice")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public Garage garage() {
        return new Garage();
    }

    @Bean
    public MastersSort mastersSort() {
        return new MastersSort();
    }

    @Bean
    public GaragePlacesSort garagePlacesSort() {
        return new GaragePlacesSort();
    }

    @Bean
    public DataSort dataSort() {
        return new DataSort();
    }

    @Bean
    public OrdersSort ordersSort() {
        return new OrdersSort();
    }

    @Bean
    public Assistant assistant() {
        return new Assistant(mastersSort(), garagePlacesSort(), dataSort(), ordersSort());
    }

    @Bean
    public ServiceManager serviceManager() {
        return new ServiceManager(garage(), assistant());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}

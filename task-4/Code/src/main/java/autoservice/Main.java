package main.java.autoservice;


import main.java.autoservice.manager.impl.ServiceManager;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ServiceManager manager = new ServiceManager();
        manager.createOrder("Change Oil", LocalDateTime.now(), LocalDateTime.of(2024, 8, 14, 10, 30), LocalDateTime.now(), 500.00);

    }
}

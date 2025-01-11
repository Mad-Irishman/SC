package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final ServiceManager serviceManager;

    @Autowired
    public UserController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return serviceManager.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return serviceManager.verifyUser(user);
    }
}

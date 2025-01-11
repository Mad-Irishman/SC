package autoservice.service;

import autoservice.models.user.User;
import autoservice.repository.UserRepository;

public interface UserService {
    UserRepository getUserRepository();

    User register(User user);

    String verifyUser(User user);

}

package autoservice.repository;

import autoservice.models.user.User;

import java.util.List;


public interface UserRepository {
    User findByName(String email);
    User saveUser(User user);
}

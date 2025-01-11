package autoservice.service.impl;

import autoservice.models.user.User;
import autoservice.models.user.UserPrincipal;
import autoservice.repository.UserRepository;
import autoservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceImpl jwtService;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder , JWTServiceImpl jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder  = passwordEncoder ;
        this.jwtService = jwtService;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User register(User user) {
        user.setHash_password(passwordEncoder.encode(user.getHash_password()));
        return userRepository.saveUser(user);
    }

    @Override
    public String verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getHash_password())
        );

        if (authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            return jwtService.generateToken(principal.getUsername()); // Генерация токена с именем пользователя
        }

        throw new RuntimeException("Authentication failed");
    }
}

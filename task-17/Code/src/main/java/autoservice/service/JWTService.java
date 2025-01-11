package autoservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(String username);

    Claims parseToken(String token);

}

package autoservice.models.user;

import autoservice.models.user.userRole.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "user_name", unique = false, nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "hash_password", unique = false, nullable = false)
    private String hash_password;
    @Column(name = "role", unique = false, nullable = true)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String name, String hash_password, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.hash_password = hash_password;
        this.role = Role.USER;
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}

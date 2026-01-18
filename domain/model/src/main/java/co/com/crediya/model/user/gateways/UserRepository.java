package co.com.crediya.model.user.gateways;

import java.util.Optional;
import java.util.UUID;

import co.com.crediya.model.user.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    Optional<User> softDeleteById(UUID id);
}

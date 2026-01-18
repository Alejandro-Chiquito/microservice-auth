package co.com.crediya.usecase.user;

import java.util.UUID;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.usecase.exception.UserNotFoundException;
import co.com.crediya.usecase.exception.ValidationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    /**
     * Creates a new user after validating business rules.
     * 
     * @param user the user to create
     * @return the saved user
     * @throws ValidationException if validation fails
     */
    public User createUser(User user) {
        userValidator.validateForCreation(user);
        return userRepository.save(user);
    }

    /**
     * Finds a user by ID.
     * 
     * @param id the ID of the user to find
     * @return the found user
     * @throws ValidationException if the ID is null or empty
     */
    public User findById(UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ValidationException("El id del usuario es requerido");
        }
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Finds a user by email.
     * 
     * @param email the email of the user to find
     * @return the found user
     * @throws ValidationException if the email is null or empty
     */
    public User findByEmail(String email) {
        userValidator.validateEmail(email);
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Updates an existing user after validating business rules.
     * 
     * @param user the user to update
     * @return the updated user
     * @throws ValidationException if validation fails
     */
    public User updateUser(User user) {
        userValidator.validateForUpdate(user);
        return userRepository.save(user);
    }

    /**
     * Soft deletes a user by ID.
     * 
     * @param id the ID of the user to delete
     * @return the deleted user
     * @throws ValidationException if the ID is null or empty
     */
    public User deleteUserById(UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ValidationException("El id del usuario es requerido");
        }
        return userRepository.softDeleteById(id).orElseThrow(UserNotFoundException::new);
    }
}

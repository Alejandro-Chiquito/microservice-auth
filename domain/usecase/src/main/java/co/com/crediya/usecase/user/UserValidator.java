package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.usecase.exception.ValidationException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final int MINIMUM_AGE = 18;
    private static final int MINIMUM_PASSWORD_LENGTH = 8;
    private static final double MINIMUM_SALARY = 0.0;
    private static final double MAXIMUM_SALARY = 15000000.0; // 15 million

    private final UserRepository userRepository;

    /**
     * Validates a user before creation.
     * 
     * @param user the user to validate
     * @throws ValidationException if validation fails
     */
    public void validateForCreation(User user) {
        validateRequiredFields(user);
        validateEmail(user.getEmail());
        validateEmailUniqueness(user.getEmail());
        validateAge(user.getBirthDate());
        validatePassword(user.getPassword());
        validatePhone(user.getPhone());
    }

    /**
     * Validates a user before update.
     * 
     * @param user the user to validate
     * @throws ValidationException if validation fails
     */
    public void validateForUpdate(User user) {
        validateRequiredFields(user);
        validateEmail(user.getEmail());
        validateAge(user.getBirthDate());
        validatePassword(user.getPassword());
        validatePhone(user.getPhone());
    }

    /**
     * Validates that all required fields are present.
     * Last names are not required since some people only have one name.
     * 
     * @param user the user to validate
     * @throws ValidationException if validation fails
     */
    private void validateRequiredFields(User user) {
        if (user == null) {
            throw new ValidationException("El usuario no puede ser nulo");
        }

        if (isNullOrEmpty(user.getFirstNames())) {
            throw new ValidationException("Los nombres son requeridos");
        }

        if (user.getBaseSalary() < MINIMUM_SALARY || user.getBaseSalary() > MAXIMUM_SALARY) {
            throw new ValidationException("El salario debe estar entre " + MINIMUM_SALARY + " y " + MAXIMUM_SALARY);
        }

        if (user.getBirthDate() == null) {
            throw new ValidationException("La fecha de nacimiento es requerida");
        }

        if (isNullOrEmpty(user.getEmail())) {
            throw new ValidationException("El correo electrónico es requerido");
        }

        if (isNullOrEmpty(user.getPassword())) {
            throw new ValidationException("La contraseña es requerida");
        }

        if (user.getRole() == null) {
            throw new ValidationException("El rol es requerido");
        }
    }

    /**
     * Validates email format.
     */
    protected void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Formato de correo electrónico inválido");
        }
    }

    /**
     * Validates that the email is not already registered.
     */
    private void validateEmailUniqueness(String email) {
        userRepository.findByEmail(email).ifPresent(existingUser -> {
            throw new ValidationException("El correo electrónico ya está registrado: " + email);
        });
    }

    /**
     * Validates that the user is at least 18 years old.
     */
    private void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new ValidationException("La fecha de nacimiento es requerida");
        }

        LocalDate now = LocalDate.now();

        if (birthDate.isAfter(now)) {
            throw new ValidationException("La fecha de nacimiento no puede estar en el futuro");
        }

        int age = Period.between(birthDate, now).getYears();

        if (age < MINIMUM_AGE) {
            throw new ValidationException(
                    String.format("El usuario debe tener al menos %d años de edad", MINIMUM_AGE));
        }
    }

    /**
     * Validates password strength.
     */
    private void validatePassword(String password) {
        if (password == null || password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new ValidationException(
                    String.format("La contraseña debe tener al menos %d caracteres", MINIMUM_PASSWORD_LENGTH));
        }

        // Validate password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra mayúscula");
        }

        // Validate password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra minúscula");
        }

        // Validate password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            throw new ValidationException("La contraseña debe contener al menos un número");
        }

        // Validate password contains at least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new ValidationException("La contraseña debe contener al menos un carácter especial");
        }
    }

    /**
     * Validates phone number format
     */
    private void validatePhone(String phone) {
        // Colombian phone format: 10 digits (3XX XXX XXXX)
        if (!phone.matches("^\\d{10}$")) {
            throw new ValidationException("El número de teléfono debe tener 10 dígitos");
        }
    }

    /**
     * Helper method to check if a string is null or empty.
     */
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}

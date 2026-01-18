package co.com.crediya.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import co.com.crediya.model.direction.Direction;
import co.com.crediya.model.role.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Builder.Default private UUID id = UUID.randomUUID();
    private String firstNames;
    private String lastNames;
    private LocalDate birthDate;
    private Direction direction;
    private String phone;
    private String email;
    private Double baseSalary;
    private String password;
    private Role role;
    @Builder.Default private Boolean isActive = true;
}

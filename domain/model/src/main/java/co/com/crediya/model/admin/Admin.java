package co.com.crediya.model.admin;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Admin {
    @Builder.Default private UUID id = UUID.randomUUID();
    private String[] firstNames;
    private String[] lastNames;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String password;
}

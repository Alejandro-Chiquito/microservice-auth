package co.com.crediya.model.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Role {
    private Long id;
    private RoleType roleType;
    private String description;

    public boolean isAdmin() {
        return roleType != null && roleType.isAdmin();
    }

    public boolean isClient() {
        return roleType != null && roleType.isClient();
    }

    public String getDisplayName() {
        return roleType != null ? roleType.getDisplayName() : "";
    }
}
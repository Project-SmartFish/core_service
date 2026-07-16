package smartfish.modules.auth.infrastructure.custom;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import smartfish.modules.user.domain.entity.AccessLevel;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomUserDetails implements UserDetails {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private AccessLevel accessLevel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(accessLevel);
    }

    @Override
    public String getUsername() {
        return email;
    }
}

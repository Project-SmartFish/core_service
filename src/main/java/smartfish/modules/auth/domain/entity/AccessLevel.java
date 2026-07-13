package smartfish.modules.auth.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum AccessLevel implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {

        return "ROLE_" + name();
    }
}
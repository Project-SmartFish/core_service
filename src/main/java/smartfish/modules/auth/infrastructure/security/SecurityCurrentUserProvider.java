package smartfish.modules.auth.infrastructure.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import smartfish.modules.auth.application.security.CurrentUserProvider;
import smartfish.modules.auth.infrastructure.custom.CustomUserDetails;


@Component
public class SecurityCurrentUserProvider implements CurrentUserProvider {

    @Override
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new SecurityException("Não existe um usuário autenticado");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            throw new SecurityException("Principal autenticado incorretamente");
        }

        if (userDetails.getEmail() == null) {
            throw new SecurityException("Usuário autenticado não possui um identificador email");
        }

        return userDetails.getEmail();
    }
}

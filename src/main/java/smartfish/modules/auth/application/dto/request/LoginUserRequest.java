package smartfish.modules.auth.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "O email não pode ser vazio")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        String password
) {}

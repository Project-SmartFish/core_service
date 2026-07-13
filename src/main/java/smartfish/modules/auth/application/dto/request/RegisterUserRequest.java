package smartfish.modules.auth.application.dto.request;

import jakarta.validation.constraints.*;

public record RegisterUserRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100,
                message = "O nome deve possuir entre 3 e 100 caracteres.")
        String name,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail informado é inválido.")
        @Size(max = 255,
                message = "O e-mail deve possuir no máximo 255 caracteres.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 255, message = "A senha deve possuir entre 8 e 255 caracteres.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
                message = "A senha deve conter pelo menos uma letra e um número."
        )
        String password
) {}

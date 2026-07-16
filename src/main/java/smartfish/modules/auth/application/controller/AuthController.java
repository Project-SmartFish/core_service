package smartfish.modules.auth.application.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartfish.modules.auth.application.dto.request.LoginUserRequest;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.auth.application.service.AuthService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        UUID id = authService.register(request);

        return ResponseEntity
                .created(URI.create("/users/" + id.toString()))
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequest request) {
        String token = authService.login(request);

        return ResponseEntity.ok(token);
    }
}

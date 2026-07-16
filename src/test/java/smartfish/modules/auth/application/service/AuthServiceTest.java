package smartfish.modules.auth.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenProvider;
import smartfish.modules.user.application.mapper.UserMapper;
import smartfish.modules.user.domain.entity.AccessLevel;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Deve registrar usuario com sucesso")
    public void shouldRegisterUserSuccessfully() {
        // Arrange
        UUID id = UUID.randomUUID();
        String encodedPassword = "senhaHashSuperSecreta12346@Aa";
        RegisterUserRequest request = new RegisterUserRequest("Joao Capivara", "joaocapivara@gmail.com", "123456@Aa");
        UserEntity user = new UserEntity(
                null,
                request.name(),
                request.email(),
                request.password(),
                AccessLevel.USER
        );

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode(request.password())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(id);

        // Act
        UUID returnedId = authService.register(request);

        // Assert
        verify(userRepository).findByEmail(request.email());
        verify(userRepository).save(user);
        assertThat(returnedId).isEqualTo(id);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }
}

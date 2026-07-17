package smartfish.modules.auth.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import smartfish.modules.auth.application.dto.request.LoginUserRequest;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.auth.infrastructure.custom.CustomUserDetails;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenProvider;
import smartfish.modules.auth.infrastructure.jwt.TokenIssuer;
import smartfish.modules.user.application.exception.EmailAlreadyUsedException;
import smartfish.modules.user.application.mapper.UserMapper;
import smartfish.modules.user.domain.entity.AccessLevel;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

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
    @DisplayName(
            """
            Deve registrar usuario com sucesso
            quando todos os dados estiverem corretos
            """
    )
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

    @Test
    @DisplayName(
            """
            Deve lançar a exceção EmailAlreadyUsedException
            quando o email do cadastro já tiver sido utilizado
            """
    )
    public void shouldThrowEmailAlreadyUsedException() {
        // Arrange
        RegisterUserRequest request = new RegisterUserRequest("Joao Capivara", "joaocapivara@gmail.com", "123456@Aa");
        UserEntity user = new UserEntity(
                null,
                request.name(),
                request.email(),
                request.password(),
                AccessLevel.USER
        );

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));

        // Act + Assert
        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(EmailAlreadyUsedException.class)
                .hasMessage("O email já foi utilizado");

        verify(userMapper, never()).toEntity(request);
        verify(passwordEncoder, never()).encode(request.password());
        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName(
            """
            Deve realizar o login com sucesso
            quando os dados estiverem válidos
            """)
    public void shouldLoginSuccessfully() {
        // Arrange
        String randomToken = "we8rsdf87wer87sd8f7wer";

        LoginUserRequest request = new LoginUserRequest(
                "adryelsapelli@gmail.com",
                "123456@Aa"
        );

        CustomUserDetails  userDetails = new CustomUserDetails(
                UUID.randomUUID(),
                "Adryel",
                "adryelsapelli@gmail.com",
                null,
                AccessLevel.USER
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(jwtTokenProvider.generateToken(userDetails.getUsername(), TokenIssuer.AUTH)).thenReturn(randomToken);

        // Act
        String returnedToken = authService.login(request);

        // Assert
        verify(authenticationManager).authenticate(any(Authentication.class));
        verify(jwtTokenProvider).generateToken(userDetails.getUsername(), TokenIssuer.AUTH);
        assertThat(returnedToken).isEqualTo(randomToken);
    }

    @Test
    @DisplayName(
            """
            Deve propagar a excecao BadCredentialsException vinda
            do authenticationManager quando credenciais estiverem invalidas
            """
    )
    public void shouldPropagateBadCredentialsException() {
        // Arrange
        LoginUserRequest request = new LoginUserRequest(
                "adryelsapelli@gmail.com",
                "123456@Aa"
        );

        when(authenticationManager.authenticate(any(Authentication.class))).thenThrow(new BadCredentialsException("Login inválido"));

        // Act + Assert
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BadCredentialsException.class);

        verify(authenticationManager).authenticate(any(Authentication.class));
        verify(jwtTokenProvider, never()).generateToken(anyString(), any(TokenIssuer.class));
    }
}

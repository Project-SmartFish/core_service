package smartfish.modules.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smartfish.modules.auth.application.dto.request.LoginUserRequest;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.auth.infrastructure.custom.CustomUserDetails;
import smartfish.modules.auth.infrastructure.jwt.TokenIssuer;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenProvider;
import smartfish.modules.user.application.exception.EmailAlreadyUsedException;
import smartfish.modules.user.application.mapper.UserMapper;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UUID register(RegisterUserRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.email());

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

    public String login(LoginUserRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authentication = authenticationManager.authenticate(authentication);

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return jwtTokenProvider.generateToken(user.getEmail(), TokenIssuer.AUTH);
    }
}

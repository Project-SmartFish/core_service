package smartfish.modules.auth.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smartfish.modules.auth.application.dto.request.LoginUserRequest;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.user.application.exception.EmailAlreadyUsedException;
import smartfish.modules.user.application.mapper.UserMapper;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private UserMapper userMapper;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public UUID register(RegisterUserRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.email());

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

    public void login(LoginUserRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authenticationManager.authenticate(authentication);
    }
}

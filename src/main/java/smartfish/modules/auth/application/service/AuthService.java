package smartfish.modules.auth.application.service;

import org.springframework.stereotype.Service;
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

    public AuthService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UUID register(RegisterUserRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.email());

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = userMapper.toEntity(request);

        return userRepository.save(user);
    }
}

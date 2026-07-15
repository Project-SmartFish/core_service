package smartfish.modules.user.application.mapper;

import org.springframework.stereotype.Component;
import smartfish.modules.auth.application.dto.request.RegisterUserRequest;
import smartfish.modules.user.domain.entity.AccessLevel;
import smartfish.modules.user.domain.entity.UserEntity;


@Component
public class UserMapper {
    public UserEntity toEntity(RegisterUserRequest request) {

        return UserEntity.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .accessLevel(AccessLevel.USER)
                .build();
    }
}

package smartfish.modules.user.domain.repository;

import smartfish.modules.user.domain.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UUID save(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
}

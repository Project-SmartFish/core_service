package smartfish.modules.user.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import smartfish.modules.user.domain.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface IUserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}

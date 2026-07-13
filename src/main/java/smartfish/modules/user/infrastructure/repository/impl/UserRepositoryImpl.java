package smartfish.modules.user.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;
import smartfish.modules.user.infrastructure.repository.jpa.IUserJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private IUserJpaRepository jpaRepository;

    public UserRepositoryImpl(IUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UUID save(UserEntity user) {
        jpaRepository.save(user);

        return user.getId();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {

        return jpaRepository.findByEmail(email);
    }
}

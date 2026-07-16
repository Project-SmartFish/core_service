package smartfish.modules.user_fishing_event.infrastruture.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smartfish.modules.user_fishing_event.domain.core.UserFishingEventEntity;
import smartfish.modules.user_fishing_event.domain.repository.UserFishingEventRepository;
import smartfish.modules.user_fishing_event.infrastruture.repository.jpa.IUserFishingEventJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserFishingEventRepositoryImpl implements UserFishingEventRepository {

    private final IUserFishingEventJpaRepository repository;

    @Override
    public UserFishingEventEntity save(UserFishingEventEntity entity) {
        return repository.save(entity);
    }

    @Override
    public boolean existsByUser_IdAndFishingEvent_Id(UUID userId,
                                                     UUID fishingEventId) {
        return repository.existsByUser_IdAndFishingEvent_Id(
                userId,
                fishingEventId);
    }

    @Override
    public Optional<UserFishingEventEntity> findByUser_IdAndFishingEvent_Id(
            UUID userId,
            UUID fishingEventId) {
        return repository.findByUser_IdAndFishingEvent_Id(
                userId,
                fishingEventId);
    }

    @Override
    public List<UserFishingEventEntity> findByUser_Id(UUID userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public List<UserFishingEventEntity> findByFishingEvent_Id(UUID fishingEventId) {
        return repository.findByFishingEvent_Id(fishingEventId);
    }

    @Override
    public long countByFishingEvent_Id(UUID fishingEventId) {
        return repository.countByFishingEvent_Id(fishingEventId);
    }
}

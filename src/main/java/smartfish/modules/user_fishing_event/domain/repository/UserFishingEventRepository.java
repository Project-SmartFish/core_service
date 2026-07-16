package smartfish.modules.user_fishing_event.domain.repository;

import smartfish.modules.user_fishing_event.domain.core.UserFishingEventEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFishingEventRepository {

    UserFishingEventEntity save(UserFishingEventEntity entity);

    boolean existsByUser_IdAndFishingEvent_Id(
            UUID userId,
            UUID fishingEventId
    );

    Optional<UserFishingEventEntity> findByUser_IdAndFishingEvent_Id(
            UUID userId,
            UUID fishingEventId
    );

    List<UserFishingEventEntity> findByUser_Id(UUID userId);

    List<UserFishingEventEntity> findByFishingEvent_Id(UUID fishingEventId);

    long countByFishingEvent_Id(UUID fishingEventId);
}

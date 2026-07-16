package smartfish.modules.user_fishing_event.infrastruture.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import smartfish.modules.user_fishing_event.domain.core.UserFishingEventEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserFishingEventJpaRepository extends JpaRepository<UserFishingEventEntity, UUID> {

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

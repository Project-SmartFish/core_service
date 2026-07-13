package smartfish.modules.fishing_event.domain.repository;

import smartfish.modules.fishing_event.domain.core.FishingEventEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishingEventRepository {

    FishingEventEntity save(FishingEventEntity entity);

    Optional<FishingEventEntity> findById(UUID id);

    List<FishingEventEntity> findAll();

    void delete(FishingEventEntity entity);
}

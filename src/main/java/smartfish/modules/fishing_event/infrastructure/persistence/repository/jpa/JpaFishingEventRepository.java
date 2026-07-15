package smartfish.modules.fishing_event.infrastructure.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;

import java.util.List;
import java.util.UUID;

public interface JpaFishingEventRepository extends JpaRepository<FishingEventEntity, UUID> {

    public List<FishingEventEntity> findAllByFishSpotId(UUID id);
}

package smartfish.modules.fish_spot.infrastructure.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import smartfish.modules.fish_spot.domain.FishSpotEntity;

import java.util.UUID;

public interface IJpaFishSpotRepository extends JpaRepository<FishSpotEntity, UUID> {
}
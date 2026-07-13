package smartfish.modules.fish_spot.domain.repository;

import smartfish.modules.fish_spot.domain.FishSpotEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishSpotRepository {

    FishSpotEntity save(FishSpotEntity fishSpot);

    Optional<FishSpotEntity> findById(UUID id);

    List<FishSpotEntity> findAll();

    void delete(FishSpotEntity fishSpot);
}
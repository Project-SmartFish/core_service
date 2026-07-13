package smartfish.modules.fish_spot.infrastructure.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smartfish.modules.fish_spot.domain.FishSpotEntity;
import smartfish.modules.fish_spot.domain.repository.FishSpotRepository;
import smartfish.modules.fish_spot.infrastructure.persistence.repository.jpa.IJpaFishSpotRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FishSpotRepositoryImpl implements FishSpotRepository {

    private final IJpaFishSpotRepository repository;

    @Override
    public FishSpotEntity save(FishSpotEntity fishSpot) {
        return repository.save(fishSpot);
    }

    @Override
    public Optional<FishSpotEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<FishSpotEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(FishSpotEntity fishSpot) {
        repository.delete(fishSpot);
    }
}
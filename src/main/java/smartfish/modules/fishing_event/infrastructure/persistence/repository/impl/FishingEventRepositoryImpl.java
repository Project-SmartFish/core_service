package smartfish.modules.fishing_event.infrastructure.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.fishing_event.domain.repository.FishingEventRepository;
import smartfish.modules.fishing_event.infrastructure.persistence.repository.jpa.JpaFishingEventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FishingEventRepositoryImpl implements FishingEventRepository {

    private final JpaFishingEventRepository repository;

    @Override
    public FishingEventEntity save(FishingEventEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<FishingEventEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<FishingEventEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(FishingEventEntity entity) {
        repository.delete(entity);
    }
}

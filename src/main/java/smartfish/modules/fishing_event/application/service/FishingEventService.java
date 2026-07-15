package smartfish.modules.fishing_event.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartfish.modules.fishing_event.application.dto.request.create.CreateFishingEventRequest;
import smartfish.modules.fishing_event.application.dto.request.update.UpdateFishingEventRequest;
import smartfish.modules.fishing_event.application.dto.response.create.CreateFishingEventResponse;
import smartfish.modules.fishing_event.application.dto.response.read.FishingEventResponse;
import smartfish.modules.fishing_event.application.dto.response.update.UpdateFishingEventResponse;
import smartfish.modules.fishing_event.application.exception.FishingEventNotFoundException;
import smartfish.modules.fishing_event.application.mapper.FishingEventMapper;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.fishing_event.domain.repository.FishingEventRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishingEventService {

    private final FishingEventRepository fishingEventRepository;

    private final FishingEventMapper fishingEventMapper;

    public CreateFishingEventResponse createFishingEvent(CreateFishingEventRequest request) {
        FishingEventEntity fishingEvent =
                fishingEventMapper.toEntity(request);

        fishingEventRepository.save(fishingEvent);

        return fishingEventMapper.toCreateResponse(fishingEvent);
    }

    public FishingEventResponse findById(UUID id) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(id)
                        .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado."));

        return fishingEventMapper.toResponse(fishingEvent);
    }

    public List<FishingEventResponse> findAll() {
        List<FishingEventEntity> fishingEventEntities = fishingEventRepository.findAll();

        return fishingEventEntities.stream()
                .map(fishingEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<FishingEventResponse> findAllByFishSpotId(UUID fishSpotId) {
        List<FishingEventEntity> fishingEventEntities = fishingEventRepository
                .findAllByFishSpotId(fishSpotId);

        return fishingEventEntities.stream()
                .map(fishingEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UpdateFishingEventResponse update(UpdateFishingEventRequest request) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(request.id())
                        .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado"));

        FishingEventEntity fishingEventUpdate = fishingEventMapper.toEntity(request);

        fishingEvent.update(
                fishingEventUpdate.getFishSpot(),
                fishingEventUpdate.getEventDate(),
                fishingEventUpdate.getEventTime()
        );

        fishingEventRepository.save(fishingEvent);

        return fishingEventMapper.toUpdateResponse(fishingEvent);
    }

    @Transactional
    public UpdateFishingEventResponse finishEvent(UUID id) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(id)
                .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado."));

        fishingEvent.finishEvent();

        fishingEventRepository.save(fishingEvent);

        return fishingEventMapper.toUpdateResponse(fishingEvent);
    }

    @Transactional
    public UpdateFishingEventResponse cancelEvent(UUID id) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(id)
                .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado."));

        fishingEvent.cancelEvent();

        fishingEventRepository.save(fishingEvent);

        return fishingEventMapper.toUpdateResponse(fishingEvent);
    }

    public void delete(UUID id) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(id)
                .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado."));

        fishingEventRepository.delete(fishingEvent);
    }
}

package smartfish.modules.fishing_event.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartfish.modules.fishing_event.application.dto.request.create.CreateFishingEventRequest;
import smartfish.modules.fishing_event.application.dto.request.update.UpdateFishingEventRequest;
import smartfish.modules.fishing_event.application.dto.response.create.CreateFishingEventResponse;
import smartfish.modules.fishing_event.application.dto.response.read.FishingEventResponse;
import smartfish.modules.fishing_event.application.exception.FishingEventNotFoundException;
import smartfish.modules.fishing_event.application.mapper.FishingEventMapper;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.fishing_event.domain.repository.FishingEventRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FishingEventService {

    private final FishingEventRepository fishingEventRepository;

    private final FishingEventMapper fishingEventMapper;

    public CreateFishingEventResponse createFishingEvent(CreateFishingEventRequest request) {
        FishingEventEntity fishingEvent =
                fishingEventMapper.toEntity(request);

        fishingEventRepository.save(fishingEvent);
    }

    public FishingEventResponse findById(UUID uuid) {
        FishingEventEntity fishingEvent = fishingEventRepository.findById(uuid)
                        .orElseThrow(() -> new FishingEventNotFoundException("Local de pesca não encontrado."));

        return fishingEventMapper.toResponse(fishingEvent);
    }

    public FishingEventResponse update(UpdateFishingEventRequest request) {

    }
}

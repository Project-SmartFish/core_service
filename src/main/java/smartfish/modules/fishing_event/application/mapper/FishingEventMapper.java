package smartfish.modules.fishing_event.application.mapper;

import org.springframework.stereotype.Component;
import smartfish.modules.fishing_event.application.dto.request.create.CreateFishingEventRequest;
import smartfish.modules.fishing_event.application.dto.response.create.CreateFishingEventResponse;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;

@Component
public class FishingEventMapper {

    public FishingEventEntity toEntity(CreateFishingEventRequest request) {

        return FishingEventEntity.builder()
                .eventDate(request.eventDate())
                .eventTime(request.eventTime())
                .build();
    }

    public CreateFishingEventResponse toDomain(FishingEventEntity entity) {

        return new CreateFishingEventResponse(
                entity.getId(),
                entity.getFishingStatus(),
                entity.getEventDate(),
                entity.getEventTime()
        );
    }
}

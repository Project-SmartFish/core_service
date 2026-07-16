package smartfish.modules.user_fishing_event.application.mapper;

import org.springframework.stereotype.Component;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user_fishing_event.application.dto.request.join.JoinFishingEventRequest;
import smartfish.modules.user_fishing_event.application.dto.response.join.JoinFishingEventResponse;
import smartfish.modules.user_fishing_event.application.dto.response.read.UserFishingEventResponse;
import smartfish.modules.user_fishing_event.domain.core.UserFishingEventEntity;

@Component
public class UserFishingEventMapper {

    public UserFishingEventEntity toEntity(JoinFishingEventRequest request) {
        FishingEventEntity fishingEvent = FishingEventEntity
                .builder()
                .id(request.fishingEventId())
                .build();

        return UserFishingEventEntity
                .builder()
                .fishingEvent(fishingEvent)
                .build();
    }

    public UserFishingEventResponse toResponse(UserFishingEventEntity entity) {
        return new UserFishingEventResponse(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFishingEvent().getId()
        );
    }

    public JoinFishingEventResponse toJoinResponse(UserFishingEventEntity entity) {
        return new JoinFishingEventResponse(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFishingEvent().getId()
        );
    }
}

package smartfish.modules.user_fishing_event.application.dto.response.join;

import java.util.UUID;

public record JoinFishingEventResponse(
        UUID id,
        UUID userId,
        UUID fishingEvent
) {}

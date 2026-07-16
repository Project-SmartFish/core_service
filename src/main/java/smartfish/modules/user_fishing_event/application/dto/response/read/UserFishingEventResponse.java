package smartfish.modules.user_fishing_event.application.dto.response.read;

import java.util.UUID;

public record UserFishingEventResponse(
        UUID id,
        UUID userId,
        UUID fishingEvent
) {}

package smartfish.modules.fishing_event.application.dto.response.update;

import smartfish.modules.fishing_event.domain.FishingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record UpdateFishingEventResponse(
        UUID id,
        UUID fishSpotId,
        FishingStatus fishingStatus,
        LocalDate eventDate,
        LocalTime eventTime
) {}

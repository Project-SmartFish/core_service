package smartfish.modules.fishing_event.application.dto.response.create;

import smartfish.modules.fishing_event.domain.FishingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateFishingEventResponse(
        UUID id,
        FishingStatus fishingStatus,
        LocalDate eventDate,
        LocalTime eventTime
) {}

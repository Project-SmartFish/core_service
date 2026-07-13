package smartfish.modules.fish_spot.application.dto.response;

import smartfish.modules.fish_spot.domain.enums.WaterType;

import java.util.UUID;

public record FishSpotResponse(

        UUID id,
        String name,
        Double latitude,
        Double longitude,
        WaterType waterType

) {
}

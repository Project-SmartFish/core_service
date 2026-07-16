package smartfish.modules.user_fishing_event.application.dto.request.join;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JoinFishingEventRequest(

        @NotNull(message = "Id de usuário é obrigatório")
        UUID userId,

        @NotNull(message = "Id de evento de pesca é obrigatório")
        UUID fishingEventId
) {}

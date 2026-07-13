package smartfish.modules.fishing_event.application.dto.request.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateFishingEventRequest(

        @NotNull(message = "Id de local de pesca é obrigatório")
        UUID fishSpotId,

        @NotNull(message = "A data do evento é obrigatória.")
        @FutureOrPresent(message = "A data do evento não pode estar no passado")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate eventDate,

        @NotNull(message = "O horário do evento é obrigatório")
        @JsonFormat(pattern = "HH:mm")
        LocalTime eventTime
) {}

package smartfish.modules.fishing_event.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fishing_event")
public class FishingEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Builder.Default
    @Column(name = "fishing_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FishingStatus fishingStatus = FishingStatus.OPEN;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event_time", nullable = false)
    private LocalTime eventTime;

    public void finishEvent() {
        this.fishingStatus = FishingStatus.CLOSED;
    }

    public void cancelEvent() {
        this.fishingStatus = FishingStatus.CANCELLED;
    }
}

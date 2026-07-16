package smartfish.modules.user_fishing_event.domain.core;

import jakarta.persistence.*;
import lombok.*;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.user.domain.entity.UserEntity;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(
        name = "user_fishing_event",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_fishing_event_user_event",
                        columnNames = {
                                "user_id",
                                "fishing_event_id"
                        }
                )
        }
)
public class UserFishingEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "fishing_event_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_user_fishing_event_fishing_event")
    )
    private FishingEventEntity fishingEvent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_user_fishing_event_user")
    )
    private UserEntity user;
}

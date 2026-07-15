package smartfish.modules.personal_fishing.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_fishing")
public class PersonalFishingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(nullable = false, name = "specie")
    private String specie;

    @Column(nullable = false, name = "amount")
    private int amount;

    @Column(nullable = false, name = "estimed_weight_kg")
    private BigDecimal estimedWeightKg;
}

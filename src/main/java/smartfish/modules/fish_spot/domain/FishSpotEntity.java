package smartfish.modules.fish_spot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import smartfish.modules.fish_spot.domain.enums.WaterType;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FishSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "geography(Point,4326)", nullable = false)
    private Point location;

    @Column(nullable = false)
    private WaterType waterType;
}

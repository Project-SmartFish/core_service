package smartfish.modules.fish_spot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FishSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "geography(Point,4326)", nullable = false)
    private Point location;


}

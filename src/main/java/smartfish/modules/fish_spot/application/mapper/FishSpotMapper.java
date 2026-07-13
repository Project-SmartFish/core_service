package smartfish.modules.fish_spot.application.mapper;

import org.locationtech.jts.geom.*;
import smartfish.modules.fish_spot.application.dto.request.create.CreateFishSpotRequest;
import smartfish.modules.fish_spot.application.dto.response.FishSpotResponse;
import smartfish.modules.fish_spot.domain.FishSpotEntity;

public class FishSpotMapper {

    private static final GeometryFactory GEOMETRY_FACTORY =
            new GeometryFactory(new PrecisionModel(), 4326);

    public static FishSpotEntity toEntity(CreateFishSpotRequest request) {

        Point point = GEOMETRY_FACTORY.createPoint(
                new Coordinate(
                        request.longitude(),
                        request.latitude()
                )
        );

        return FishSpotEntity.builder()
                .name(request.name())
                .location(point)
                .waterType(request.waterType())
                .build();
    }

    public static FishSpotResponse toResponse(FishSpotEntity fishSpot) {

        return new FishSpotResponse(
                fishSpot.getId(),
                fishSpot.getName(),
                fishSpot.getLocation().getY(),
                fishSpot.getLocation().getX(),
                fishSpot.getWaterType()
        );
    }
}
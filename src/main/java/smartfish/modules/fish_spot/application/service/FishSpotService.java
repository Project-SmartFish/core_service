package smartfish.modules.fish_spot.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartfish.modules.fish_spot.application.dto.request.create.CreateFishSpotRequest;
import smartfish.modules.fish_spot.application.dto.request.update.UpdateFishSpotRequest;
import smartfish.modules.fish_spot.application.dto.response.FishSpotResponse;
import smartfish.modules.fish_spot.application.exceptions.FishSpotNotFoundException;
import smartfish.modules.fish_spot.application.mapper.FishSpotMapper;
import smartfish.modules.fish_spot.domain.FishSpotEntity;
import smartfish.modules.fish_spot.domain.repository.FishSpotRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FishSpotService {

    private final FishSpotRepository fishSpotRepository;

    public void createFishSpot (CreateFishSpotRequest createFishSpotRequest)
    {
        FishSpotEntity fishSpot = FishSpotMapper.toEntity(createFishSpotRequest);

        fishSpotRepository.save(fishSpot);

    }

    public void updateFishSpot(UpdateFishSpotRequest request, UUID id) throws FishSpotNotFoundException {

        fishSpotRepository.findById(id)
                .orElseThrow(() -> new FishSpotNotFoundException("Local de pesca não encontrado."));

        FishSpotEntity fishSpot = FishSpotMapper.updateEntity(id, request);

        fishSpotRepository.save(fishSpot);
    }

    public FishSpotResponse findByIdFishSpot (UUID id) throws FishSpotNotFoundException {
        FishSpotEntity fishSpot = fishSpotRepository.findById(id)
                .orElseThrow(() -> new FishSpotNotFoundException("Local de pesca não encontrado."));

        return FishSpotMapper.toResponse(fishSpot);
    }

    public List<FishSpotResponse> findAllFishSpot() {

        return fishSpotRepository.findAll()
                .stream()
                .map(FishSpotMapper::toResponse)
                .toList();
    }

    public void deleteFishSpot (UUID id) throws FishSpotNotFoundException {
        FishSpotEntity fishSpot = fishSpotRepository.findById(id)
                .orElseThrow(() -> new FishSpotNotFoundException("Local de pesca não encontrado."));

        fishSpotRepository.delete(fishSpot);
    }


}

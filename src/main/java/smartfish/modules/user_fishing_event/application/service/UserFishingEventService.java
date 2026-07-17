package smartfish.modules.user_fishing_event.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartfish.modules.auth.infrastructure.security.SecurityCurrentUserProvider;
import smartfish.modules.fishing_event.application.exception.FishingEventNotFoundException;
import smartfish.modules.fishing_event.domain.core.FishingEventEntity;
import smartfish.modules.fishing_event.domain.repository.FishingEventRepository;
import smartfish.modules.user.domain.entity.UserEntity;
import smartfish.modules.user.domain.repository.UserRepository;
import smartfish.modules.user_fishing_event.application.dto.request.join.JoinFishingEventRequest;
import smartfish.modules.user_fishing_event.application.dto.response.join.JoinFishingEventResponse;
import smartfish.modules.user_fishing_event.application.dto.response.read.UserFishingEventResponse;
import smartfish.modules.user_fishing_event.application.exception.UserNotParticipatingException;
import smartfish.modules.user_fishing_event.application.mapper.UserFishingEventMapper;
import smartfish.modules.user_fishing_event.domain.core.UserFishingEventEntity;
import smartfish.modules.user_fishing_event.domain.repository.UserFishingEventRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFishingEventService {

    private final UserFishingEventRepository repository;
    private final FishingEventRepository fishingEventRepository;
    private final UserRepository userRepository;
    private SecurityCurrentUserProvider userProvider;
    private final UserFishingEventMapper mapper;

    @Transactional
    public JoinFishingEventResponse joinUserFishingEvent(JoinFishingEventRequest request) {

        UserEntity user = findCurrentUser();

        FishingEventEntity fishingEvent = fishingEventRepository.findById(request.fishingEventId())
                .orElseThrow(() ->
                        new FishingEventNotFoundException("Evento de Pesca não encontrado"));

        UserFishingEventEntity entity = mapper.toEntity(
                request,
                user,
                fishingEvent);

        entity = repository.save(entity);

        return mapper.toJoinResponse(entity);
    }

    @Transactional
    public void leaveUserFishingEvent(UUID fishingEventId) {

        UserEntity user = findCurrentUser();

        UserFishingEventEntity entity =
                repository.findByUser_IdAndFishingEvent_Id(
                        user.getId(),
                        fishingEventId
                ).orElseThrow(() ->
                        new UserNotParticipatingException("Usuário não participa desse evento"));

        repository.delete(entity);
    }

    @Transactional(readOnly = true)
    public List<UserFishingEventResponse> listCurrentUserFishingEvents() {
        UserEntity user = findCurrentUser();

        List<UserFishingEventEntity> entities = repository
                .findByUser_Id(user.getId());

        return entities.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserFishingEventResponse> listFishingEventParticipants(UUID fishingEventId) {

        List<UserFishingEventEntity> entities = repository
                .findByFishingEvent_Id(fishingEventId);

        return entities.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


    private UserEntity findCurrentUser() {
        String userEmail = userProvider.getCurrentUserEmail();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        return user;
    }

}

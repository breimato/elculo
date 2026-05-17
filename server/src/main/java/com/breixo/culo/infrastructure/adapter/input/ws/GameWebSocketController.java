package com.breixo.culo.infrastructure.adapter.input.ws;

import com.breixo.culo.domain.exception.CuloException;
import com.breixo.culo.domain.port.input.room.CreateRoomUseCase;
import com.breixo.culo.domain.port.input.room.JoinRoomUseCase;
import com.breixo.culo.domain.port.input.room.StartGameUseCase;
import com.breixo.culo.domain.port.output.room.RoomPersistencePort;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.CreateRoomRequestDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.JoinRoomRequestDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.StartGameRequestDto;
import com.breixo.culo.infrastructure.adapter.input.ws.mapper.CreateRoomRequestMapper;
import com.breixo.culo.infrastructure.adapter.input.ws.mapper.JoinRoomRequestMapper;
import com.breixo.culo.infrastructure.adapter.input.ws.mapper.StartGameRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameWebSocketController {

  /** The create room use case. */
  private final CreateRoomUseCase createRoomUseCase;

  /** The join room use case. */
  private final JoinRoomUseCase joinRoomUseCase;

  /** The start game use case. */
  private final StartGameUseCase startGameUseCase;

  /** The room event publisher. */
  private final RoomEventPublisher roomEventPublisher;

  /** The room persistence port. */
  private final RoomPersistencePort roomPersistencePort;

  /** The create room request mapper. */
  private final CreateRoomRequestMapper createRoomRequestMapper;

  /** The join room request mapper. */
  private final JoinRoomRequestMapper joinRoomRequestMapper;

  /** The start game request mapper. */
  private final StartGameRequestMapper startGameRequestMapper;

  @MessageMapping("/room.create")
  public void createRoom(@Payload final CreateRoomRequestDto createRoomRequestDto) {
    try {
      final var createRoomCommand = this.createRoomRequestMapper.toCreateRoomCommand(createRoomRequestDto);
      final var roomJoinResult = this.createRoomUseCase.execute(createRoomCommand);
      this.roomEventPublisher.publishJoinResult(roomJoinResult);
    } catch (final CuloException culoException) {
      log.warn("Error creating room: {}", culoException.getMessage());
    }
  }

  @MessageMapping("/room.join")
  public void joinRoom(@Payload final JoinRoomRequestDto joinRoomRequestDto) {
    try {
      final var joinRoomCommand = this.joinRoomRequestMapper.toJoinRoomCommand(joinRoomRequestDto);
      final var roomJoinResult = this.joinRoomUseCase.execute(joinRoomCommand);
      this.roomEventPublisher.publishJoinResult(roomJoinResult);
    } catch (final CuloException culoException) {
      log.warn("Error joining room: {}", culoException.getMessage());
      this.roomEventPublisher.publishErrorToClient(joinRoomRequestDto.getClientId(), culoException);
      this.publishErrorIfPlayerKnown(
          joinRoomRequestDto.getClientId(),
          joinRoomRequestDto.getRoomCode(),
          culoException);
    }
  }

  @MessageMapping("/room.start")
  public void startGame(@Payload final StartGameRequestDto startGameRequestDto) {
    try {
      final var startGameCommand = this.startGameRequestMapper.toStartGameCommand(startGameRequestDto);
      final var room = this.startGameUseCase.execute(startGameCommand);
      this.roomEventPublisher.publishRoomState(room);
    } catch (final CuloException culoException) {
      log.warn("Error starting game: {}", culoException.getMessage());
      this.roomEventPublisher.publishErrorToClient(startGameRequestDto.getClientId(), culoException);
      this.publishErrorIfPlayerKnown(
          startGameRequestDto.getClientId(),
          startGameRequestDto.getRoomCode(),
          culoException);
    }
  }

  private void publishErrorIfPlayerKnown(
      final String clientId,
      final String roomCode,
      final CuloException culoException) {
    final var playerId = this.resolvePlayerId(clientId, roomCode);
    playerId.ifPresent(id -> this.roomEventPublisher.publishError(id, culoException));
  }

  private Optional<String> resolvePlayerId(final String clientId, final String roomCode) {
    if (StringUtils.isBlank(roomCode)) {
      return Optional.empty();
    }
    return this.roomPersistencePort.findByCode(roomCode)
        .flatMap(room -> room.findPlayerByClientId(clientId))
        .map(player -> player.getId());
  }
}

package com.breixo.culo.infrastructure.adapter.input.ws;

import com.breixo.culo.domain.exception.CuloException;
import com.breixo.culo.domain.model.Room;
import com.breixo.culo.domain.model.room.RoomJoinResult;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.JoinedRoomDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.RoomStateDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.WsErrorDto;
import com.breixo.culo.infrastructure.adapter.input.ws.mapper.JoinedRoomDtoMapper;
import com.breixo.culo.infrastructure.adapter.input.ws.mapper.RoomStateDtoMapper;
import com.breixo.culo.infrastructure.config.WsDestinationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomEventPublisher {

  /** The simp messaging template. */
  private final SimpMessagingTemplate simpMessagingTemplate;

  /** The joined room dto mapper. */
  private final JoinedRoomDtoMapper joinedRoomDtoMapper;

  /** The room state dto mapper. */
  private final RoomStateDtoMapper roomStateDtoMapper;

  public void publishJoinResult(final RoomJoinResult roomJoinResult) {
    final var joinedRoomDto = this.joinedRoomDtoMapper.toJoinedRoomDto(roomJoinResult);
    final var clientId = roomJoinResult.room().findPlayerById(roomJoinResult.playerId())
        .map(player -> player.getClientId())
        .orElseThrow();
    this.publishJoinedRoom(roomJoinResult.playerId(), joinedRoomDto);
    this.publishJoinedRoomToClient(clientId, joinedRoomDto);
    this.publishRoomState(roomJoinResult.room());
  }

  public void publishJoinedRoomToClient(final String clientId, final JoinedRoomDto joinedRoomDto) {
    final var destination = WsDestinationConstants.clientTopic(clientId)
        + WsDestinationConstants.JOINED_ROOM_SUFFIX;
    this.simpMessagingTemplate.convertAndSend(destination, joinedRoomDto);
  }

  public void publishErrorToClient(final String clientId, final CuloException culoException) {
    final var wsErrorDto = WsErrorDto.builder()
        .code(culoException.getCode())
        .message(culoException.getMessage())
        .build();
    final var destination = WsDestinationConstants.clientTopic(clientId)
        + WsDestinationConstants.ERROR_SUFFIX;
    this.simpMessagingTemplate.convertAndSend(destination, wsErrorDto);
  }

  public void publishRoomState(final Room room) {
    final var roomStateDto = this.roomStateDtoMapper.toRoomStateDto(room);
    final var destination = WsDestinationConstants.roomTopic(room.getCode())
        + WsDestinationConstants.ROOM_STATE_SUFFIX;
    this.simpMessagingTemplate.convertAndSend(destination, roomStateDto);
  }

  public void publishJoinedRoom(final String playerId, final JoinedRoomDto joinedRoomDto) {
    final var destination = WsDestinationConstants.playerQueue(playerId)
        + WsDestinationConstants.JOINED_ROOM_SUFFIX;
    this.simpMessagingTemplate.convertAndSend(destination, joinedRoomDto);
  }

  public void publishError(final String playerId, final CuloException culoException) {
    final var wsErrorDto = WsErrorDto.builder()
        .code(culoException.getCode())
        .message(culoException.getMessage())
        .build();
    final var destination = WsDestinationConstants.playerQueue(playerId)
        + WsDestinationConstants.ERROR_SUFFIX;
    this.simpMessagingTemplate.convertAndSend(destination, wsErrorDto);
  }
}

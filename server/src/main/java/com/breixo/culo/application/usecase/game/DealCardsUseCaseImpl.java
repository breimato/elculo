package com.breixo.culo.application.usecase.game;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.domain.PlayerRole;
import com.breixo.culo.domain.command.game.DealCardsCommand;
import com.breixo.culo.domain.exception.GameException;
import com.breixo.culo.domain.exception.RoomException;
import com.breixo.culo.domain.exception.constants.GameExceptionConstants;
import com.breixo.culo.domain.exception.constants.RoomExceptionConstants;
import com.breixo.culo.domain.model.Room;
import com.breixo.culo.domain.port.input.game.DealCardsUseCase;
import com.breixo.culo.domain.port.output.room.RoomPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DealCardsUseCaseImpl implements DealCardsUseCase {

  /** The room persistence port. */
  private final RoomPersistencePort roomPersistencePort;

  @Override
  public Room execute(final DealCardsCommand dealCardsCommand) {
    final var room = this.roomPersistencePort.findByCode(dealCardsCommand.roomCode())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.ROOM_NOT_FOUND));
    final var player = room.findPlayerByClientId(dealCardsCommand.clientId())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.PLAYER_NOT_IN_ROOM));
    if (!room.getPhase().equals(GamePhase.DEALING)) {
      throw new GameException(GameExceptionConstants.WRONG_PHASE);
    }
    final var isCulo = player.getRole() == PlayerRole.CULO;
    final var isFirstGame = room.getLastCuloId() == null;
    final var isHost = room.isHost(player.getId());
    if (!isCulo && !isFirstGame) {
      throw new GameException(GameExceptionConstants.NOT_CULO);
    }
    if (isFirstGame && !isHost) {
      throw new RoomException(RoomExceptionConstants.NOT_HOST);
    }
    room.dealCards();
    return this.roomPersistencePort.save(room);
  }
}

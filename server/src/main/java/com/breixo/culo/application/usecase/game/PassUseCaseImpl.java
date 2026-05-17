package com.breixo.culo.application.usecase.game;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.domain.RuleEngine;
import com.breixo.culo.domain.command.game.PassCommand;
import com.breixo.culo.domain.exception.GameException;
import com.breixo.culo.domain.exception.RoomException;
import com.breixo.culo.domain.exception.constants.GameExceptionConstants;
import com.breixo.culo.domain.exception.constants.RoomExceptionConstants;
import com.breixo.culo.domain.model.game.PassResult;
import com.breixo.culo.domain.port.input.game.PassUseCase;
import com.breixo.culo.domain.port.output.room.RoomPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PassUseCaseImpl implements PassUseCase {

  private static final RuleEngine RULE_ENGINE = new RuleEngine();

  /** The room persistence port. */
  private final RoomPersistencePort roomPersistencePort;

  @Override
  public PassResult execute(final PassCommand passCommand) {
    final var room = this.roomPersistencePort.findByCode(passCommand.roomCode())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.ROOM_NOT_FOUND));
    final var player = room.findPlayerByClientId(passCommand.clientId())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.PLAYER_NOT_IN_ROOM));
    if (!room.getPhase().equals(GamePhase.PLAYING)) {
      throw new GameException(GameExceptionConstants.WRONG_PHASE);
    }
    if (!player.getId().equals(room.getCurrentPlayerId())) {
      throw new GameException(GameExceptionConstants.NOT_YOUR_TURN);
    }

    final var round = room.getCurrentRound();
    round.registerPass();

    final var roundEnded = RULE_ENGINE.isRoundOver(round, room.getActivePlayerCount());
    if (roundEnded) {
      final var winnerId = round.getLastPlayerId();
      round.reset();
      if (winnerId != null) {
        final var winnerIdx = room.getPlayerOrder().indexOf(winnerId);
        room.setCurrentPlayerIndex(winnerIdx);
      }
    } else {
      room.advanceTurn(false);
    }

    final var savedRoom = this.roomPersistencePort.save(room);
    return PassResult.builder()
        .room(savedRoom)
        .playerId(player.getId())
        .roundEnded(roundEnded)
        .build();
  }
}
